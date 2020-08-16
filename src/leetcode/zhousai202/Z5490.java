package leetcode.zhousai202;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 5490. 吃掉 N 个橘子的最少天数
 * 厨房里总共有 n 个橘子，你决定每一天选择如下方式之一吃这些橘子：
 *      吃掉一个橘子。
 *      如果剩余橘子数 n 能被 2 整除，那么你可以吃掉 n/2 个橘子。
 *      如果剩余橘子数 n 能被 3 整除，那么你可以吃掉 2*(n/3) 个橘子。
 *      每天你只能从以上 3 种方案中选择一种方案。
 * 请你返回吃掉所有 n 个橘子的最少天数。
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：你总共有 10 个橘子。
 * 第 1 天：吃 1 个橘子，剩余橘子数 10 - 1 = 9。
 * 第 2 天：吃 6 个橘子，剩余橘子数 9 - 2*(9/3) = 9 - 6 = 3。（9 可以被 3 整除）
 * 第 3 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。
 * 第 4 天：吃掉最后 1 个橘子，剩余橘子数 1 - 1 = 0。
 * 你需要至少 4 天吃掉 10 个橘子。
 *
 * 示例 2：
 * 输入：n = 6
 * 输出：3
 * 解释：你总共有 6 个橘子。
 * 第 1 天：吃 3 个橘子，剩余橘子数 6 - 6/2 = 6 - 3 = 3。（6 可以被 2 整除）
 * 第 2 天：吃 2 个橘子，剩余橘子数 3 - 2*(3/3) = 3 - 2 = 1。（3 可以被 3 整除）
 * 第 3 天：吃掉剩余 1 个橘子，剩余橘子数 1 - 1 = 0。
 * 你至少需要 3 天吃掉 6 个橘子。
 *
 * 示例 3：
 * 输入：n = 1
 * 输出：1
 *
 * 示例 4：
 * 输入：n = 56
 * 输出：6
 *
 * 提示：
 *
 * 1 <= n <= 2*10^9
 *
 */
public class Z5490 {
    private int min = Integer.MAX_VALUE;
    
    @Test
    public void test1() {
        Assert.assertEquals( 4, minDays1( 10 ) );
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 3, minDays1( 6 ) );
    }
    
    @Test
    public void test3() {
        Assert.assertEquals( 6, minDays1( 56 ) );
    }
    
    @Test
    public void test4() {
        Assert.assertEquals( 10, minDays1( 673 ) );
    }
    
    // @Test
    // public void test5() {
    //     Assert.assertEquals( 29, minDays1( 61455274 ) );
    // }
    
    /**
     * 通过回溯法暴力求解
     *
     * @param n
     * @return
     */
    public int minDays1( int n ) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        traceBack( n, 0 );
        return min;
    }
    
    /**
     * 回溯法超时
     *
     * @param n
     * @param days
     */
    private void traceBack( int n, int days ) {
        System.out.println( "n=" + n + ",days=" + days );
        if (n <= 0) {
            if (days < min) {
                min = days;
            }
            return;
        }
        if (n % 3 == 0) {
            traceBack( n / 3, days + 1 );
        } else if (n % 2 == 0) {
            traceBack( n / 2, days + 1 );
        }
        traceBack( n - 1, days + 1 );
    }
    
    @Test
    public void test21() {
        Assert.assertEquals( 4, minDays2( 10 ) );
    }
    
    @Test
    public void test22() {
        Assert.assertEquals( 3, minDays2( 6 ) );
    }
    
    @Test
    public void test23() {
        Assert.assertEquals( 6, minDays2( 56 ) );
    }
    
    @Test
    public void test24() {
        Assert.assertEquals( 10, minDays2( 673 ) );
    }
    
    @Test
    public void test25() {
        Assert.assertEquals( 29, minDays2( 61455274 ) );
    }
    
    /**
     * 动态规划
     *
     * 内存超限
     *
     * @param n
     * @return
     */
    public int minDays2( int n ) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int dp[] = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 3 == 0) {
                dp[i] = Math.min( dp[i / 3] + 1, dp[i - 1] + 1 );
            } else if (i % 2 == 0) {
                dp[i] = Math.min( dp[i / 2] + 1, dp[i - 1] + 1 );
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        return dp[n];
    }
    
    @Test
    public void test31() {
        Assert.assertEquals( 4, minDays3( 10 ) );
    }
    
    @Test
    public void test32() {
        Assert.assertEquals( 3, minDays3( 6 ) );
    }
    
    @Test
    public void test33() {
        Assert.assertEquals( 6, minDays3( 56 ) );
    }
    
    @Test
    public void test34() {
        Assert.assertEquals( 10, minDays3( 673 ) );
    }
    
    @Test
    public void test35() {
        Assert.assertEquals( 29, minDays3( 61455274 ) );
    }
    
    /**
     * 记忆递归
     *
     *  通过
     *
     * @param n
     * @return
     */
    public int minDays3( int n ) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return dfs( n, new HashMap<Integer, Integer>() );
    }
    
    /**
     *
     * @param n     剩余桔子个数
     * @param memo
     * @return
     */
    private int dfs( int n, Map<Integer, Integer> memo ) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        if (memo.containsKey( n )) {
            return memo.get( n );
        }
        int result = 1 + Math.min( dfs( n / 2, memo ) + n % 2, dfs( n / 3, memo ) + n % 3 );
        memo.put( n, result );
        return result;
    }
    
}

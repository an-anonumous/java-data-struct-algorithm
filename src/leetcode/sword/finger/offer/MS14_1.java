package leetcode.sword.finger.offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题14- I. 剪绳子
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 *
 * 2 <= n <= 58
 */
public class MS14_1 {
    
    /**
     * 暴力递归
     *
     * n=2:  1+1  -->1*1=1;
     * n=3:  2+1  -->2*1=2;
     * n=4:  2+2  -->2*2=4;
     *
     * @param n
     * @return
     */
    
    // 用来记录保存已经计算过得值，减少重复计算
    Map<Integer, Integer> map = new HashMap<>();
    
    /**
     * 每一步分割一段，直到绳子分完。在每一步中群举所有可能的长度取值。
     * 每一步都群集所有的可能
     *
     * @param n
     * @return
     */
    public int recursive( int n ) {
        // 从上面注释可以看出来，n为2,3,4时分割后的积比原数小，所以小于等于4时不再分割
        if (n <= 4) {
            return n;
        }
        
        // 减少重复计算
        if (map.containsKey( n )) {
            return map.get( n );
        }
        
        int max = 0;
        for (int i = 1; i < n; i++) {
            // 先分一段，穷举第一段所有可能，取最大值
            max = Math.max( max, i * recursive( n - i ) );
        }
        
        map.put( n, max );
        return max;
    }
    
    public int cuttingRope( int n ) {
        // 如果n为2,3，虽然分割后的积比原数字还小，但是题意必须分，且可以直接得出答案，故直接返回
        if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        
        // 长度大于3的，暴力递归求解
        return recursive( n );
    }
    
    
    @Test
    public void Test() {
        MS14_1 ms14_1 = new MS14_1();
        Assert.assertEquals( 1, ms14_1.cuttingRope( 2 ) );
        Assert.assertEquals( 1, ms14_1.dpcutRope( 2 ) );
    }
    
    @Test
    public void Test1() {
        MS14_1 ms14_1 = new MS14_1();
        Assert.assertEquals( 36, ms14_1.dpcutRope( 10 ) );
    }
    
    /**
     * 迭代版动态规划
     *
     * 在暴力递归中，每一步分一段，直至绳子分完。每一步中都群举这一段可以去的长度，然后在剩下的绳子中求规模小一点的最大值，
     * 最后返回这两者之积的最大值。
     *
     * @param n
     * @return
     */
    int dpcutRope( int n ) {
        // 如果n为2,3，虽然分割后的积比原数字还小，但是题意必须分，且可以直接得出答案，故直接返回
        if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        } else if (n == 4) {
            return 4;
        }
        
        // dp[k]表示绳子长度为k时，分割后长度之积最大值
        int[] dp = new int[n + 1];
        
        // 长度大于4后，为了保证取得最大值，2,3不再分割。
        for (int i = 0; i <= 4; i++) {
            dp[i] = i;
        }
        
        for (int i = 5; i <= n; i++) {
            dp[i] = 0;
        }
        
        // 开始填表
        for (int i = 5; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (dp[i] < j * dp[i - j]) {
                    dp[i] = j * dp[i - j];
                }
            }
        }
        return dp[n];
    }
    
    @Test
    public void Test2() {
        MS14_1 ms14_1 = new MS14_1();
        System.out.println( ms14_1.dpcutRope( 10000 ) );
    }
    
    
}

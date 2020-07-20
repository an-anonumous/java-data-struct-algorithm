package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 312. 戳气球
 *
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。现在要求你戳破所有的气球。如果你戳破气球 i ，就可以
 * 获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，
 * 气球 left 和气球 right 就变成了相邻的气球。求所能获得硬币的最大数量。
 *
 * 说明:
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * 示例:
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 */
public class L312 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 167, maxCoins( new int[]{3, 1, 5, 8} ) );
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 3630, maxCoins( new int[]{8, 2, 6, 8, 9, 8, 1, 4, 1, 5, 3, 0, 7, 7, 0, 4, 2, 2, 5} ) );
    }
    
    /**
     * 为了方便处理，我们对nums 数组稍作处理，将其两边各加上题目中假设存在的 nums[−1] 和 nums[n] ，并保存在val 数组中，即
     * val[i]=nums[i−1] 。之所以这样处理是为了处理 nums[−1] ，防止下标越界。下文中的区间均指数组 val 上的区间。
     *
     * 思路及算法
     *
     * 我们观察戳气球的操作，发现这会导致两个气球从不相邻变成相邻，使得后续操作难以处理。于是我们倒过来看这些操作，将全过程看作是每次添加一
     * 个气球。 我们定义方法 solve，令 solve(i,j) 表示将开区间 (i,j) 内的位置全部填满气球能够得到的最多硬币数。由于是开区间，因此区间两
     * 端的气球的编号就是 i 和 j，对应着 val[i] 和 val[j]。当i≥j−1 时，开区间中没有气球，solve(i,j) 的值为 0；当 i < j-1i<j−1 时，
     * 我们枚举开区间 (i,j) 内的全部位置 mid，令mid 为当前区间第一个添加的气球(也就是最后一个被扎破的气球)，该操作能得到的硬币数为
     * val[i]×val[mid]×val[j]。同时我们递归地计算分割出的两区间对 solve(i,mid) 和 solve(mid,j) 的贡献，这三项之和的最大值，
     * 即为solve(i,j) 的值。
     * 这样问题就转化为求 solve(i,mid) 和 solve(mid,j) ，可以写出方程：
     *          sove(i,j)=max( val[i]×val[mid]×val[j] + solve(i,mid) + solve(mid,j) )  当i<j−1时
     *                   =0                                                            当i>=j-1时
     *
     * 超时
     *
     * @param nums
     * @return
     */
    public int maxCoins( int[] nums ) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        int[] val = new int[n + 2];
        for (int i = 1; i < val.length - 1; i++) {
            val[i] = nums[i - 1];
        }
        val[0] = 1;
        val[n + 1] = 1;
        
        return solve( val, 0, val.length - 1 );
    }
    
    private int solve( int[] nums, int i, int j ) {
        if (i >= j - 1) {
            return 0;
        }
        int sum = 0;
        for (int mid = i + 1; mid < j; mid++) {
            sum = Math.max( sum, nums[i] * nums[mid] * nums[j] + solve( nums, i, mid ) + solve( nums, mid, j ) );
        }
        return sum;
    }
    
    @Test
    public void test3() {
        Assert.assertEquals( 167, maxCoins2( new int[]{3, 1, 5, 8} ) );
    }
    
    @Test
    public void test4() {
        Assert.assertEquals( 3630, maxCoins2( new int[]{8, 2, 6, 8, 9, 8, 1, 4, 1, 5, 3, 0, 7, 7, 0, 4, 2, 2, 5} ) );
    }
    
    /**
     * 记忆优化，减少重复计算
     *
     * @param nums
     * @return
     */
    public int maxCoins2( int[] nums ) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        int[] val = new int[n + 2];
        for (int i = 1; i < val.length - 1; i++) {
            val[i] = nums[i - 1];
        }
        val[0] = 1;
        val[n + 1] = 1;
        
        int[][] mem = new int[n + 2][n + 2];
        for (int[] m : mem) {
            Arrays.fill( m, -1 );
        }
        
        int result = solve2( val, 0, val.length - 1, mem );
        for (int[] m : mem) {
            System.out.println( Arrays.toString( m ) );
        }
        
        return result;
    }
    
    private int solve2( int[] nums, int i, int j, int[][] mem ) {
        if (i >= j - 1) {
            mem[i][j] = 0;
            return 0;
        }
        
        if (mem[i][j] != -1) {
            return mem[i][j];
        }
        
        int sum = 0;
        for (int mid = i + 1; mid < j; mid++) {
            sum = Math.max( sum, nums[i] * nums[mid] * nums[j] + solve2( nums, i, mid, mem ) + solve2( nums, mid, j, mem
            ) );
        }
        mem[i][j] = sum;
        return sum;
    }
    
    @Test
    public void test5() {
        Assert.assertEquals( 167, dpMaxCoins( new int[]{3, 1, 5, 8} ) );
    }
    
    @Test
    public void test6() {
        Assert.assertEquals( 3630, dpMaxCoins( new int[]{8, 2, 6, 8, 9, 8, 1, 4, 1, 5, 3, 0, 7, 7, 0, 4, 2, 2, 5} ) );
    }
    
    public int dpMaxCoins( int[] nums ) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        final int n = nums.length;
        int[] val = new int[n + 2];
        for (int i = 1; i < val.length - 1; i++) {
            val[i] = nums[i - 1];
        }
        val[0] = 1;
        val[n + 1] = 1;
        
        int[][] dp = new int[n + 2][n + 2];
        
        // 沿主对角线，从右下角开始向左上角填表
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = i + 2; j < dp.length; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max( dp[i][j], val[i] * val[j] * val[k] + dp[i][k] + dp[k][j] );
                }
            }
        }
        
        return dp[0][n + 1];
    }
}

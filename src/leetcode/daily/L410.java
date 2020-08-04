package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 410. 分割数组的最大值
 *
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 *
 * 注意:
 * 数组长度 n 满足以下条件:
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 *
 * 示例:
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 * 输出:
 * 18
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 */
public class L410 {
    
    @Test
    public void test() {
        Assert.assertEquals( 18, splitArray( new int[]{7, 2, 5, 10, 8}, 2 ) );
        
    }
    
    /**
     * 方法一：动态规划
     *
     * 思路与算法
     * 「将数组分割为 mm 段，求……」是动态规划题目常见的问法。
     *
     * 本题中，我们可以令 f[i][j] 表示将数组的前 i 个数分割为 j 段所能得到的最大连续子数组和的最小值。在进行状态转移时，我们可以考虑第j
     * 段的具体范围，即我们可以枚举 k，其中前 k个数被分割为 j-1 段，而第 k+1 到第 i 个数为第 j 段。此时，这 j 段子数组中和的最大值，就
     * 等于 f[k][j-1] 与 sub(k+1, i)中的较大值，其中 sub(i,j) 表示数组 nums 中下标落在区间 [i,j]内的数的和。
     *
     * 由于我们要使得子数组中和的最大值最小，因此可以列出如下的状态转移方程：
     *           f[i][j] = min(max(f[k][j−1],sub(k+1,i) ) )     i>k>=0
     *
     * 对于状态 f[i][j]，由于我们不能分出空的子数组，因此合法的状态必须有 i≥j。对于不合法（i <j）的状态，由于我们的目标是求出最小值，因
     * 此可以将这些状态全部初始化为一个很大的数。在上述的状态转移方程中，一旦我们尝试从不合法的状态 f[k][j-1]进行转移，那么max(⋯) 将会
     * 是一个很大的数，就不会对最外层的 min{⋯} 产生任何影响。
     * 此外，我们还需要将 f[0][0] 的值初始化为 0。在上述的状态转移方程中，当 j=1 时，唯一的可能性就是前 i 个数被分成了一段。如果枚举的
     * k=0，那么就代表着这种情况；如果 k=0，对应的状态 f[k][0]是一个不合法的状态，无法进行转移。因此我们需要令 f[0][0] = 0。
     *
     * 最终的答案即为 f[n][m]。
     *
     * @param nums
     * @param m
     * @return
     */
    public int splitArray( int[] nums, int m ) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        final int n = nums.length;
        int dp[][] = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill( dp[i], Integer.MAX_VALUE );
        }
        
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min( i, m ); j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min( dp[i][j], Math.max( dp[k][j - 1], sub[i] - sub[k] ) );
                }
            }
        }
        
        // for (int i = 0; i < n + 1; i++) {
        //     System.out.println( Arrays.toString( dp[i] ) );
        // }
        return dp[n][m];
    }
    
}

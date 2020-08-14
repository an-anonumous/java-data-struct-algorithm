package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

/**
 * 494. 目标和
 *
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个
 * 符号添加在前面。返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 *
 * 示例：
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 一共有5种方法让最终目标和为3。
 *
 * 提示：
 *
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 */
public class H494 {
    private int conunt;
    
    @Test
    public void test1() {
        Assert.assertEquals( 5, findTargetSumWays( new int[]{1, 1, 1, 1, 1}, 3 ) );
    }
    
    public int findTargetSumWays( int[] nums, int S ) {
        traceBack( nums, 0, 0, S );
        return conunt;
    }
    
    private void traceBack( int[] nums, int index, int sum, int target ) {
        if (index >= nums.length) {
            if (sum == target) {
                conunt++;
            }
            return;
        }
        traceBack( nums, index + 1, sum + nums[index], target );
        traceBack( nums, index + 1, sum - nums[index], target );
    }
    
    /**
     * dp[i][j] 代表前i个元素组成的和为j的个数，从dp[i-1][j]出发可以到达dp[i][j+nums[i]],dp[i][j-nums[i]]
     * dp[i][j + nums[i]] += dp[i - 1][j]
     * dp[i][j - nums[i]] += dp[i - 1][j]
     *
     * @param nums
     * @param S
     * @return
     */
    public int dpFindTargetSumWays( int[] nums, int S ) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = -1000; j <= 1000; j++) {
                if (dp[i - 1][j + 1000] > 0) {
                    dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000];
                    dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }
}

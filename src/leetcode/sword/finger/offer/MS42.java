package leetcode.sword.finger.offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 *
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 *
 * 示例1:
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 提示：
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 */
public class MS42 {
    
    @Test
    public void test() {
        Assert.assertEquals( 6, maxSubArray( new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4} ) );
    }
    
    // dp[i]dp[i] 代表以元素 nums[i]nums[i] 为结尾的连续子数组最大和。
    public int maxSubArray( int[] nums ) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        final int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max( nums[i], nums[i] + dp[i - 1] );
        }
        
        System.out.println( Arrays.toString( dp ) );
        
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }
}

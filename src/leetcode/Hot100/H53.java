package leetcode.Hot100;

import org.junit.Assert;
import org.junit.Test;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class H53 {
    
    @Test
    public void test() {
        H53 h53 = new H53();
        Assert.assertEquals( 6, h53.greedyMaxSubArray( new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4} ) );
        Assert.assertEquals( 6, h53.maxSubArray( new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4} ) );
    }
    
    /**
     *  贪心算法
     *  如果当前元素之前的数组元素中最大子序列和为负数，则舍弃
     *
     * @param nums
     * @return
     */
    public int greedyMaxSubArray( int[] nums ) {
        int max = nums[0], currSum = Integer.MIN_VALUE, preSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            if (preSum > 0) {
                preSum = currSum = preSum + nums[i];
            } else {
                preSum = currSum = nums[i];
            }
            if (currSum > max) {
                max = currSum;
            }
        }
        
        return max;
    }
    
    /**
     * 动态规划
     * dp[k]代表以nums[k]结尾的子数组的最大和
     *
     * @param nums
     * @return
     */
    public int dpMaxSubArray( int[] nums ) {
        // dp[k]代表以nums[k]结尾的子数组的最大和
        int[] dp = new int[nums.length];
        
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }
        
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
    
    /**
     * 分治
     *
     * @param nums
     * @return
     */
    public int maxSubArray( int[] nums ) {
        return dfs( nums, 0, nums.length - 1 ).mSum;
    }
    
    protected State dfs( int[] nums, int left, int right ) {
        State state = new State();
        
        // 递归边界
        if (left == right) {
            state.lSum = nums[left];
            state.rSum = nums[left];
            state.iSum = nums[left];
            state.mSum = nums[left];
            return state;
        }
        
        int mid = ( left + right ) >> 1;
        State lstate = dfs( nums, left, mid );
        State rstate = dfs( nums, mid + 1, right );
        
        state.iSum = lstate.iSum + rstate.iSum;
        state.lSum = lstate.lSum > lstate.iSum + rstate.lSum ? lstate.lSum : lstate.iSum + rstate.lSum;
        state.rSum = rstate.rSum > rstate.iSum + lstate.rSum ? rstate.rSum : rstate.iSum + lstate.rSum;
        
        // [l,r] 的 mSum 对应的区间是否跨越 m——它可能不跨越 m，也就是说 [l,r] 的 mSum 可能是「左子区间」的 mSum和「右子区间」的 mSum 中的一个；
        // 它也可能跨越m，可能是「左子区间」的 rSum 和 「右子区间」的 lSum 求和。三者取大。
        state.mSum = Math.max( Math.max( lstate.mSum, rstate.mSum ), lstate.rSum + rstate.lSum );
        return state;
    }
    
    /**
     * 对于一个区间 [l, r][l,r]，我们可以维护四个量：
     *
     * lSum 表示 [l,r] 内以 l 为左端点的最大子段和
     * rSum 表示 [l,r] 内以 r 为右端点的最大子段和
     * mSum 表示 [l,r] 内的最大子段和
     * iSum 表示 [l,r] 的区间和
     *
     */
    public static class State {
        public int lSum;// 以左区间为端点的最大子段和
        public int rSum;// 以右区间为端点的最大子段和
        public int iSum;// 区间所有数的和
        public int mSum;// 该区间的最大子段和
    }
    
    
}

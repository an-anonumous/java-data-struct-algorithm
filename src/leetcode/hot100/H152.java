package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * 152. 乘积最大子数组
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
 * 并返回该子数组所对应的乘积。
 *
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 */
public class H152 {
    
    protected int max;
    
    @Test
    public void test1() {
        H152 h152 = new H152();
        Assert.assertEquals( 6, h152.maxProduct( new int[]{2, 3, -2, 4} ) );
        Assert.assertEquals( 0, h152.maxProduct( new int[]{-2, 0, -1} ) );
    }
    
    /**
     * 动态规划
     *
     * 如果数组中全为非负数，使用dp[k]代表以nums[k]结尾的子数组的最大积，dp[k]=max(nums[k],num[k]*dp[k-1]),
     * 考虑到数组可能会有负数，负负得正，所以还要一个类似的数组记录最小值。
     *
     * @param nums
     * @return
     */
    public int maxProduct( int[] nums ) {
        int max[] = new int[nums.length];
        int min[] = new int[nums.length];
        
        max[0] = nums[0];
        min[0] = nums[0];
        
        int answer = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            max[i] = Math.max( nums[i], Math.max( max[i - 1] * nums[i], min[i - 1] * nums[i] ) );
            min[i] = Math.min( nums[i], Math.min( max[i - 1] * nums[i], min[i - 1] * nums[i] ) );
            if (max[i] > answer) {
                answer = max[i];
            }
        }
        return answer;
        
    }
    
    @Test
    public void test2() {
        H152 h152 = new H152();
        Assert.assertEquals( 6, h152.dfsMaxProduct( new int[]{2, 3, -2, 4} ) );
        Assert.assertEquals( 0, h152.dfsMaxProduct( new int[]{-2, 0, -1} ) );
        
        Random random = new Random( System.currentTimeMillis() );
        int[] arr = new int[10000];
        for (int i : arr) {
            i = random.nextInt() % 1000;
        }
        Assert.assertEquals( h152.maxProduct( arr ), h152.dfsMaxProduct( arr ) );
    }
    
    protected void dfs( int[] nums, int curr, int product ) {
        if (curr >= nums.length) {
            return;
        }
        
        product *= nums[curr];
        max = Math.max( max, product );
        dfs( nums, curr + 1, product );
    }
    
    /**
     *
     * @param nums
     * @return
     */
    public int dfsMaxProduct( int[] nums ) {
        max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            dfs( nums, i, 1 );
        }
        return max;
    }
}

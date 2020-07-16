package leetcode.Hot100;

import org.junit.Assert;
import org.junit.Test;

/**
 * 300. 最长上升子序列
 *
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n^2) 。
 *
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 */
public class H300 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 4, lengthOfLIS( new int[]{10, 9, 2, 5, 3, 7, 101, 18} ) );
    }
    
    /**
     * 动态规划
     *
     * 使用数组元素dp[k],代表以nums[k]结尾的最长上升子序列长度。从小到大计算 dp[]dp[] 数组的值，在计算 dp[i] 之前，我们已经计算出 dp[0…i−1] 的值，
     * 则状态转移方程为：
     *                  dp[i]=max(dp[j])+1, 其中0≤j<i 且 num[j]<num[i]
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS( int[] nums ) {
        
        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max( dp[i], dp[j] + 1 );
                }
            }
        }
        
        int max = 0;
        for (int i : dp) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }
    
    
}

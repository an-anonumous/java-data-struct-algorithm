package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为K的子数组
 *
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *
 * 说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 */
public class H560 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 2, subarraySum( new int[]{1, 1, 1}, 2 ) );
    }
    
    /**
     *
     * 暴力枚举
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum( int[] nums, int k ) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    ++result;
                }
            }
        }
        return result;
    }
    
    /**
     * 使用数组pre[]记录前缀和，pre[i]代表以[0,i]范围内的数字和；和为k的子数组[i,j]满足pre[j]-pre[i-1]=k,
     * 也就是说以j结尾和为k的子数组个数为前缀和pre[j]-k的数组个数(只考虑j左侧)。
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum1( int[] nums, int k ) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        map.put( 0, 1 ); // 一个都不选的时候前缀和为0，这样的数组有0个，
        
        int pre = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey( pre - k )) {
                count += map.get( pre - k );
            }
            map.put( pre, map.getOrDefault( pre, 0 ) + 1 );
        }
        return count;
    }
    
}

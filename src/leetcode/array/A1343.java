package leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * 1343.
 * 大小为 K 且平均值大于等于阈值的子数组数目给你一个整数数组 arr 和两个整数 k 和 threshold 。请你返回长度为 k 且平均值大于等于 threshold
 * 的子数组数目。
 *
 * 示例 1：
 * 输入：arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
 * 输出：3
 * 解释：子数组 [2,5,5],[5,5,5] 和 [5,5,8] 的平均值分别为 4，5 和 6 。其他长度为 3 的子数组的平均值都小于 4 （threshold 的值)。
 *
 * 示例 2：
 * 输入：arr = [1,1,1,1,1], k = 1, threshold = 0
 * 输出：5
 *
 * 示例 3：
 * 输入：arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
 * 输出：6
 * 解释：前 6 个长度为 3 的子数组平均值都大于 5 。注意平均值不是整数。
 *
 * 示例 4：
 * 输入：arr = [7,7,7,7,7,7,7], k = 7, threshold = 7
 * 输出：1
 *
 * 示例 5：
 * 输入：arr = [4,4,4,4], k = 4, threshold = 1
 * 输出：1
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^4
 * 1 <= k <= arr.length
 * 0 <= threshold <= 10^4
 */
public class A1343 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 3, numOfSubarrays( new int[]{2, 2, 2, 2, 5, 5, 5, 8}, 3, 4 ) );
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 6, numOfSubarrays( new int[]{11, 13, 17, 23, 29, 31, 7, 5, 2, 3}, 3, 5 ) );
    }
    
    public int numOfSubarrays( int[] arr, int k, int threshold ) {
        int count = 0;
        for (int i = 0; i <= arr.length - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += arr[j];
            }
            if (sum >= threshold * k) {
                count++;
            }
        }
        return count;
    }
    
    @Test
    public void test3() {
        Assert.assertEquals( 3, numOfSubarrays1( new int[]{2, 2, 2, 2, 5, 5, 5, 8}, 3, 4 ) );
    }
    
    @Test
    public void test4() {
        Assert.assertEquals( 6, numOfSubarrays1( new int[]{11, 13, 17, 23, 29, 31, 7, 5, 2, 3}, 3, 5 ) );
    }
    
    /**
     *
     * @param arr
     * @param k
     * @param threshold
     * @return
     */
    public int numOfSubarrays1( int[] arr, int k, int threshold ) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int count = 0;
        int dp[] = new int[arr.length + 1];
        dp[0] = 0;
        for (int i = 0; i < arr.length; i++) {
            dp[i + 1] = dp[i] + arr[i];
            if (i + 1 - k >= 0 && dp[i + 1] - dp[i + 1 - k] >= k * threshold) {
                ++count;
            }
        }
        return count;
    }
}

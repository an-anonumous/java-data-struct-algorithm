package leetcode.hot100;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 *496. 下一个更大元素 I
 *
 * 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 *
 * 示例 1:
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 *     对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 *     对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 *     对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 *
 * 示例 2:
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 *     对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 *     对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 *
 * 提示：
 * nums1和nums2中所有元素是唯一的。
 * nums1和nums2 的数组大小都不超过1000。
 *
 */
public class H496 {
    
    /**
     * 我们可以忽略数组 nums1，先对将 nums2 中的每一个元素，求出其下一个更大的元素。随后对于将这些答案放入哈希映射（HashMap）中，再遍
     * 历数组 nums1，并直接找出答案。对于nums2，我们可以使用单调栈来解决这个问题。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement( int[] nums1, int[] nums2 ) {
        if (nums1 == null || nums1.length == 0) {
            return new int[0];
        }
        
        // 存放nums2数组中所有元素的下一个更大的元素
        int temp[] = new int[nums2.length];
        Deque<Integer> stack = new ArrayDeque<>();   // 单调栈，存放还没有找个下一个比他自己大的元素的下标
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                int index = stack.pop();
                temp[index] = nums2[i];
            }
            stack.push( i );
        }
        
        // 处理没有比它大的元素
        while (!stack.isEmpty()) {
            temp[stack.pop()] = -1;
        }
        
        // nums2数组 值 到 下标的映射
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put( nums2[i], i );
        }
        
        int result[] = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = temp[map.get( nums1[i] )];
        }
        return result;
    }
    
    /**
     * 优化一下，直接将nums2中的结果存入map中，然后比那里数组nums1找出
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement2( int[] nums1, int[] nums2 ) {
        if (nums1 == null || nums1.length == 0) {
            return new int[0];
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        // 使用单调栈求解nums2中每一个元素的下一个更大的元素
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                map.put( stack.pop(), nums2[i] );
            }
            stack.push( nums2[i] );
        }
        
        while (!stack.isEmpty()) {
            map.put( stack.pop(), -1 );
        }
        
        int result[] = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get( nums1[i] );
        }
        return result;
    }
}

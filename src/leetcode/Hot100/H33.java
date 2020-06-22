package leetcode.Hot100;

import java.util.Arrays;

/**
 * 33. 搜索旋转排序数组
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 */
public class H33 {
    public int search( int[] nums, int target ) {
        int left = 0, right = nums.length - 1, mid = ( left + right ) / 2;
        
        while (left < right) {
            if (target == nums[mid]) {
                return mid;
            }
            
            if (nums[mid] > nums[right]) {
                // 此时mid在左侧连续递增部分
                if (target < nums[mid]) {
                    // 二分搜索[left+1,mid-1]
                    int ans = Arrays.binarySearch( nums, left + 1, mid - 1 + 1, target );
                    if (ans < 0) {
                        return -1;
                    } else {
                        return ans;
                    }
                } else {
                    left = mid + 1;
                    mid = ( left + right ) / 2;
                }
            } else {
                if (target > nums[mid]) {
                    // 二分搜索 [mid+1,right]
                    int ans = Arrays.binarySearch( nums, mid + 1, right + 1, target );
                    if (ans < 0) {
                        return -1;
                    } else {
                        return ans;
                    }
                } else {
                    right = mid - 1;
                    mid = ( left + right ) / 2;
                }
            }
        }
        
        return -1;
    }
}

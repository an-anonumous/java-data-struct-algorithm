package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

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
    
    @Test
    public void test1() {
        H33 h33 = new H33();
        Assert.assertEquals( 4, h33.search( new int[]{4, 5, 6, 7, 0, 1, 2}, 0 ) );
    }
    
    /**
     * 二分搜索
     *
     * 1）当nums[mid]>nums[left]时，[left,mid]之间是完全有序的，如果 nums[left]<=target>=nums[mid],则二分搜索[left,mid];否则，缩小问题
     * 规模[mid+1,right].
     * 2)但nums[mid]<nums[right]时，[mid,right]之间是完全有序的，如果 nums[mid] <= target >=nums[right],则二分搜索[mid,right];否则，
     * 缩小问题规模到[left,mid-1].
     *
     * @param nums
     * @param target
     * @return
     */
    public int search( int[] nums, int target ) {
        int left = 0, right = nums.length - 1, mid = ( left + right ) / 2;
        
        while (left <= right) {
            if (target == nums[mid]) {
                return mid;
            }
            
            if (nums[mid] > nums[right]) { // [left,mid]是完全有序的
                if (target < nums[mid] && target >= nums[left]) {
                    // 二分搜索[left,mid-1]
                    // 如果target不在数组中，则返回-(target应该插入的下标+1)
                    int ans = Arrays.binarySearch( nums, left, mid - 1 + 1, target );
                    if (ans < 0) {
                        return -1;
                    } else {
                        return ans;
                    }
                } else {
                    left = mid + 1;
                    mid = ( left + right ) / 2;
                }
            } else {    // [mid,right] 是完全有序的
                if (target >= nums[mid] && target <= nums[right]) {
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
    
    // 官方C++参考代码
    // int search(vector<int>& nums, int target) {
    //     int n = (int)nums.size();
    //     if (!n) return -1;
    //     if (n == 1) return nums[0] == target ? 0 : -1;
    //     int l = 0, r = n - 1;
    //     while (l <= r) {
    //         int mid = (l + r) / 2;
    //         if (nums[mid] == target) return mid;
    //         if (nums[0] <= nums[mid]) {
    //             if (nums[0] <= target && target < nums[mid]) {
    //                 r = mid - 1;
    //             } else {
    //                 l = mid + 1;
    //             }
    //         } else {
    //             if (nums[mid] < target && target <= nums[n - 1]) {
    //                 l = mid + 1;
    //             } else {
    //                 r = mid - 1;
    //             }
    //         }
    //     }
    //     return -1;
    // }
    
    
    @Test
    public void test3() {
        H33 h33 = new H33();
        Assert.assertEquals( 1, h33.search( new int[]{1, 3}, 3 ) );
    }
    
    @Test
    public void test2() {
        H33 h33 = new H33();
        Assert.assertEquals( -1, h33.search( new int[]{4, 5, 6, 7, 0, 1, 2}, 3 ) );
    }
}

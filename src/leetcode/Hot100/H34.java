package leetcode.Hot100;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1: *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 */
public class H34 {
    
    /**
     * 先用二分查找找到一个target,然后向两侧扩展
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange( int[] nums, int target ) {
        int index = Arrays.binarySearch( nums, target );
        if (index < 0) {
            return new int[]{-1, -1};
        } else {
            int[] answer = new int[2];
            int i = index - 1;
            while (i >= 0 && nums[i] == nums[i + 1]) {
                --i;
            }
            answer[0] = i + 1;
            
            int j = index + 1;
            while (j < nums.length && nums[j] == nums[j - 1]) {
                j++;
            }
            answer[1] = j - 1;
            return answer;
        }
    }
}

package leetcode.Hot100;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 */
public class H283 {
    
    /**
     * 使用双指针将所有的非零元素移动到数组的左端，后边全部填0
     *
     * @param nums
     */
    public void moveZeroes( int[] nums ) {
        int slow = 0;       // 下一个非零元素存放的索引
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[slow++] = nums[i];
            }
        }
        
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

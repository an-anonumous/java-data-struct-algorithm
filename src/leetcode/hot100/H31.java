package leetcode.hot100;

import java.util.Arrays;

/**
 * 31. 下一个排列
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */
public class H31 {
    
    /**
     * 对于一个完全降序排列的序列，比如：7,6,4,3,1，交换任何两个数字都会使字典序变小。所以要从右向左找第一个瞒住num[i]<num[i-1]的位置i,
     * 比如：5,7,6,4,3,1中的5，然后将num[i]与左侧[i-1,num.length]中大于num[i]的最小数交换，最后将新的[i-1,num.length]序列重新排成
     * 最小排列。
     *
     * @param nums
     */
    public void nextPermutation( int[] nums ) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int i = nums.length - 2;
        for (; i >= 0; i--) {
            if (nums[i] >= nums[i + 1]) {
                continue;
            } else {
                break;
            }
        }
        
        // 所有数字降序排列，么有更大的字典序列
        if (i < 0) {
            Arrays.sort( nums );
            return;
        }
        
        int j = nums.length - 1;
        for (; j > i; j--) {
            if (nums[i] < nums[j]) {
                break;
            }
        }
        
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        
        Arrays.sort( nums, i + 1, nums.length );
    }
}

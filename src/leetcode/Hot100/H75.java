package leetcode.Hot100;

import org.junit.Test;

import java.util.Arrays;

/**
 * 75. 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 *
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 */
public class H75 {
    
    @Test
    public void test() {
        H75 h75 = new H75();
        int[] arr = {2, 0, 2, 1, 1, 0};
        h75.sortColors( arr );
        System.out.println( Arrays.toString( arr ) );
    }
    
    /**
     * 思路：
     * 我们用三个指针（p0, p2 和curr）来分别追踪0的最右边界，2的最左边界和当前考虑的元素。
     * 本解法的思路是沿着数组移动 curr 指针，若nums[curr] = 0，则将其与 nums[p0]互换；若 nums[curr] = 2 ，则与 nums[p2]互换。
     *
     * 算法
     * 初始化0的最右边界：p0 = 0。在整个算法执行过程中 nums[idx < p0] = 0.
     * 初始化2的最左边界 ：p2 = n - 1。在整个算法执行过程中 nums[idx > p2] = 2.
     * 初始化当前考虑的元素序号 ：curr = 0.
     * While curr <= p2 :
     * 若 nums[curr] = 0 ：交换第 curr个 和 第p0个 元素，并将指针都向右移。
     * 若 nums[curr] = 2 ：交换第 curr个和第 p2个元素，并将 p2指针左移 。
     * 若 nums[curr] = 1 ：将指针curr右移。
     *
     * 复杂度分析
     * 时间复杂度 :由于对长度 NN的数组进行了一次遍历，时间复杂度为O(N) 。
     * 空间复杂度 :由于只使用了常数空间，空间复杂度为O(1) 。
     *
     * @param nums
     */
    public void sortColors( int[] nums ) {
        int p0 = 0, p2 = nums.length - 1, temp = 0;
        for (int i = 0; i < nums.length && i <= p2; ) {
            
            if (nums[i] == 0) {
                temp = nums[p0];
                nums[p0] = nums[i];
                nums[i] = temp;
                p0++;
                i++; // 左边已经是有序序列
                
            } else if (nums[i] == 2) {
                temp = nums[p2];
                nums[p2] = nums[i];
                nums[i] = temp;
                p2--;
            } else {
                i++;
            }
            
            // System.out.println( Arrays.toString( nums ) );
            
        }
    }
}

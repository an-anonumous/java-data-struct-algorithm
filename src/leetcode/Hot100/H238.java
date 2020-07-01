package leetcode.Hot100;

import org.junit.Test;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 *
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 */
public class H238 {
    
    @Test
    public void test1() {
        System.out.println( Arrays.toString( productExceptSelf( new int[]{1, 2, 3, 4} ) ) );
    }
    
    /**
     * 初始化两个空数组 L 和 R。对于给定索引 i，L[i] 代表的是 i 左侧所有数字的乘积，R[i] 代表的是 i 右侧所有数字的乘积。
     * 我们需要用两个循环来填充 L 和 R 数组的值。对于数组 L，L[0] 应该是 1，因为第一个元素的左边没有元素。对于其他元素：L[i] = L[i-1] * nums[i-1]。
     * 同理，对于数组 R，R[length-1] 应为 1。length 指的是输入数组的大小。其他元素：R[i] = R[i+1] * nums[i+1]。
     * 当 R 和 L 数组填充完成，我们只需要在输入数组上迭代，且索引 i 处的值为：L[i] * R[i]。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf( int[] nums ) {
        // 用来保存当前元素之前的所有元素乘积
        int[] prefix = new int[nums.length];
        
        // 当前元素之后的所有元素的乘积
        int[] postfix = new int[nums.length];
        
        // 计算前缀
        prefix[0] = 1;
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        
        // 计算后缀
        postfix[nums.length - 1] = 1;
        for (int i = postfix.length - 2; i >= 0; i--) {
            postfix[i] = nums[i + 1] * postfix[i + 1];
        }
        
        // 计算结果
        for (int i = 0; i < postfix.length; i++) {
            prefix[i] *= postfix[i];
        }
        return prefix;
    }
}

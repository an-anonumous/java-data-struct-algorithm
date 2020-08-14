package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 581. 最短无序连续子数组
 *
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。你找到的子数组应是最短的，
 * 请输出它的长度。
 *
 * 示例 1:
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 * 说明 :
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 */
public class H581 {
    @Test
    public void test1() {
        Assert.assertEquals( 5, findUnsortedSubarray( new int[]{2, 6, 4, 8, 10, 9, 15} ) );
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 0, findUnsortedSubarray( new int[]{1, 2, 3, 4, 5} ) );
    }
    
    /**
     * 一个简单的想法是：我们将数组nums进行排序，记为nums_sorted。然后我们比较 nums 和 nums_sorted的元素来决定最左边和最右边不匹配的元素。
     * 它们之间的子数组就是要求的最短无序子数组。
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray( int[] nums ) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int arr[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }
        Arrays.sort( arr );
        int l = 0;
        while (l < nums.length && nums[l] == arr[l]) {
            ++l;
        }
        int r = nums.length - 1;
        while (r >= 0 && nums[r] == arr[r]) {
            --r;
        }
        
        if (l >= r) {
            return 0;
        }
        
        return r - l + 1;
    }
    
    @Test
    public void test3() {
        Assert.assertEquals( 5, findUnsortedSubarray1( new int[]{2, 6, 4, 8, 10, 9, 15} ) );
    }
    
    @Test
    public void test4() {
        Assert.assertEquals( 0, findUnsortedSubarray1( new int[]{1, 2, 3, 4, 5} ) );
    }
    
    @Test
    public void test5() {
        Assert.assertEquals( 2, findUnsortedSubarray1( new int[]{1, 3, 2, 4, 5} ) );
    }
    
    /**
     * 使用栈
     *
     * 核心想法是找到未排序子数组中最大、最小值应该插入的位置
     *
     * 算法:
     * 这个方法背后的想法仍然是选择排序。我们需要找到无序子数组中最小元素和最大元素分别对应的正确位置，来求得我们想要的无序子数组的边界。
     *
     * 为了达到这一目的，此方法中，我们使用 栈 。我们从头遍历 nums数组，如果遇到的数字大小一直是升序的，我们就不断把对应的下标压入栈中，
     * 这么做的目的是因为这些元素在目前都是处于正确的位置上。一旦我们遇到前面的数比后面的数大，也就是 nums[j]比栈顶元素小，我们可以知道
     * nums[j] 一定不在正确的位置上。为了找到 nums[j] 的正确位置，我们不断将栈顶元素弹出，直到栈顶元素比 nums[j] 小，我们假设栈顶元
     * 素对应的下标为 kk ，那么我们知道 nums[j] 的正确位置下标应该是k+1 。我们重复这一过程并遍历完整个数组，这样我们可以找到最小的 k，
     * 它也是无序子数组的左边界。
     *
     * 类似的，我们逆序遍历一遍 nums 数组来找到无序子数组的右边界。这一次我们将降序的元素压入栈中，如果遇到一个升序的元素，我们像上面所述
     * 的方法一样不断将栈顶元素弹出，直到找到一个更大的元素，以此找到无序子数组的右边界。
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray1( int[] nums ) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int l = nums.length - 1, r = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push( 0 );
        for (int i = 1; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                l = Math.min( l, stack.poll() );
            }
            stack.push( i );
        }
        
        if (stack.size() == nums.length) {
            return 0;
        }
        
        stack.clear();
        stack.push( nums.length - 1 );
        for (int i = nums.length - 2; i >= 0; --i) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                r = Math.max( r, stack.pop() );
            }
            stack.push( i );
        }
        return r - l + 1;
    }
}

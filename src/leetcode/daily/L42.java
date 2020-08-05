package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 42. 接雨水
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1]
 * 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 */
public class L42 {
    
    @Test
    public void test1() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        Assert.assertEquals( 6, trap1( height ) );
    }
    
    /**
     * 暴力解法
     *
     * @param height
     * @return
     */
    public int trap1( int[] height ) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        int result = 0;
        
        // 计算每个柱子真上方能存多少雨水，如果当前柱子的左边最高的柱子left ,右边最高的柱子right都比height[i]高，才会在当前柱子上方存储水。
        // 储水的容量为left,right中较小者高度减去当前柱子高度height[i];
        for (int i = 0; i < height.length; i++) {
            int left = 0;
            for (int j = 0; j < i; j++) {
                left = Math.max( left, height[j] );
            }
            
            int right = 0;
            for (int j = i + 1; j < height.length; j++) {
                right = Math.max( right, height[j] );
            }
            
            if (Math.min( left, right ) > height[i]) {
                result += Math.min( left, right ) - height[i];
            }
        }
        return result;
    }
    
    /**
     *
     * 提前计算左右两边的最高柱子高度
     *
     * @param height
     * @return
     */
    public int trap2( int[] height ) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        int left[] = new int[height.length];
        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max( height[i], left[i - 1] );
        }
        
        int right[] = new int[height.length];
        right[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 1 - 1; i >= 0; --i) {
            right[i] = Math.max( height[i], right[i + 1] );
        }
        
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            result += Math.min( left[i], right[i] ) - height[i];
        }
        return result;
    }
    
    /**
     * 单调栈
     *
     * 我们在遍历数组时维护一个栈。如果当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈，意思是当前的条形块被栈中的前一个条形块
     * 界定。如果我们发现一个条形块长于栈顶，我们可以确定栈顶的条形块被当前条形块和栈的前一个条形块界定，因此我们可以弹出栈顶元素并且累加
     * 答案到 ans 。
     *
     * @param height
     * @return
     */
    public int trap3( int[] height ) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        Deque<Integer> stack = new ArrayDeque<Integer>();
        
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int index = stack.pop();
                if (!stack.isEmpty()) {
                    result += ( i - stack.peek() - 1 ) * ( Math.min( height[stack.peek()], height[i] ) - height[index] );
                }
            }
            stack.push( i );
        }
        return result;
    }
}

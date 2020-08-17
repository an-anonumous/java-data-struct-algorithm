package leetcode.sword.finger.offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 30. 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 *
 * 示例:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.min();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.min();   --> 返回 -2.
 *
 * 提示：
 *
 * 各函数的调用总次数不超过 20000 次
 */
class MinStack {
    
    Deque<Integer> stack = new ArrayDeque<>(); // 保存栈中的数据
    Deque<Integer> min = new ArrayDeque<>();   // 每次插入元素后将新的最小值插入到栈顶，删除元素后，将栈顶的最小值一起删除。
    
    /** initialize your data structure here. */
    public MinStack() {
    
    }
    
    public static void main( String[] args ) {
        MinStack minStack = new MinStack();
        minStack.push( -2 );
        minStack.push( 0 );
        minStack.push( -3 );
        minStack.min();
        minStack.pop();
        minStack.top();
        minStack.min();
    }
    
    public void push( int x ) {
        stack.push( x );
        if (min.isEmpty()) {
            min.push( x );
        } else {
            min.push( Math.min( x, min.peek() ) );
        }
    }
    
    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        stack.pop();
        min.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int min() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
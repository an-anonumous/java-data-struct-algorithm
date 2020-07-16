package leetcode.sword.finger.offer;

/**
 * 面试题09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * (若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 *
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 *
 * 提示：
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 */

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */

import java.util.LinkedList;

/**
 * 维护两个栈，第一个栈存储元素，第二个栈用于辅助操作。
 */
public class MS09 {
    // // 不推荐使用java Stack类的原因：首先Stack是继承自Vector而不是内部组合Vector来实现；
    // // 其次就是Stack是线程安全的
    // Stack<Integer> stack1;
    // Stack<Integer> stack2;
    // int size;
    //
    // public ms09() {
    //     stack1 = new Stack<>();
    //     stack2 = new Stack<>();
    //     size = 0;
    // }
    //
    // public void appendTail(int value) {
    //     while (!stack1.empty()) {
    //         stack2.push(stack1.pop());
    //     }
    //     stack1.push(value);
    //     size++;
    //     while (!stack2.empty()) {
    //         stack1.push(stack2.pop());
    //     }
    // }
    //
    // public int deleteHead() {
    //     if (size == 0) {
    //         return -1;
    //     }
    //     size--;
    //     return stack1.pop();
    // }
    
    // 使用LinkedList实现
    int size = 0;
    private LinkedList<Integer> stack1;
    private LinkedList<Integer> stack2;
    
    public MS09() {
        size = 0;
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }
    
    //使用stack1的栈顶作为队列的队首
    public void appendTail(int value) {
        while (!stack1.isEmpty()) {
            stack2.addLast(stack1.removeLast());
        }
        stack1.addLast(value);
        ++size;
    }
    
    public int deleteHead() {
        if (size == 0) {
            return -1;
        }
        
        while (!stack2.isEmpty()) {
            stack1.addLast(stack2.removeLast());
        }
        --size;
        return stack1.removeLast();
    }
}

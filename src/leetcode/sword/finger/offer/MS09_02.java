package leetcode.sword.finger.offer;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 面试题9：用两个栈实现队列
 * 题目：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail
 * 和deleteHead，分别完成在队列尾部插入结点和在队列头部删除结点的功能。 *
 */
public class MS09_02 {
    public static class Stack {
        Queue<Integer> queue1 = new ArrayDeque<>();
        Queue<Integer> queue2 = new ArrayDeque<>();
        
        public void push( int val ) {
            if (!queue1.isEmpty()) {
                queue1.add( val );
            } else {
                queue2.add( val );
            }
        }
        
        public int pop() {
            Queue<Integer> q1 = null, q2 = null;
            if (!queue1.isEmpty()) {
                q1 = queue1;
                q2 = queue2;
            } else {
                q1 = queue2;
                q2 = queue1;
            }
            
            while (q1.size() > 1) {
                q2.add( q1.remove() );
            }
            return q1.poll();
        }
    }
}

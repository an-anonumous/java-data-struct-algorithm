package leetcode.hot100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 234. 回文链表
 *
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 */
public class H234 {
    public boolean isPalindrome( ListNode head ) {
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode node = head;
        while (node != null) {
            stack.push( node.val );
            node = node.next;
        }
        
        node = head;
        while (node != null) {
            if (node.val != stack.pop()) {
                return false;
            }
            node = node.next;
        }
        return true;
    }
    
    private static class ListNode {
        int val;
        ListNode next;
        
        ListNode( int x ) { val = x; }
    }
    
}

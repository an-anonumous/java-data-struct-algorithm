package leetcode.sword.finger.offer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点
 *
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 */
public class Ms22 {
    
    private int index = 0;
    private ListNode node = null;
    
    /**
     * 解法一、双指针
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd1( ListNode head, int k ) {
        if (head == null) {
            return null;
        }
        ListNode first = head, second = head;
        for (int i = 0; i < k; i++) {
            if (first != null) {
                first = first.next;
            }
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        return second;
    }
    
    /**
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd2( ListNode head, int k ) {
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode node = head;
        while (node != null) {
            stack.push( node );
            node = node.next;
        }
        if (stack.size() < k) {
            return null;
        }
        while (k > 1 && !stack.isEmpty()) {
            stack.pop();
            --k;
        }
        return stack.peek();
    }
    
    /**
     * 解法三，递归
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd3( ListNode head, int k ) {
        recusive( head, k );
        return node;
    }
    
    private void recusive( ListNode head, int k ) {
        if (head == null) {
            return;
        }
        recusive( head.next, k );
        ++index;
        if (index == k) {
            node = head;
        }
    }
    
    private static class ListNode {
        int val;
        ListNode next;
        
        ListNode( int x ) { val = x; }
    }
    
}

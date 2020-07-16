package leetcode.hot100;

/**
 * 206. 反转链表
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 */
public class H206 {
    private ListNode root = null;
    
    public ListNode reverseList( ListNode head ) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode pre = head, curr = head.next;
        pre.next = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
    
    public ListNode reverseList2( ListNode head ) {
        recusive( head );
        return root;
    }
    
    private ListNode recusive( ListNode curr ) {
        if (curr == null) {
            return null;
        }
        ListNode node = recusive( curr.next );
        if (node == null) {
            root = curr;
        } else {
            node.next = curr;
        }
        curr.next = null;
        return curr;
    }
    
    protected static class ListNode {
        int val;
        ListNode next;
        
        ListNode( int x ) { val = x; }
    }
    
}

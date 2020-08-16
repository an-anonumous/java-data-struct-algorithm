package leetcode.sword.finger.offer;

/**
 * 剑指 Offer 24. 反转链表
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 */
public class MS24 {
    
    /**
     * 头插法
     *
     * @param head
     * @return
     */
    public ListNode reverseList1( ListNode head ) {
        ListNode H = new ListNode( 0 );
        while (head != null) {
            ListNode next = head.next;
            
            // 头插法
            head.next = H.next;
            H.next = head;
            
            head = next;
        }
        return H.next;
    }
    
    public ListNode reverseList2( ListNode head ) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode node = reverseList2( head.next );
        head.next.next = head;
        head.next = null;
        return node;
    }
    
    private static class ListNode {
        int val;
        ListNode next;
        
        ListNode( int x ) { val = x; }
    }
    
}

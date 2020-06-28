package leetcode.Hot100;

/**
 * 21. 合并两个有序链表
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class H21 {
    
    /**
     * 迭代版
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists( ListNode l1, ListNode l2 ) {
        ListNode head = new ListNode( 0 ), curr = head;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = new ListNode( l1.val );
                curr = curr.next;
                l1 = l1.next;
            } else {
                curr.next = new ListNode( l2.val );
                curr = curr.next;
                l2 = l2.next;
            }
        }
        
        // l1还有剩余节点
        while (l1 != null) {
            curr.next = new ListNode( l1.val );
            curr = curr.next;
            l1 = l1.next;
        }
    
        // l2还有剩余节点
        while (l2 != null) {
            curr.next = new ListNode( l2.val );
            curr = curr.next;
            l2 = l2.next;
        }
    
        return head.next;
    }
    
    /**
     * 递归版
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2( ListNode l1, ListNode l2 ) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists2( l1.next, l2 );
            return l1;
        } else {
            l2.next = mergeTwoLists2( l1, l2.next );
            return l2;
        }
    
    }
    
    protected static class ListNode {
        int val;
        ListNode next;
        
        ListNode( int x ) { val = x; }
    }
}
    

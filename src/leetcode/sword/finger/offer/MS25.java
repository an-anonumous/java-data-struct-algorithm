package leetcode.sword.finger.offer;

/**
 * 剑指 Offer 25. 合并两个排序的链表
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 限制：
 *
 * 0 <= 链表长度 <= 1000
 */
public class MS25 {
    public ListNode mergeTwoLists( ListNode l1, ListNode l2 ) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        
        ListNode head = null, tail = null;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                if (head == null) {
                    head = l1;
                    tail = l1;
                } else {
                    tail.next = l1;
                    tail = l1;
                }
                l1 = l1.next;
            } else {
                if (head == null) {
                    head = l2;
                    tail = l2;
                } else {
                    tail.next = l2;
                    tail = l2;
                }
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }
        return head;
    }
    
    private static class ListNode {
        int val;
        ListNode next;
        
        ListNode( int x ) { val = x; }
    }
}

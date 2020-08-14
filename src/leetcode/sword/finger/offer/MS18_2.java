package leetcode.sword.finger.offer;


import org.junit.Test;

public class MS18_2 {
    
    @Test
    public void test() {
        ListNode Head = new ListNode( 0 );
        
        ListNode node1 = new ListNode( 1 );
        node1.next = Head.next;
        Head.next = node1;
        
        ListNode node2 = new ListNode( 2 );
        node2.next = Head.next;
        Head.next = node2;
        
        ListNode node3 = new ListNode( 2 );
        node3.next = Head.next;
        Head.next = node3.next;
        
        deleteDuplication( Head.next );
        System.out.println( "hello" );
    }
    
    public ListNode deleteDuplication( ListNode pHead ) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        
        ListNode curr = pHead, pre = null;
        while (curr != null) {
            ListNode next = curr.next;
            if (next != null && next.val == curr.val) {
                int val = curr.val;
                while (next != null && next.val == val) {
                    next = next.next;
                }
                if (pre != null) {
                    pre.next = next;
                } else {
                    pHead = next;
                }
                curr = next;
            } else {
                pre = curr;
                curr = next;
            }
        }
        return pHead;
    }
    
    private static class ListNode {
        int val;
        ListNode next = null;
        
        ListNode( int val ) {
            this.val = val;
        }
    }
}

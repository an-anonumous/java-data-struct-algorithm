package leetcode.sword.finger.offer;

public class MS23 {
    public ListNode EntryNodeOfLoop( ListNode pHead ) {
        if (pHead == null) {
            return null;
        }
        
        // 先判断是否有环
        ListNode meeting = null;
        boolean hasLoop = false;
        ListNode fast = pHead;
        ListNode slow = pHead;
        while (fast != null && slow != null) {
            if (fast == slow) {
                hasLoop = true;
                meeting = fast;
                break;
            }
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
            slow = slow.next;
        }
        
        if (!hasLoop) {
            return null;
        }
        
        // 计算环的长度
        int loopLen = 1;
        ListNode p = meeting.next;
        while (p != meeting) {
            ++loopLen;
            p = p.next;
        }
        
        fast = pHead;
        slow = pHead;
        for (int i = 0; i < loopLen; ++i) {
            fast = fast.next;
        }
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    
    private static class ListNode {
        int val;
        ListNode next = null;
        
        ListNode( int val ) {
            this.val = val;
        }
    }
}

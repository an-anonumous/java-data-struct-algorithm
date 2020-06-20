package leetcode.Hot100;

import com.sun.jndi.toolkit.ctx.HeadTail;

/**
 * 19. 删除链表的倒数第N个节点
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 */

// Definition for singly-linked list.
// class ListNode {
//     int val;
//     ListNode next;
//     ListNode(int x) { val = x; }
// }


public class H19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 为了统一删除头结点和普通节点的操作，引入哑结点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode front=dummy,tail=dummy;
        for (int i = 0; i <=n; i++) {
            tail=tail.next;
        }
        
        while (tail!=null){
            tail=tail.next;
            front=front.next;
        }
        
        front.next=front.next.next;//删除倒数第n个节点
        
        return dummy.next;
    }
}

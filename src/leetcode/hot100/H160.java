package leetcode.hot100;

import java.util.HashSet;
import java.util.Set;

/**
 * 160. 相交链表
 *
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 注意： *
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 */
public class H160 {
    /**
     * 方法二: 哈希表法
     *
     * 遍历链表 A 并将每个结点的地址/引用存储在哈希表中。然后检查链表 B 中的每一个结点 bi 是否在哈希表中。若在，则 bi为相交结点。
     *
     * 复杂度分析
     * 时间复杂度 : O(m+n)O(m+n)。
     * 空间复杂度 : O(m) 或 O(n)。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode( ListNode headA, ListNode headB ) {
        // 将链表A中所有的节点存入set中
        Set<ListNode> set = new HashSet<>();
        while (headA != null) {
            set.add( headA );
            headA = headA.next;
        }
        
        while (headB != null) {
            if (set.contains( headB )) {
                return headB;
            }
            headB = headB.next;
        }
        
        return null;
    }
    
    /**
     * 方法三：双指针法
     *
     * 由于相交节点前的两个部分链表长度不一样，分别用lenSubA和lenSubB表示，假设lenSubA比lenSubB大k,我们可以PA让先指向第k个节点,PB指向第0个节点，
     * 然后逐个先后跳，直至PA==PB,或者链表结束。由于相交以后的链表是相同的，也就是链表A比B多k个元素，所以k可以通过链表做长度得到lenA-lenB;
     *
     * 创建两个指针 pA 和 pB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
     * 当 pA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B); 类似的，当 pB 到达链表的尾部时，将它重定位到链表 A 的头结点。
     * 若在某一时刻 pA 和 pB 相遇，则 pA/pB 为相交结点。
     *
     * 想弄清楚为什么这样可行, 可以考虑以下两个链表: A={1,3,5,7,9,11} 和 B={2,4,9,11}，相交于结点 9。 由于 B.length (=4) < A.length (=6)，
     * pB 比 pA 少经过 2个结点，会先到达尾部。将 pB 重定向到 A 的头结点，pA重定向到 B 的头结点后，pB 要比 pA 多走 2 个结点。因此，它们会同时到达交点。
     * 如果两个链表存在相交，它们末尾的结点必然相同。因此当 pA/pB 到达链表结尾时，记录下链表 A/B 对应的元素。若最后元素不相同，则两个链表不相交。
     *
     * 复杂度分析
     * 时间复杂度 : O(m+n)。
     * 空间复杂度 : O(1)。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode2( ListNode headA, ListNode headB ) {
        int lenA = 0, lenB = 0;
        ListNode pA = headA;
        while (pA != null) {
            lenA++;
            pA = pA.next;
        }
        
        ListNode pB = headB;
        while (pB != null) {
            lenB++;
            pB = pB.next;
        }
        
        int k = Math.abs( lenA - lenB );
        if (lenA > lenB) {
            for (int i = 0; i < k; i++) {
                headA = headA.next;
            }
        } else {
            for (int i = 0; i < k; i++) {
                headB = headB.next;
            }
        }
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
    
    protected class ListNode {
        int val;
        ListNode next;
        
        ListNode( int x ) {
            val = x;
            next = null;
        }
    }
    
}

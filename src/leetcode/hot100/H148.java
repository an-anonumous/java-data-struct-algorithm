package leetcode.hot100;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * 148. 排序链表
 *
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 */
public class H148 {
    
    @Test
    public void Test1() {
        ListNode head = new ListNode( 0 ), tail = head;
        tail.next = new ListNode( 4 );
        tail = tail.next;
        tail.next = new ListNode( 2 );
        tail = tail.next;
        tail.next = new ListNode( 1 );
        tail = tail.next;
        tail.next = new ListNode( 3 );
        tail = tail.next;
        H148 h148 = new H148();
        
        System.out.println( h148.quickSortList( head.next ) );
    }
    
    @Test
    public void Test2() {
        ListNode head = new ListNode( 0 ), tail = head;
        tail.next = new ListNode( -1 );
        tail = tail.next;
        tail.next = new ListNode( 5 );
        tail = tail.next;
        tail.next = new ListNode( 4 );
        tail = tail.next;
        tail.next = new ListNode( 3 );
        tail = tail.next;
        tail.next = new ListNode( 0 );
        tail = tail.next;
        
        H148 h148 = new H148();
        System.out.println( h148.quickSortList( head.next ) );
    }
    
    
    /**
     * 快速排序
     *
     * @param head
     * @return
     */
    public ListNode quickSortList( ListNode head ) {
        // 递归结束条件
        if (head == null || head.next == null) {
            return head;
        }
        
        // 划分
        ListNode base = head, left = new ListNode( 0 ), right = new ListNode( 0 );
        head = head.next;
        while (head != null) {
            if (head.val < base.val) {
                ListNode next = head.next;
                
                // 头插法插入head节点
                head.next = left.next;
                left.next = head;
                
                head = next;
            } else {
                ListNode next = head.next;
                
                head.next = right.next;
                right.next = head;
                
                head = next;
            }
        }
        
        // 右边半部分
        base.next = quickSortList( right.next );
        
        // 左边
        ListNode Lhead = quickSortList( left.next );
        if (Lhead == null) {
            return base;
        }
        ListNode tail = Lhead;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = base;
        return Lhead;
    }
    
    
    @Test
    public void Test3() {
        ListNode head = new ListNode( 0 ), tail = head;
        tail.next = new ListNode( 4 );
        tail = tail.next;
        tail.next = new ListNode( 2 );
        tail = tail.next;
        tail.next = new ListNode( 1 );
        tail = tail.next;
        tail.next = new ListNode( 3 );
        tail = tail.next;
        H148 h148 = new H148();
        
        System.out.println( h148.mergeSortList( head.next ) );
    }
    
    /**
     * 归并排序
     *
     * 空间复杂度不符合题目O(1)的要求
     *
     * @param head
     * @return
     */
    public ListNode mergeSortList( ListNode head ) {
        Deque<ListNode> stack = new ArrayDeque<>( 128 );
        ListNode curr = head;
        while (curr != null) {
            stack.push( curr );
            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }
        
        if (stack.size() <= 1) {
            return head;
        }
        
        while (stack.size() > 1) {
            stack.push( merge( stack.pop(), stack.pop() ) );
        }
        
        return stack.pop();
    }
    
    /**
     * 归并两个升序排列的链表，归并后返回一个新的升序链表
     *
     * @param left
     * @param right
     * @return
     */
    protected ListNode merge( ListNode left, ListNode right ) {
        ListNode head = new ListNode( 0 ), tail = head;
        
        while (left != null && right != null) {
            if (left.val < right.val) {
                tail.next = left;
                tail = tail.next;
                left = left.next;
            } else {
                tail.next = right;
                tail = tail.next;
                right = right.next;
            }
        }
        
        if (left != null) {
            tail.next = left;
        }
        
        if (right != null) {
            tail.next = right;
        }
        return head.next;
    }
    
    @Test
    public void Test4() {
        ListNode head = new ListNode( 0 ), tail = head;
        tail.next = new ListNode( -1 );
        tail = tail.next;
        tail.next = new ListNode( 5 );
        tail = tail.next;
        tail.next = new ListNode( 4 );
        tail = tail.next;
        tail.next = new ListNode( 3 );
        tail = tail.next;
        tail.next = new ListNode( 0 );
        tail = tail.next;
        
        H148 h148 = new H148();
        System.out.println( h148.mergeSortList( head.next ) );
    }
    
    @Test
    public void Test5() {
        ListNode head = new ListNode( 0 ), tail = head;
        tail.next = new ListNode( 1 );
        tail = tail.next;
        
        H148 h148 = new H148();
        System.out.println( h148.mergeSortList( head.next ) );
    }
    
    
    @Test
    public void Test6() {
        ListNode head = new ListNode( 0 ), tail = head;
        tail.next = new ListNode( 4 );
        tail = tail.next;
        tail.next = new ListNode( 2 );
        tail = tail.next;
        tail.next = new ListNode( 1 );
        tail = tail.next;
        tail.next = new ListNode( 3 );
        tail = tail.next;
        H148 h148 = new H148();
        
        System.out.println( h148.recursiveMergeSortList( head.next ) );
    }
    
    @Test
    public void Test7() {
        ListNode head = new ListNode( 0 ), tail = head;
        tail.next = new ListNode( -1 );
        tail = tail.next;
        tail.next = new ListNode( 5 );
        tail = tail.next;
        tail.next = new ListNode( 4 );
        tail = tail.next;
        tail.next = new ListNode( 3 );
        tail = tail.next;
        tail.next = new ListNode( 0 );
        tail = tail.next;
        
        H148 h148 = new H148();
        System.out.println( h148.recursiveMergeSortList( head.next ) );
    }
    
    @Test
    public void Test8() {
        ListNode head = new ListNode( 0 ), tail = head;
        tail.next = new ListNode( 1 );
        tail = tail.next;
        
        H148 h148 = new H148();
        System.out.println( h148.recursiveMergeSortList( head.next ) );
    }
    
    /**
     *  递归版归并排序
     *
     *  通过递归实现链表归并排序，有以下两个环节：
     *
     * 分割 cut 环节：
     *
     * 找到当前链表中点，并从中点将链表断开（以便在下次递归 cut 时，链表片段拥有正确边界）；
     * 我们使用 fast,slow 快慢双指针法，奇数个节点找到中点，偶数个节点找到中心左边的节点。
     * 找到中点 slow 后，执行 slow.next = None 将链表切断。
     *
     * 递归分割时，输入当前链表左端点 head 和中心节点 slow 的下一个节点 tmp(因为链表是从 slow 切断的)。
     *
     * cut 递归终止条件： 当head.next == None时，说明只有一个节点了，直接返回此节点。
     *
     *
     * 合并 merge 环节：
     * 将两个排序链表合并，转化为一个排序链表。
     *
     * @param head
     * @return
     */
    public ListNode recursiveMergeSortList( ListNode head ) {
        // 递归终止条件
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode slow = head, faster = slow.next;// 防止只有两个节点时死循环
        while (faster != null && faster.next != null) {
            slow = slow.next;
            faster = faster.next.next;
        }
        
        ListNode left = head, right = slow.next;
        slow.next = null;                       // 从中间断开
        left = recursiveMergeSortList( left );
        right = recursiveMergeSortList( right );
        
        return merge( left, right );
    }
    
    @Test
    public void Test9() {
        ListNode head = new ListNode( 0 ), tail = head;
        tail.next = new ListNode( -1 );
        tail = tail.next;
        tail.next = new ListNode( 5 );
        tail = tail.next;
        tail.next = new ListNode( 4 );
        tail = tail.next;
        tail.next = new ListNode( 3 );
        tail = tail.next;
        tail.next = new ListNode( 0 );
        tail = tail.next;
        
        H148 h148 = new H148();
        System.out.println( h148.iterateMergeSortList( head.next ) );
    }
    
    /**
     * 迭代版归并排序
     *
     * @param head
     * @return
     */
    public ListNode iterateMergeSortList( ListNode head ) {
        // 计算当前链表的长度
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            len++;
        }
        
        if (len <= 1) {
            return head;
        }
        
        // 记录结果链表的指针
        ListNode res = null;
        
        curr = head;
        for (int d = 1; d < len; d *= 2) {
            
            ListNode first = new ListNode( 0 ), last = first;
            
            while (curr != null) {
                // left,right用来记录要归并的两段链表
                ListNode left = null, right = null;
                
                left = curr;
                for (int i = 1; i < d && curr != null; i++) {
                    curr = curr.next;
                }
                
                if (curr == null) {
                    last.next = left;
                    // first.next = merge( first.next, left );
                    continue;
                }
                
                right = curr.next;
                curr.next = null;//断开
                curr = right;
                for (int i = 1; i < d && curr != null; i++) {
                    curr = curr.next;
                }
                
                if (curr != null) {
                    ListNode next = curr.next;
                    curr.next = null;
                    curr = next;
                }
                
                // 将上面取出来的两段归并
                last.next = merge( left, right );
                
                // 尾插法，将链表重新连接起来
                while (last != null && last.next != null) {
                    last = last.next;
                }
            }
            
            res = first.next;
            curr = first.next;
            first.next = null;
            last = first;
        }
        return res;
    }
    
    @Test
    public void Test11() {
        ListNode head = new ListNode( 0 ), tail = head;
        tail.next = new ListNode( 1 );
        tail = tail.next;
        
        H148 h148 = new H148();
        System.out.println( h148.iterateMergeSortList( head.next ) );
    }
    
    @Test
    public void Test12() {
        ListNode head = new ListNode( 0 ), tail = head;
        Random random = new Random( System.currentTimeMillis() );
        for (int i = 0; i < 100; i++) {
            tail.next = new ListNode( random.nextInt() % 100 );
            tail = tail.next;
        }
        
        H148 h148 = new H148();
        System.out.println( h148.iterateMergeSortList( head.next ) );
    }
    
    @Test
    public void Test10() {
        ListNode head = new ListNode( 0 ), tail = head;
        
        tail.next = new ListNode( -1 );
        tail = tail.next;
        tail.next = new ListNode( 5 );
        tail = tail.next;
        tail.next = new ListNode( 3 );
        tail = tail.next;
        tail.next = new ListNode( 4 );
        tail = tail.next;
        tail.next = new ListNode( 0 );
        tail = tail.next;
        
        H148 h148 = new H148();
        System.out.println( h148.iterateMergeSortList( head.next ) );
    }
    
    protected static class ListNode {
        int val;
        ListNode next;
        
        ListNode( int x ) { val = x; }
        
        @Override
        public String toString() {
            return "ListNode{" +
                           "val=" + val +
                           ", next=" + next +
                           '}';
        }
    }
}

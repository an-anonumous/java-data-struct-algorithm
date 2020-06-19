package leetcode.SwordFingerOffer;

import java.util.Arrays;

/**
 * 面试题06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *
 * 限制：
 * 0 <= 链表长度 <= 10000
 */

// Definition for singly-linked list.

class ListNode {
    public int val;
    public ListNode next;
    
    public ListNode(int x) {
        val = x;
    }
}

public class MS06 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(2);
        
        System.out.println(Arrays.toString(new MS06().reversePrint(head)));
    }
    
    /**
     * 先遍历单链表将元素依次放入栈中，再从栈顶依次弹出栈顶元素放入数组
     *
     * @param head
     * @return
     */
    // public int[] reversePrint(ListNode head) {
    //     List<Integer> stack = new ArrayList<>(128);
    //     while (head != null) {
    //         stack.add(head.val);
    //         head = head.next;
    //     }
    //     if (stack.size() == 0) {
    //         return new int[0];
    //     }
    //     int[] result = new int[stack.size()];
    //     for (int i = 0; i < result.length; i++) {
    //         result[i] = stack.get(stack.size() - 1 - i);
    //     }
    //     return result;
    // }
    
    /**
     * 两遍遍历，第一次遍历求出元素个数，第二次遍历将元素逆序放入数组。
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            ++len;
            curr = curr.next;
        }
        
        int[] arr = new int[len];
        
        curr = head;
        --len;
        while (curr != null) {
            arr[len] = curr.val;
            curr = curr.next;
            --len;
        }
        
        return arr;
    }
}

package leetcode.daily;

import java.util.*;

/**
 * 99. 恢复二叉搜索树
 *
 * 二叉搜索树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 *
 * 示例 1:
 * 输入: [1,3,null,null,2]
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * 输出: [3,1,null,null,2]
 *    3
 *   /
 *  1
 *   \
 *    2
 *
 * 示例 2:
 * 输入: [3,1,4,null,null,2]
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * 输出: [2,1,4,null,null,3]
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 *
 * 进阶:
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 *
 */
public class L99 {
    
    /**
     * https://leetcode-cn.com/problems/recover-binary-search-tree/solution/hui-fu-er-cha-sou-suo-shu-by-leetcode
     * -solution/
     *
     * @param root
     */
    public void recoverTree( TreeNode root ) {
        if (root == null) {
            return;
        }
        
        List<Integer> list = new LinkedList<>();
        Map<Integer, TreeNode> map = new HashMap<>();
        inorder( root, list, map );
        
        int indexs[] = findReversed( list );
        TreeNode a = map.get( indexs[0] );
        TreeNode b = map.get( indexs[1] );
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
        
    }
    
    /**
     * 中序遍历二叉搜索树，遍历结果存放到list中，并且将list中第i个元素的应用保存到map中
     *
     * @param root
     * @param list
     * @param map
     */
    private void inorder( TreeNode root, List<Integer> list, Map<Integer, TreeNode> map ) {
        if (root == null) {
            return;
        }
        
        inorder( root.left, list, map );
        list.add( root.val );
        map.put( list.size() - 1, root );
        inorder( root.right, list, map );
    }
    
    /**
     * 找出需要交换的两个数字子list中的下标
     *
     * 有两种可能，逆序的两个数相邻或者不相邻
     *
     * @param list
     * @return
     */
    private int[] findReversed( List<Integer> list ) {
        int x = -1, y = -1;
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get( i + 1 ) < list.get( i )) {
                if (y == -1) {
                    x = i;
                    y = i + 1;
                } else {
                    y = i + 1;
                }
            }
        }
        return new int[]{x, y};
    }
    
    /**
     * 方法二：隐式中序遍历
     *
     * 思路与算法
     * 方法一是显式地将中序遍历的值序列保存在一个 nums 数组中，然后再去寻找被错误交换的节点，但我们也可以隐式地在中序遍历的过程就找到被错误交换
     * 的节点 x 和 y。具体来说，由于我们只关心中序遍历的值序列中每个相邻的位置的大小关系是否满足条件，且错误交换后最多两个位置不满足条件，因此
     * 在中序遍历的过程我们只需要维护当前中序遍历到的最后一个节点 pred ，然后在遍历到下一个节点的时候，看两个节点的值是否满足前者小于后者即可，
     * 如果不满足说明找到了一个交换的节点，且在找到两次以后就可以终止遍历。
     *
     * 这样我们就可以在中序遍历中直接找到被错误交换的两个节点 x 和 y，不用显式建立nums 数组。
     *
     * 中序遍历的实现有迭代和递归两种等价的写法，在本方法中提供迭代实现的写法。使用迭代实现中序遍历需要手动维护栈。
     *
     * @param root
     */
    public void recoverTree1( TreeNode root ) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        
        TreeNode curr = root;
        TreeNode pre = null, x = null, y = null;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push( curr );
                curr = curr.left;
            }
            
            // 访问
            curr = stack.pop();
            if (pre != null && curr.val < pre.val) {
                if (y == null) {
                    x = pre;
                    y = curr;
                } else {
                    y = curr;
                    break;
                }
            }
            pre = curr;
            
            curr = curr.right;
        }
        
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
        
    }
    
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode() {}
        
        TreeNode( int val ) { this.val = val; }
        
        TreeNode( int val, TreeNode left, TreeNode right ) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

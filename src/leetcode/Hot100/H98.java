package leetcode.Hot100;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * 假设一个二叉搜索树具有如下特征：
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 */


public class H98 {
    
    /**
     * 解法一
     *
     * @param root
     * @return
     */
    public boolean isValidBST1( TreeNode root ) {
        return inOrderRecusive( root, null, null );
    }
    
    /**
     * 函数表示考虑以 root 为根的子树，判断子树中所有节点的值是否都在 (l,r) 的范围内（注意是开区间）。如果 root 节点的值 val 不在 (l,r)
     * 的范围内说明不满足条件直接返回，否则我们要继续递归调用检查它的左右子树是否满足，如果都满足才说明这是一棵二叉搜索树。
     *
     * 那么根据二叉搜索树的性质，在递归调用左子树时，我们需要把上界 upper 改为 root.val，即调用 inOrderRecusive(root.left, lower, root.val)
     * ，因为左子树里所有节点的值均小于它的根节点的值。同理递归调用右子树时，我们需要把下界 lower 改为 root.val，即调用 inOrderRecusive(root.right,
     * root.val, upper)。
     *
     * 函数递归调用的入口为 inOrderRecusive(root, -inf, +inf)， inf 表示一个无穷大的值。
     *
     * @param root
     * @param lower
     * @param higher
     * @return
     */
    protected boolean inOrderRecusive( TreeNode root, Integer lower, Integer higher ) {
        if (root == null) {
            return true;
        }
        
        if (lower != null && root.val <= lower) {
            return false;
        }
        
        if (higher != null && root.val >= higher) {
            return false;
        }
        
        if (!inOrderRecusive( root.left, lower, root.val )) {
            return false;
        }
        
        return inOrderRecusive( root.right, root.val, higher );
    }
    
    /**
     * 二叉搜索树「中序遍历」得到的值构成的序列一定是升序的，这启示我们在中序遍历的时候实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可。
     * 如果均大于说明这个序列是升序的，整棵树是二叉搜索树，否则不是.
     *
     * @param root
     * @return
     */
    public boolean isValidBST2( TreeNode root ) {
        Integer pre = null; // 中序遍历序列中上一个元素
        
        // 中序非递归遍历
        Deque<TreeNode> stack = new ArrayDeque();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push( root );
                root = root.left;
            }
            root = stack.pop();
            // System.out.println( root.val );
            if (pre == null) {
                pre = root.val;
            } else {
                if (pre >= root.val) {
                    return false;
                }
                pre = root.val;
            }
            root = root.right;
        }
        
        return true;
    }
    
    // Definition for a binary tree node.
    protected static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
}

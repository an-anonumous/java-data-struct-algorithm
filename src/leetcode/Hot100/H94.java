package leetcode.Hot100;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class H94 {
    protected void recusive( TreeNode root, List<Integer> result ) {
        if (root == null) {
            return;
        }
        
        // 左子树
        recusive( root.left, result );
        
        // 根
        result.add( root.val );
        
        // 右子树
        recusive( root.right, result );
    }
    
    /**
     * 递归算法
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal( TreeNode root ) {
        List<Integer> result = new LinkedList<>();
        recusive( root, result );
        return result;
    }
    
    public List<Integer> InorderTraversal2( TreeNode root ) {
        List<Integer> result = new LinkedList<>();
        
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push( root );
                root = root.left;
            }
            root = stack.pop();
            result.add( root.val );
            root = root.right;
        }
        return result;
    }
    
    // Definition for a binary tree node.
    protected static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
}






package leetcode.hot100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. 翻转二叉树
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 * 输入：
 *           4
 *         /   \
 *        2     7
 *       / \   / \
 *      1   3 6   9
 *
 * 输出：
 *           4
 *         /   \
 *        7     2
 *       / \   / \
 *      9   6 3   1
 *
 */
public class H226 {
    
    /**
     * 后续遍历
     *
     * @param root
     * @return
     */
    public TreeNode invertTree( TreeNode root ) {
        if (root == null) {
            return null;
        }
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        root.left = invertTree( right );
        root.right = invertTree( left );
        
        return root;
    }
    
    /**
     * 层次遍历
     *
     * @param root
     * @return
     */
    public TreeNode levelInvertTree( TreeNode root ) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add( root );
        }
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            
            TreeNode right = curr.right;
            TreeNode left = curr.left;
            
            if (right != null) {
                queue.add( right );
            }
            if (left != null) {
                queue.add( left );
            }
            
            curr.left = right;
            curr.right = left;
        }
        return root;
    }
    
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
    
}

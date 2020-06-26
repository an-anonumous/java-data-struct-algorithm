package leetcode.Hot100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 */
public class H101 {
    
    /**
     * 递归
     *
     * @param root
     * @return
     */
    public boolean dfsIsSymmetric( TreeNode root ) {
        if (root == null) {
            return true;
        }
        return preOrderTraverseDFS( root.left, root.right );
    }
    
    protected boolean preOrderTraverseDFS( TreeNode l, TreeNode r ) {
        if (l == null && r == null) {
            return true;
        }
        
        if (l == null || r == null) {
            return false;
        }
        
        return l.val == r.val && preOrderTraverseDFS( l.left, r.right ) && preOrderTraverseDFS( l.right, r.left );
    }
    
    public boolean bfsIsSymmetric( TreeNode root ) {
        if (root == null) {
            return true;
        }
        
        // bfs
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( root.left );
        queue.offer( root.right );
        
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            
            if (node1 == null && node2 == null) {
                continue;
            }
            
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            
            queue.offer( node1.left );
            queue.offer( node2.right );
            
            queue.offer( node1.right );
            queue.offer( node2.left );
            
        }
        
        return true;
    }
    
    protected static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
}

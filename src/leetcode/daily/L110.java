package leetcode.daily;

/**
 * 110. 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 */
public class L110 {
    public boolean isBalanced( TreeNode root ) {
        return height( root ) >= 0;
    }
    
    /**
     * 如果左子树和右子树都是平衡二叉树，且左右子树高度差不超过1，则从当前节点出发的二叉树也是平衡二叉树，返回其高度，否则返回-1。
     *
     * @param root
     * @return
     */
    private int height( TreeNode root ) {
        if (root == null) {
            return 0;
        }
        int l = height( root.left );
        int r = height( root.right );
        if (l == -1 || r == -1 || Math.abs( l - r ) > 1) {
            return -1;
        }
        return Math.max( l, r ) + 1;
        
    }
    
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
}

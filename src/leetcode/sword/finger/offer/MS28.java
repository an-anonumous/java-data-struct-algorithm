package leetcode.sword.finger.offer;

/**
 * 剑指 Offer 28. 对称的二叉树
 *
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * 限制：
 * 0 <= 节点个数 <= 1000
 */
public class MS28 {
    public boolean isSymmetric( TreeNode root ) {
        return isSymmetric( root, root );
    }
    
    public boolean isSymmetric( TreeNode right, TreeNode left ) {
        if (right == null && left == null) {
            return true;
        }
        if (right == null || left == null) {
            return false;
        }
        if (right.val != left.val) {
            return false;
        }
        return isSymmetric( left.left, right.right ) && isSymmetric( left.right, right.left );
    }
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
}

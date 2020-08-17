package leetcode.sword.finger.offer;

/**
 * 剑指 Offer 26. 树的子结构
 *
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构) B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *
 * 给定的树 B：
 *    4
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 *
 * 示例 2：
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 *
 * 限制：
 * 0 <= 节点个数 <= 10000
 */
public class MS26 {
    public boolean isSubStructure( TreeNode A, TreeNode B ) {
        boolean result = false;
        if (A != null && B != null) {
            if (A.val == B.val) {
                result = check( A, B );
            }
            if (!result) {
                result = isSubStructure( A.left, B );
            }
            if (!result) {
                result = isSubStructure( A.right, B );
            }
        }
        return result;
    }
    
    /**
     *
     * @param root1
     * @param root2
     * @return
     */
    private boolean check( TreeNode root1, TreeNode root2 ) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return check( root1.left, root2.left ) && check( root1.right, root2.right );
    }
    
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
}

package leetcode.Hot100;

/**
 * 104. 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 */
public class H104 {
    
    /**
     * DFS
     * 先求出左右子树的高度，然后去较大者+1
     *
     * @param root
     * @return
     */
    public int maxDepth( TreeNode root ) {
        if (root == null) {
            return 0;
        }
        return Math.max( maxDepth( root.left ), maxDepth( root.right ) ) + 1;
    }
    
    public int maxDepth2( TreeNode root ) {
        int[] max = new int[1];
        dfs2( root, 1, max );
        return max[0];
    }
    
    /**
     *
     * @param root      当前节点
     * @param level     当前节点的深度
     * @param max       已知的树的最大深度
     */
    protected void dfs2( TreeNode root, int level, int[] max ) {
        if (root == null) {
            return;
        }
        
        if (level > max[0]) {
            max[0] = level;
        }
        
        dfs2( root.left, level + 1, max );
        dfs2( root.right, level + 1, max );
    }
    
    protected static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
}

package leetcode.hot100;

/**
 * 543. 二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 */
public class H543 {
    private int Max = 0;
    
    /**
     * 而任意一条路径均可以被看作由某个节点为起点，从其左儿子和右儿子向下遍历的路径拼接得到。假设我们知道对于该节点的左儿子向下遍历经过最多的
     * 节点数 L （即以左儿子为根的子树的深度） 和其右儿子向下遍历经过最多的节点数 R（即以右儿子为根的子树的深度），那么以该节点为起点的路径
     * 经过节点数的最大值即为 L+R+1。
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree( TreeNode root ) {
        depth( root );
        return Max;
    }
    
    /**
     * 计算以root为根节点的二叉树的深度
     *
     * @param root
     * @return
     */
    private int depth( TreeNode root ) {
        if (root == null) {
            return 0;
        }
        int L = depth( root.left );
        int R = depth( root.right );
        
        if (L + R > Max) {
            Max = L + R;
        }
        return Math.max( L, R ) + 1;
    }
    
    
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
    
}

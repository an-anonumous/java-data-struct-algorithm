package leetcode.Hot100;

/**
 * 114. 二叉树展开为链表
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class H114 {
    
    protected TreeNode pre = new TreeNode();
    
    /**
     * 使用先序遍历来构建单链表
     *
     * @param root
     */
    public void recusiveFlatten( TreeNode root ) {
        if (root == null) {
            return;
        }
        
        // 先处理根节点
        pre.right = root;
        pre = pre.right;
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        
        // 左子树
        recusiveFlatten( left );
        
        // 右子树
        recusiveFlatten( right );
    }
    
    /*************二叉树的先序遍历非递归版*************/
    // public static void preOrderStack(TreeNode root) {
    //     if (root == null) {
    //         return;
    //     }
    //     Stack<TreeNode> s = new Stack<TreeNode>();
    //     while (root != null || !s.isEmpty()) {
    //         while (root != null) {
    //             System.out.println(root.val);
    //             s.push(root);
    //             root = root.left;
    //         }
    //         root = s.pop();
    //         root = root.right;
    //     }
    // }
    
    /**
     * 可以发现展开的顺序其实就是二叉树的先序遍历。     *
     *
     * 1.将左子树插入到右子树的地方
     * 2.将原来的右子树接到左子树的最右边节点
     * 3.考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
     *
     * 可以看图理解下这个过程。
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     *
     * //将 1 的左子树插入到右子树的地方
     *     1
     *      \
     *       2         5
     *      / \         \
     *     3   4         6
     * //将原来的右子树接到左子树的最右边节点
     *     1
     *      \
     *       2
     *      / \
     *     3   4
     *          \
     *           5
     *            \
     *             6
     *
     *  //将 2 的左子树插入到右子树的地方
     *     1
     *      \
     *       2
     *        \
     *         3       4
     *                  \
     *                   5
     *                    \
     *                     6
     *
     *  //将原来的右子树接到左子树的最右边节点
     *     1
     *      \
     *       2
     *        \
     *         3
     *          \
     *           4
     *            \
     *             5
     *              \
     *               6
     *
     *   ......
     */
    public void flatten( TreeNode root ) {
        while (root != null) {
            if (root.left == null) {
                // 下一个
                root = root.right;
            } else {
                
                TreeNode left = root.left;
                TreeNode right = root.right;
                root.left = null;
                
                // 将左子树插入到右子树的地方
                root.right = left;
                
                // 寻找左子树最右下节点
                while (left.right != null) {
                    left = left.right;
                }
                
                // 将原来的右子树接到左子树的最右边节点
                left.right = right;
                
                // 下一个
                root = root.right;
            }
        }
        
    }
    
    protected static class TreeNode {
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

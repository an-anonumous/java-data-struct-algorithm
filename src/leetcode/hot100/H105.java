package leetcode.hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class H105 {
    
    /**
     * 先序遍历:    [ 根节点, [左子树的前序遍历结果], [右子树的前序遍历结果] ]
     * 中序遍历:    [ [左子树的中序遍历结果], 根节点, [右子树的中序遍历结果] ]
     *
     * 只要我们在中序遍历中定位到根节点，那么我们就可以分别知道左子树和右子树中的节点数目。由于同一颗子树的前序遍历和中序遍历的长度显然是相同的，
     * 因此我们就可以对应到前序遍历的结果中，对上述形式中的所有左右括号进行定位。
     *
     * 这样以来，我们就知道了左子树的前序遍历和中序遍历结果，以及右子树的前序遍历和中序遍历结果，我们就可以递归地对构造出左子树和右子树，
     * 再将这两颗子树接到根节点的左右位置。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree( int[] preorder, int[] inorder ) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put( inorder[i], i );
        }
        return recusiveBuildTree( preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map );
    }
    
    protected TreeNode recusiveBuildTree( int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd,
                                          Map<Integer, Integer> map ) {
        if (preStart > preEnd) {
            return null;
        }
        
        // 构建当前节点
        TreeNode root = new TreeNode( preorder[preStart] );
        
        if (map.containsKey( preorder[preStart] )) {
            
            // 查找前序遍历的第一个节点，也就是根节点在中序序列中的下标；
            int rootIndex = map.get( preorder[preStart] );
            
            int leftLen = rootIndex - inStart;
            int rightLen = inEnd - rootIndex;
            
            root.left = recusiveBuildTree( preorder, preStart + 1, preStart + leftLen,
                    inorder, inStart, rootIndex - 1, map );
            root.right = recusiveBuildTree( preorder, preStart + leftLen + 1, preEnd,
                    inorder, rootIndex + 1, inEnd, map );
            
        }
        return root;
    }
    
    protected static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
}

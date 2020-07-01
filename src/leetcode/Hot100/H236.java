package leetcode.Hot100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 236. 二叉树的最近公共祖先
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 */
public class H236 {
    
    public TreeNode lowestCommonAncestor( TreeNode root, TreeNode p, TreeNode q ) {
        TreeNode[] answer = new TreeNode[1];
        if (postOrder( root, p, q, answer )) {
            return answer[0];
        }
        return null;
    }
    
    /**
     * 通过递归后续遍历来寻找最近公共祖先。如果以当前节点为根的子树中包含有p,q则返回true。
     * 以下两种情况下，当前节点就是要找的最近公共祖先：
     *      1)左子树和右子树都返回true;
     *      2)左子树或者右子树有一个返回true,并且当前节点和p、q中的一个是相同的。
     *
     * @param curr
     * @param p
     * @param q
     * @param answer
     * @return
     */
    private boolean postOrder( TreeNode curr, TreeNode p, TreeNode q, TreeNode[] answer ) {
        if (curr == null) {
            return false;
        }
        
        boolean a = postOrder( curr.left, p, q, answer );
        boolean b = postOrder( curr.right, p, q, answer );
        
        if (( a && b ) || ( ( a || b ) && ( p.val == curr.val || q.val == curr.val ) )) {
            answer[0] = curr;
        }
        
        return a || b || ( p.val == curr.val || q.val == curr.val );
    }
    
    /**
     * 方法二：存储父节点
     *
     * 思路
     * 我们可以用哈希表存储所有节点的父节点，然后我们就可以利用节点的父节点信息从 p 结点开始不断往上跳，并记录已经访问过的节点，
     * 再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，那么这个节点就是我们要找的最近公共祖先。
     *
     * 算法
     * 从根节点开始遍历整棵二叉树，用哈希表记录每个节点的父节点指针。
     * 从 p 节点开始不断往它的祖先移动，并用数据结构记录已经访问过的祖先节点。
     * 同样，我们再从 q 节点开始不断往它的祖先移动，如果有祖先已经被访问过，即意味着这是 p 和 q 的深度最深的公共祖先，即 LCA 节点。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2( TreeNode root, TreeNode p, TreeNode q ) {
        Map<Integer, TreeNode> parents = new HashMap<>();
        dfs( root, parents );
        
        Set<Integer> visited = new HashSet<>();
        while (p != null) {
            visited.add( p.val );
            p = parents.get( p.val );
        }
        
        while (q != null) {
            if (visited.contains( q.val )) {
                return q;
            }
            q = parents.get( q.val );
        }
        return null;
    }
    
    /**
     * 递归标记父节点
     *
     * @param root
     * @param parents
     */
    private void dfs( TreeNode root, Map<Integer, TreeNode> parents ) {
        if (root == null) {
            return;
        }
        
        if (root.left != null) {
            parents.put( root.left.val, root );
            dfs( root.left, parents );
        }
        
        if (root.right != null) {
            parents.put( root.right.val, root );
            dfs( root.right, parents );
        }
    }
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
}

package leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. 打家劫舍 III
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有
 * 且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚
 * 上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */
public class L337 {
    private Map<TreeNode, Integer> mem1 = new HashMap<TreeNode, Integer>(); // 选择当前节点
    private Map<TreeNode, Integer> mem2 = new HashMap<TreeNode, Integer>(); // 不选择当前节点
    
    public int rob( TreeNode root ) {
        if (root == null) {
            return 0;
        }
        return dfs( root, true );
    }
    
    /**
     * 递归求解
     *
     * 对于每个根节点有选与不选两种情况，如果选择不能选孩子节点，如果不选孩子节点可选也可不选。
     *
     * @param root       当前节点
     * @param canSelect  当前节点是否可选
     * @return
     */
    private int dfs( TreeNode root, boolean canSelect ) {
        if (root == null) {
            return 0;
        }
        int maxValue = 0;
        
        int m = Integer.MIN_VALUE, n = Integer.MIN_VALUE;
        if (mem1.containsKey( root )) {
            m = mem1.get( root );
        } else {
            m = root.val + dfs( root.left, false ) + dfs( root.right, false );
            mem1.put( root, m );
        }
        
        if (mem2.containsKey( root )) {
            n = mem2.get( root );
        } else {
            n = dfs( root.left, true ) + dfs( root.right, true );
            mem2.put( root, n );
        }
        
        if (canSelect) {
            // 根节点可以选择的情况下，有选与不选两种情况
            maxValue = Math.max( m, n );
        } else {
            // 当前root不可选，就是说选了root的父节点，可以选择root的子节点。
            maxValue = n;
        }
        return maxValue;
    }
    
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
}

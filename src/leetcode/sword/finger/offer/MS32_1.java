package leetcode.sword.finger.offer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回：
 *
 * [3,9,20,15,7]
 *
 *
 * 提示：
 *
 * 节点总数 <= 1000
 */
public class MS32_1 {
    public int[] levelOrder( TreeNode root ) {
        if (root == null) {
            return new int[0];
        }
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add( root );
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add( node.val );
            if (node.left != null) {
                queue.add( node.left );
            }
            if (node.right != null) {
                queue.add( node.right );
            }
        }
        int[] result = new int[list.size()];
        int i = 0;
        for (int e : list) {
            result[i++] = e;
        }
        return result;
    }
    
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
    
}

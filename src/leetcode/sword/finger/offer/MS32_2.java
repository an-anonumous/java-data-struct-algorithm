package leetcode.sword.finger.offer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 剑指 Offer 32 - II. 从上到下打印二叉树 II
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 提示：
 * 节点总数 <= 1000
 */
public class MS32_2 {
    public List<List<Integer>> levelOrder( TreeNode root ) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add( root );
        while (queue.isEmpty() == false) {
            Queue<TreeNode> q = new ArrayDeque<>( queue );
            queue.clear();
            List<Integer> list = new ArrayList<>();
            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                list.add( node.val );
                if (node.left != null) {
                    queue.add( node.left );
                }
                if (node.right != null) {
                    queue.add( node.right );
                }
            }
            result.add( list );
        }
        return result;
    }
    
    public List<List<Integer>> levelOrder2( TreeNode root ) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add( root );
        while (queue.isEmpty() == false) {
            List<Integer> list = new ArrayList<>();
            int num = queue.size();
            while (num > 0) {
                TreeNode node = queue.poll();
                list.add( node.val );
                if (node.left != null) {
                    queue.add( node.left );
                }
                if (node.right != null) {
                    queue.add( node.right );
                }
                --num;
            }
            result.add( list );
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

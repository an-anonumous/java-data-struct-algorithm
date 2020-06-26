package leetcode.Hot100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
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
 */
public class H102 {
    
    /**
     * 层次化遍历
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder( TreeNode root ) {
        // 保存最终结果
        List<List<Integer>> result = new LinkedList<>();
        
        // 层次遍历的辅助队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer( root );
        while (!queue.isEmpty()) {
            // 每个循环处理一层
            
            Queue<TreeNode> tempQueue = new LinkedList<>();
            while (!queue.isEmpty()) {
                tempQueue.offer( queue.poll() );
            }
            
            List<Integer> level = new LinkedList<>();
            while (tempQueue.isEmpty() == false) {
                TreeNode node = tempQueue.remove();
                if (node != null) {
                    level.add( node.val );
                    queue.offer( node.left );
                    queue.offer( node.right );
                }
            }
            if (!level.isEmpty()) {
                result.add( level );
            }
        }
        return result;
    }
    
    /**
     * 递归解法
     *
     * @param root
     * @return
     */
    public List<List<Integer>> recusiveLevelOrder( TreeNode root ) {
        List<List<Integer>> result = new LinkedList<>();
        dfs( root, 0, result );
        return result;
    }
    
    /**
     * 先序遍历
     *
     * @param root      当前节点
     * @param level     当前节点的层号
     * @param result    记录所有结果
     */
    protected void dfs( TreeNode root, int level, List<List<Integer>> result ) {
        if (root == null) {
            return;
        }
        
        if (level >= result.size()) {
            result.add( new ArrayList<Integer>() );
        }
        
        result.get( level ).add( root.val );
        dfs( root.left, level + 1, result );
        dfs( root.right, level + 1, result );
    }
    
    protected class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode( int x ) { val = x; }
    }
}

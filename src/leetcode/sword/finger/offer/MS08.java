package leetcode.sword.finger.offer;

import org.junit.Assert;
import org.junit.Test;

/**
 * 面试题8：二叉树的下一个结点
 * 题目：给定一棵二叉树和其中的一个结点，如何找出中序遍历顺序的下一个结点？
 * 树中的结点除了有两个分别指向左右子结点的指针以外，还有一个指向父结点的指针。
 */
public class MS08 {
    
    /**
     *        8
     *       /  \
     *      6    10
     *     / \   / \
     *    5   7  9  11
     */
    @Test
    public void test() {
        BTNode root = connectNodes( new BTNode( 8 ),
                connectNodes( new BTNode( 6 ), new BTNode( 5 ), new BTNode( 7 ) ),
                connectNodes( new BTNode( 10 ), new BTNode( 9 ), new BTNode( 11 ) ) );
        
        Assert.assertEquals( 9, getNext( root ).value );
        Assert.assertEquals( 8, getNext( root.left.right ).value );
        Assert.assertEquals( null, getNext( root.right.right ) );
        
    }
    
    
    /**
     *
     * @param curr
     * @return
     */
    public BTNode getNext( BTNode curr ) {
        if (curr == null) {
            return null;
        }
        
        // 当前节点有右孩子，则中序遍历的下一个节点为，右子树的最左下节点
        if (curr.right != null) {
            BTNode next = curr.right;
            while (next != null && next.left != null) {
                next = next.left;
            }
            return next;
        } else {
            BTNode parent = curr.parent;
            // 如果当前节点没有右子树，且当前节点为父节点的左子树，则中序遍历的下一个节点为父节点
            if (parent != null && parent.left == curr) {
                return parent;
            }
            
            // 如果当前节点没有右子树，且为父节点的右孩子，逐层向上遍历，直到找到一个节点是它父节点的左子树的祖先它的父节点就是下一个节点。
            while (parent != null && parent.right == curr) {
                curr = parent;
                parent = parent.parent;
            }
            
            if (parent != null) {
                return parent;
            }
        }
        return null;
    }
    
    private BTNode connectNodes( BTNode root, BTNode left, BTNode right ) {
        if (root == null) {
            return root;
        }
        
        root.left = left;
        root.right = right;
        
        if (left != null) {
            left.parent = root;
        }
        if (right != null) {
            right.parent = root;
        }
        return root;
    }
    
    /**
     * 二叉树的节点
     */
    private static class BTNode {
        public int value;
        public BTNode left = null;
        public BTNode right = null;
        public BTNode parent = null;
        
        public BTNode( int value, BTNode left, BTNode right, BTNode parent ) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.value = value;
        }
        
        public BTNode( int value ) {
            this( value, null, null, null );
        }
    }
}

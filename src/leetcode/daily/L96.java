package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 * 输入: 3
 * 输出: 5
 *
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */
public class L96 {
    private Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
    
    @Test
    public void test() {
        Assert.assertEquals( 5, numTrees( 3 ) );
    }
    
    /**
     * 递归
     *
     * 思路
     * 给定一个有序序列 1⋯n，为了构建出一棵二叉搜索树，我们可以遍历每个数字 i，将该数字作为树根，将 1⋯(i−1) 序列作为左子树，将(i+1)⋯n
     * 序列作为右子树。接着我们可以按照同样的方式递归构建左子树和右子树。
     *
     * 在上述构建的过程中，由于根的值不同，因此我们能保证每棵二叉搜索树是唯一的。
     *
     * @param n
     * @return
     */
    public int numTrees( int n ) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += numTrees( 1, i - 1 ) * numTrees( i + 1, n );
        }
        return sum;
    }
    
    private int numTrees( int start, int end ) {
        if (start >= end) {
            return 1;
        }
        
        Integer cached = memo.get( end - start + 1 );
        if (cached != null) {
            return cached;
        }
        
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += numTrees( start, i - 1 ) * numTrees( i + 1, end );
        }
        
        memo.put( end - start + 1, sum );
        return sum;
    }
    
    /**
     *
     * 将题目进一步抽象为：给定n个连续的数字，这n个数字能够组成多少种形态的二叉搜索树，记为F(n)。选其中一个数字k作为根节点，假设小于k的
     * 数字有a个，大于k的数字有b个，则以k为根节点的二叉搜索树的个数为：F(a)*F(b). F(0)=1,F(1)=1
     * 那么F(n)=∑F(ak)*F(bk)   k=1,2,3...n
     *
     * @param n
     * @return
     */
    public int dpNumTrees( int n ) {
        int[] dp = new int[n + 1];
        Arrays.fill( dp, 0 );
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            //求解dp[i]
            for (int j = 1; j <= i; j++) {
                // j为root
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}

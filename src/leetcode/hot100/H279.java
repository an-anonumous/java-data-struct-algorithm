package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 279. 完全平方数
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
 * 你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 */
public class H279 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 3, numSquares( 12 ) );
        Assert.assertEquals( 2, numSquares( 13 ) );
    }
    
    public int numSquares( int n ) {
        // 暂存所有小于n的完全平方数
        List<Integer> list = new ArrayList<>();
        int i = 1;
        while (i * i <= n) {
            list.add( i * i );
            ++i;
        }
        
        int[] dp = new int[n + 1];
        for (int j = 0; j < dp.length; j++) {
            dp[j] = Integer.MAX_VALUE;
        }
        dp[1] = 1;
        
        for (int j = 2; j <= n; j++) {
            if (list.contains( j )) {
                dp[j] = 1;
            }
            for (int k = 0; k < list.size() && j - list.get( k ) >= 1; k++) {
                dp[j] = Math.min( dp[j], dp[j - list.get( k )] + 1 );
            }
        }
        return dp[n];
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 1, numSquares( 4 ) );
    }
}

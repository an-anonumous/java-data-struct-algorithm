package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

/**
 * 64. 最小路径和
 *
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 */
public class H64 {
    
    @Test
    public void test() {
        H64 h64 = new H64();
        Assert.assertEquals( 7, h64.dpMinPathSum( new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}} ) );
    }
    
    /**
     * 动态规划解法一
     * 思路：
     *  dp[i][j] 代表从左上角到grid[i][j]的最短路径
     *  dp[i][j]=min(dp[i-1][j],dp[i][j-1])+dp[i][j]
     *
     * @param grid
     * @return
     */
    public int dpMinPathSum( int[][] grid ) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == 0 && j == 0) {
                    continue;
                } else if (i == 0) {
                    grid[i][j] = grid[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                } else {
                    grid[i][j] = Math.min( grid[i - 1][j], grid[i][j - 1] ) + grid[i][j];
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
    
    /**
     * 动态规划二
     *
     * dp(i,j) 表示从坐标 (i,j) 到右下角的最小路径权值。我们初始化右下角的 dp 值为对应的原矩阵值。
     * dp(i,j)=grid(i,j)+min(dp(i+1,j),dp(i,j+1))
     *
     * @param grid
     * @return
     */
    public int dpMinPathSum2( int[][] grid ) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[i].length - 1; j >= 0; j--) {
                if (i == dp.length - 1 && j == dp[i].length - 1) {
                    dp[i][j] = grid[i][j];
                } else if (i == dp.length - 1) {
                    dp[i][j] = grid[i][j] + dp[i][j + 1];
                } else if (j == dp[i].length - 1) {
                    dp[i][j] = grid[i][j] + dp[i + 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.min( dp[i + 1][j], dp[i][j + 1] );
                }
            }
        }
        return dp[0][0];
    }
    
}

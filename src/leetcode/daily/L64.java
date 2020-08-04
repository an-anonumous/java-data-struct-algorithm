package leetcode.daily;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 */
public class L64 {
    
    private int minLen = Integer.MAX_VALUE;
    
    @Test
    public void test1() {
        int arr[][] = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        Assert.assertEquals( 7, MinPathSum( arr ) );
    }
    
    @Test
    public void test2() {
        int arr[][] = new int[][]{
                {3, 8, 6, 0, 5, 9, 9, 6, 3, 4, 0, 5, 7, 3, 9, 3},
                {0, 9, 2, 5, 5, 4, 9, 1, 4, 6, 9, 5, 6, 7, 3, 2},
                {8, 2, 2, 3, 3, 3, 1, 6, 9, 1, 1, 6, 6, 2, 1, 9},
                {1, 3, 6, 9, 9, 5, 0, 3, 4, 9, 1, 0, 9, 6, 2, 7},
                {8, 6, 2, 2, 1, 3, 0, 0, 7, 2, 7, 5, 4, 8, 4, 8},
                {4, 1, 9, 5, 8, 9, 9, 2, 0, 2, 5, 1, 8, 7, 0, 9},
                {6, 2, 1, 7, 8, 1, 8, 5, 5, 7, 0, 2, 5, 7, 2, 1},
                {8, 1, 7, 6, 2, 8, 1, 2, 2, 6, 4, 0, 5, 4, 1, 3},
                {9, 2, 1, 7, 6, 1, 4, 3, 8, 6, 5, 5, 3, 9, 7, 3},
                {0, 6, 0, 2, 4, 3, 7, 6, 1, 3, 8, 6, 9, 0, 0, 8},
                {4, 3, 7, 2, 4, 3, 6, 4, 0, 3, 9, 5, 3, 6, 9, 3},
                {2, 1, 8, 8, 4, 5, 6, 5, 8, 7, 3, 7, 7, 5, 8, 3},
                {0, 7, 6, 6, 1, 2, 0, 3, 5, 0, 8, 0, 8, 7, 4, 3},
                {0, 4, 3, 4, 9, 0, 1, 9, 7, 7, 8, 6, 4, 6, 9, 5},
                {6, 5, 1, 9, 9, 2, 2, 7, 4, 2, 7, 2, 2, 3, 7, 2},
                {7, 1, 9, 6, 1, 2, 7, 0, 9, 6, 6, 4, 4, 5, 1, 0},
                {3, 4, 9, 2, 8, 3, 1, 2, 6, 9, 7, 0, 2, 4, 2, 0},
                {5, 1, 8, 8, 4, 6, 8, 5, 2, 4, 1, 6, 2, 2, 9, 7}};
        System.out.println( MinPathSum( arr ) );
    }
    
    /**
     * 超时
     * @param grid
     * @return
     */
    public int MinPathSum( int[][] grid ) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        minLen = Integer.MAX_VALUE;
        dfs( grid, 0, 0, 0 );
        return minLen;
    }
    
    /**
     *
     * @param grid
     * @param r
     * @param c
     * @param len
     * @return
     */
    public void dfs( int[] @NotNull [] grid, int r, int c, int len ) {
        if (r > grid.length - 1 && c > grid[0].length - 1) {
            return;
        }
        len += grid[r][c];
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            if (len < minLen) {
                minLen = len;
            }
            return;
        }
        final int R = grid.length;
        final int C = grid[0].length;
        
        if (r < R - 1) {
            dfs( grid, r + 1, c, len );
        }
        if (c < C - 1) {
            dfs( grid, r, c + 1, len );
        }
    }
    
    public int dpminPathSum( int[][] grid ) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        final int R = grid.length;
        final int C = grid[0].length;
        
        int dp[][] = new int[R][C];
        
        dp[0][0] = grid[0][0];
        // 第0行
        for (int c = 1; c < C; c++) {
            dp[0][c] = grid[0][c] + dp[0][c - 1];
        }
        
        // 第0列
        for (int r = 1; r < R; r++) {
            dp[r][0] = dp[r - 1][0] + grid[r][0];
        }
        
        for (int r = 1; r < R; r++) {
            for (int c = 1; c < C; c++) {
                dp[r][c] = Math.min( dp[r - 1][c], dp[r][c - 1] ) + grid[r][c];
            }
        }
        return dp[R - 1][C - 1];
    }
    
    @Test
    public void test3() {
        int arr[][] = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        Assert.assertEquals( 7, dpminPathSum( arr ) );
    }
    
    @Test
    public void test4() {
        int arr[][] = new int[][]{
                {3, 8, 6, 0, 5, 9, 9, 6, 3, 4, 0, 5, 7, 3, 9, 3},
                {0, 9, 2, 5, 5, 4, 9, 1, 4, 6, 9, 5, 6, 7, 3, 2},
                {8, 2, 2, 3, 3, 3, 1, 6, 9, 1, 1, 6, 6, 2, 1, 9},
                {1, 3, 6, 9, 9, 5, 0, 3, 4, 9, 1, 0, 9, 6, 2, 7},
                {8, 6, 2, 2, 1, 3, 0, 0, 7, 2, 7, 5, 4, 8, 4, 8},
                {4, 1, 9, 5, 8, 9, 9, 2, 0, 2, 5, 1, 8, 7, 0, 9},
                {6, 2, 1, 7, 8, 1, 8, 5, 5, 7, 0, 2, 5, 7, 2, 1},
                {8, 1, 7, 6, 2, 8, 1, 2, 2, 6, 4, 0, 5, 4, 1, 3},
                {9, 2, 1, 7, 6, 1, 4, 3, 8, 6, 5, 5, 3, 9, 7, 3},
                {0, 6, 0, 2, 4, 3, 7, 6, 1, 3, 8, 6, 9, 0, 0, 8},
                {4, 3, 7, 2, 4, 3, 6, 4, 0, 3, 9, 5, 3, 6, 9, 3},
                {2, 1, 8, 8, 4, 5, 6, 5, 8, 7, 3, 7, 7, 5, 8, 3},
                {0, 7, 6, 6, 1, 2, 0, 3, 5, 0, 8, 0, 8, 7, 4, 3},
                {0, 4, 3, 4, 9, 0, 1, 9, 7, 7, 8, 6, 4, 6, 9, 5},
                {6, 5, 1, 9, 9, 2, 2, 7, 4, 2, 7, 2, 2, 3, 7, 2},
                {7, 1, 9, 6, 1, 2, 7, 0, 9, 6, 6, 4, 4, 5, 1, 0},
                {3, 4, 9, 2, 8, 3, 1, 2, 6, 9, 7, 0, 2, 4, 2, 0},
                {5, 1, 8, 8, 4, 6, 8, 5, 2, 4, 1, 6, 2, 2, 9, 7}};
        System.out.println( dpminPathSum( arr ) );
    }
    
}

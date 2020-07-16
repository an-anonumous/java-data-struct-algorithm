package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

/**
 * 62. 不同路径
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 *
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 *
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10 ^ 9
 *
 */
public class H62 {
    
    @Test
    public void test() {
        H62 h62 = new H62();
        Assert.assertEquals( 3, h62.dfsUniquePaths( 3, 2 ) );
        Assert.assertEquals( 28, h62.dfsUniquePaths( 7, 3 ) );
    }
    
    /**
     * 递归版
     *
     * @param m
     * @param n
     * @return
     */
    public int dfsUniquePaths( int m, int n ) {
        
        int[][] map = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = -1;
            }
        }
        return traceBack( 0, 0, m, n, map );
    }
    
    /**
     * 回溯，dfs
     *
     * @param r         当前机器人所在行
     * @param c         当前机器人所在列
     * @param m         矩阵行数
     * @param n         矩阵的列数
     * @param map       存储当前位置到重点的路径条数
     * @return 从(r, c)到重点的路径数
     */
    protected int traceBack( int r, int c, final int m, final int n, int[][] map ) {
        if (r == m - 1 && c == n - 1) {
            return 1;
        }
        
        if (map[r][c] != -1) {
            return map[r][c];
        }
        
        int a = 0, b = 0;
        
        if (r + 1 < m) {
            a = traceBack( r + 1, c, m, n, map );
        }
        
        if (c + 1 < n) {
            b = traceBack( r, c + 1, m, n, map );
        }
        
        map[r][c] = a + b;
        return a + b;
    }
    
    /**
     * 我们令 dp[i][j] 是到达 i, j 最多路径
     * 动态方程：dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 注意，对于第一行 dp[0][j]，或者第一列 dp[i][0]，由于都是在边界，所以只能为 1
     *
     * 时间复杂度：O(m∗n)
     * 空间复杂度：O(m∗n)
     *
     * @param m
     * @param n
     * @return
     */
    public int dpUniquePaths( int m, int n ) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}

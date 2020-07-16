package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

/**
 * 221. 最大正方形
 *
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 *
 */
public class H221 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 4, maximalSquare( new char[][]{
                {'1', '0', '1', '0', '0',},
                {'1', '0', '1', '1', '1',},
                {'1', '1', '1', '1', '1',},
                {'1', '0', '0', '1', '0',}
        } ) );
    }
    
    /**
     * 动态规划
     *      dp[i][j]表示以matrix[i][j]为右下角的最大正方形边长.
     *      dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
     *
     * @param matrix
     * @return
     */
    public int maximalSquare( char[][] matrix ) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int maxlen = 0;
        
        final int R = matrix.length;
        final int C = matrix[0].length;
        int dp[][] = new int[R][C];
        
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min( Math.min( dp[i - 1][j], dp[i][j - 1] ), dp[i - 1][j - 1] ) + 1;
                    }
                }
                
                if (dp[i][j] > maxlen) {
                    maxlen = dp[i][j];
                }
            }
        }
        return maxlen * maxlen;
    }
    
    /**
     * 暴力解法
     *
     * @param matrix
     * @return
     */
    public int maximalSquare2( char[][] matrix ) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '1') {
                    int area = maxArea( matrix, i, j );
                    if (area > max) {
                        max = area;
                    }
                }
            }
        }
        return max;
    }
    
    /**
     * 计算以matrix[r][c]为左上角顶点，能构成的最大正方形面积
     *
     * @param matrix
     * @param r
     * @param c
     * @return
     */
    protected int maxArea( char[][] matrix, final int r, final int c ) {
        if (matrix[r][c] == '0') {
            return 0;
        }
        
        final int R = matrix.length;
        final int C = matrix[0].length;
        
        int max = 1, d = 1;
        boolean flag = true;
        while (r + d < R && c + d < C && flag) {
            // 下一行
            for (int i = c; i <= c + d; i++) {
                if (matrix[r + d][i] == '0') {
                    flag = false;
                    break;
                }
            }
            
            // 右边一列
            for (int i = r; i <= r + d; i++) {
                if (matrix[i][c + d] == '0') {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                d++;
            }
        }
        return ( d ) * ( d );
    }
    
}

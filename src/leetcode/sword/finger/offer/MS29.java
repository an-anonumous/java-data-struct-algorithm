package leetcode.sword.finger.offer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 剑指 Offer 29. 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 限制：
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 */
public class MS29 {
    
    @Test
    public void test1() {
        System.out.println( Arrays.toString( spiralOrder( new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}} ) ) );
    }
    
    @Test
    public void test2() {
        System.out.println( Arrays.toString( spiralOrder( new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}} ) ) );
    }
    
    public int[] spiralOrder( int[][] matrix ) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        
        int R = matrix.length;
        int C = matrix[0].length;
        int[] result = new int[C * R];
        int index = 0;
        
        int rl = 0, cl = 0, rh = R - 1, ch = C - 1;
        
        while (rl <= rh && cl <= ch) {
            // 上面的行
            for (int i = cl; i <= ch; i++) {
                result[index++] = matrix[rl][i];
            }
            ++rl;
            if (rl > rh) {
                break;
            }
            
            // 右边的列
            for (int i = rl; i <= rh; i++) {
                result[index++] = matrix[i][ch];
            }
            --ch;
            if (ch < cl) {
                break;
            }
            
            // 下边的行
            for (int i = ch; i >= cl; --i) {
                result[index++] = matrix[rh][i];
            }
            --rh;
            if (rh < rl) {
                break;
            }
            
            // 左边的列
            for (int i = rh; i >= rl; --i) {
                result[index++] = matrix[i][cl];
            }
            ++cl;
        }
        return result;
    }
}

package leetcode.hot100;

import org.junit.Test;

import java.util.Arrays;

/**
 * 48. 旋转图像
 *
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 *
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1:
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * 示例 2:
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 */
public class H48 {
    
    @Test
    public void test() {
        H48 h48 = new H48();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        h48.rotate( matrix );
        for (int i = 0; i < matrix.length; i++) {
            System.out.println( Arrays.toString( matrix[i] ) );
        }
    }
    
    /**
     *
     * 最直接的想法是先转置矩阵，然后翻转每一行。这个简单的方法已经能达到最优的时间复杂度O(N^2)
     *
     * @param matrix
     */
    public void rotate( int[][] matrix ) {
        
        // 先转置矩阵
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }
    
    @Test
    public void test2() {
        H48 h48 = new H48();
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15},
                {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}
        };
        h48.rotate( matrix );
        for (int i = 0; i < matrix.length; i++) {
            System.out.println( Arrays.toString( matrix[i] ) );
        }
    }
    
    /**
     *    1 1 1 1 1 1 2
     *    4 # # # # @ 2
     *    4 $ % % & @ 2
     *    4 $ ( ! & @ 2
     *    4 $ ( ^ ^ @ 2
     *    4 $ * * * * 2
     *    4 3 3 3 3 3 3
     *
     *    {1,  2,  3,  4,   5},
     *    {6,  7,  8,  9,  10},
     *    {11, 12, 13, 14, 15},
     *    {16, 17, 18, 19, 20},
     *    {21, 22, 23, 24, 25}
     *
     *    [21, 16, 11, 6, 1]
     *    [22, 17, 12, 7, 2]
     *    [23, 18, 13, 8, 3]
     *    [24, 19, 14, 9, 4]
     *    [25, 20, 15, 10, 5]
     *
     *    (1,5,25,21)->(21,1,5,25)
     *    (2,10,24,16)->(16,2,10,24)
     *
     * @param matrix
     */
    public void rotate2( int[][] matrix ) {
        int n = matrix.length;
        
        for (int i = 0; i <= n / 2; i++) {    //   有多少个需要旋转的圈
            for (int j = 0; j < n / 2; j++) { //   每个圈里面的四元组个数
                int[] tmp = new int[4];
                
                int row = i;
                int col = j;
                
                // 将需要交换位置的四个元素取出到tmp数组中
                for (int k = 0; k < 4; k++) {
                    tmp[k] = matrix[row][col];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
                
                // 从第四个元素开始写会
                for (int k = 0; k < 4; k++) {
                    matrix[row][col] = tmp[( k + 3 ) % 4];
                    int x = row;
                    row = col;
                    col = n - 1 - x;
                }
            }
        }
    }
    
    @Test
    public void test1() {
        H48 h48 = new H48();
        int[][] matrix =
                {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        h48.rotate( matrix );
        for (int i = 0; i < matrix.length; i++) {
            System.out.println( Arrays.toString( matrix[i] ) );
        }
    }
}

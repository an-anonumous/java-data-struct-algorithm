package leetcode.SwordFingerOffer;

import org.junit.Test;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，
 * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *  
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 *  
 * 限制：
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MS04 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1 , 4 , 7 , 11 , 15} ,
                {2 , 5 , 8 , 12 , 19} ,
                {3 , 6 , 9 , 16 , 22} ,
                {10 , 13 , 14 , 17 , 24} ,
                {18 , 21 , 23 , 26 , 30}
        };
        
        System.out.println(new MS04().findNumberIn2DArray(matrix , 5));
        System.out.println(new MS04().findNumberIn2DArray(matrix , 20));
    }
    
    /**
     * 官方解题思路：
     * 利用矩阵部分有序的特点，从最右上角开始元matrix[r][c]素开始与待查找元素num比较，如果num > matrix[r][c]则可以确定r行的元素都比num
     * 小，舍弃r行；如果num < matrix[r][c],则可以确认num比c列的元素都小，所以舍弃第c列；如果num == matrix[r][c]则返回。
     *
     * 复杂度分析
     * 时间复杂度：O(n+m)。访问到的下标的行最多增加 n 次，列最多减少 m 次，因此循环体最多执行 n + m 次。
     * 空间复杂度：O(1)。
     *
     * @param matrix
     * @param num
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix , int num) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        
        int r = 0, c = matrix[0].length - 1;
        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == num) {
                return true;
            } else if (matrix[r][c] < num) {
                r++;
            } else {
                c--;
            }
        }
        return false;
    }
    
    /**
     * 自己解题思路：考虑到矩阵的特点，矩阵汇总最右下角的元素是矩阵中最大的元素，矩阵中最左上角的元素是最小的元素。
     * 使用矩阵正中间的一个元素将矩阵划分为四块，每次递归根据中间元素matrix[r][c]与待查找元素num的相对大小，排除
     * 矩阵（左上角或者右下角）1/4的元素。
     *
     * @param matrix
     * @param target
     * @return
     */
    // public boolean findNumberIn2DArray(int[][] matrix , int target) {
    //     if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
    //         return false;
    //     }
    //
    //     int R = matrix.length;
    //     int C = matrix[0].length;
    //
    //     return findNumberIn2DArray(matrix , 0 , R - 1 , 0 , C - 1 , target);
    // }
    //
    // private boolean findNumberIn2DArray(int[][] matrix , int rl , int rh , int cl , int ch , int num) {
    //     if (rl > rh || cl > ch) return false;
    //
    //     if (rl == rh && cl == ch) {
    //         return num == matrix[rl][cl];
    //     }
    //
    //     int r = (rl + rh) / 2;
    //     int c = (cl + ch) / 2;
    //
    //     if (matrix[r][c] == num) {
    //         return true;
    //     } else if (num < matrix[r][c]) {
    //         boolean res = false;
    //         res = res || findNumberIn2DArray(matrix , rl , r , cl , c , num);
    //         res = res || findNumberIn2DArray(matrix , r + 1 , rh , cl , c - 1 , num);//左下
    //         res = res || findNumberIn2DArray(matrix , rl , r - 1 , c + 1 , ch , num);//右上
    //         return res;
    //     } else {
    //         boolean res = false;
    //         res = res || findNumberIn2DArray(matrix , r + 1 , rh , cl , c , num);//左下
    //         res = res || findNumberIn2DArray(matrix , rl , r , c + 1 , ch , num);//右上
    //         res = res || findNumberIn2DArray(matrix , r + 1 , rh , c + 1 , ch , num);
    //         return res;
    //     }
    // }
    
    @Test
    public void test1() {
        int[][] matrix = new int[][]{
                {1 , 2 , 3 , 4 , 5} ,
                {6 , 7 , 8 , 9 , 10} ,
                {11 , 12 , 13 , 14 , 15} ,
                {16 , 17 , 18 , 19 , 20} ,
                {21 , 22 , 23 , 24 , 25}
        };
        System.out.println(new MS04().findNumberIn2DArray(matrix , 5));
    }
}


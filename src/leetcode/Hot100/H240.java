package leetcode.Hot100;

/**
 * 240. 搜索二维矩阵 II
 *
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 *
 */
public class H240 {
    /**
     * 如下图所示满足题意的矩阵中每个“7”型是一个连续递增的序列，从最右上角元素开始与target比较，每次淘汰一行或者一列。
     *
     * & & & & &
     * = = = = &
     * % % % = &
     * # # % = &
     * @ # % = &
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix( int[][] matrix, int target ) {
    
    }
}

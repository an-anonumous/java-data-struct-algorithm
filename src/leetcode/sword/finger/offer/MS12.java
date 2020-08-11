package leetcode.sword.finger.offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 面试题12. 矩阵中的路径
 *
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，
 * 每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * 例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。 *
 * [
 * ["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]
 * ]
 *
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 * 示例 1： *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 *
 * 示例 2： *
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *
 * 提示： *
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 */
public class MS12 {
    public boolean exist( char[][] board, String word ) {
        if (board==null || board.length==0 || board[0]==null || board[0].length==0 || word==null || word.length()==0) {
            return false;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i<visited.length; i++) {
            for (int j = 0; j<visited[0].length; j++) {

                for (int r = 0; r<visited.length; r++) {
                    for (int c = 0; c<visited[0].length; c++) {
                        visited[r][c] = false;
                    }
                }

                if (dfs( board, i, j, word, 0, visited )) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs( char[][] board, int r, int c, String word, int index, boolean[][] visited ) {
        if (visited[r][c]) {
            return false;
        }

        if (word.charAt( index )!=board[r][c]) {
            return false;
        } else {
            if (index==word.length()-1) {
                return true;
            }

            visited[r][c] = true;
            if (r+1<board.length && dfs( board, r+1, c, word, index+1, visited )) {
                return true;
            } else {
                visited[r][c] = false;
            }

            visited[r][c] = true;
            if (c+1<board[0].length && dfs( board, r, c+1, word, index+1, visited )) {
                return true;
            } else {
                visited[r][c] = false;
            }

            visited[r][c] = true;
            if (r-1 >= 0 && dfs( board, r-1, c, word, index+1, visited )) {
                return true;
            } else {
                visited[r][c] = false;
            }

            visited[r][c] = true;
            if (c-1 >= 0 && dfs( board, r, c-1, word, index+1, visited )) {
                return true;
            } else {
                visited[r][c] = false;
            }
        }
        return false;
    }

    @Test
    public void test1() {
        MS12 ms12 = new MS12();
        char[][] matrix = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
//        System.out.println( ms12.exist( matrix, "ABCCED" ) );
        assertEquals( true, ms12.exist( matrix, "ABCCED" ) );
    }

    @Test
    public void test2() {
        MS12 ms12 = new MS12();
        char[][] matrix = {{'a', 'b'}, {'c', 'd'}};
//        System.out.println( ms12.exist( matrix, "abcd" ) );
        assertEquals( false, ms12.exist( matrix, "abcd" ) );
    }

    @Test
    public void test3() {
        MS12 ms12 = new MS12();
        char[][] matrix = {
                {'a', 'b', 'c', 'e'},
                {'s', 'f', 'c', 's'},
                {'a', 'd', 'e', 'e'}};
//        System.out.println( ms12.exist( matrix, "abfb" ) );
        assertEquals( false, ms12.exist( matrix, "abfb" ) );
    }

    @Test
    public void test4() {
        MS12 ms12 = new MS12();
        char[][] matrix = {
                {'a', 'b', 'c', 'e'},
                {'s', 'f', 'c', 's'},
                {'a', 'd', 'e', 'e'}};
        assertEquals( true, ms12.exist( matrix, "bfce" ) );
    }

    @Test
    public void test5() {
        MS12 ms12 = new MS12();
        char[][] matrix = {{'a'}};
        assertEquals( true, ms12.exist( matrix, "a" ) );
    }

    @Test
    public void test6() {
        MS12 ms12 = new MS12();
        char[][] matrix = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        assertEquals( true, ms12.exist( matrix, "ABCESEEEFS" ) );
    }
    
    @Test
    public void test7() {
        MS12 ms12 = new MS12();
        char[][] matrix = {
                {'a', 'a'}
        };
        assertEquals( false, ms12.exist( matrix, "aaa" ) );
    }
    
    // ========================================================================
    
    /**
     * ABCE
     * SFCS
     * ADEE
     */
    @Test
    public void test21() {
        Assert.assertTrue( hasPath( "ABCESFCSADEE".toCharArray(), 3, 4, "ABCCED".toCharArray() ) );
    }
    
    public boolean hasPath( char[] matrix, int rows, int cols, char[] str ) {
        if (matrix == null || rows == 0 || str == null || str.length == 0) {
            return false;
        }
        
        boolean[][] used = new boolean[rows][cols];
        
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                
                for (boolean[] booleans : used) {
                    Arrays.fill( booleans, false );
                }
    
                if (traceBacke( matrix, rows, cols, i, j, str, 0, used )) {
                    return true;
                }
            }
        }
        return false;
        
    }
    
    private boolean traceBacke( char[] matrix, final int rows, final int cols, int r, int c, char[] str, int k,
                                boolean[][] used ) {
        // 重复访问一个节点
        if (used[r][c] == true || k > str.length - 1) {
            return false;
        }
        
        // 当前字符匹配失败
        if (matrix[r * cols + c] != str[k]) {
            return false;
        }
        
        // 完全匹配
        if (k == str.length - 1) {
            return true;
        }
        
        used[r][c] = true;
        if (r + 1 <= rows - 1 && traceBacke( matrix, rows, cols, r + 1, c, str, k + 1, used )) {
            return true;
        }
        
        if (r - 1 >= 0 && traceBacke( matrix, rows, cols, r - 1, c, str, k + 1, used )) {
            return true;
        }
        
        if (c + 1 <= cols - 1 && traceBacke( matrix, rows, cols, r, c + 1, str, k + 1, used )) {
            return true;
        }
        
        if (c - 1 >= 0 && traceBacke( matrix, rows, cols, r, c - 1, str, k + 1, used )) {
            return true;
        }
        
        used[r][c] = false;
        
        return false;
    }
    
}

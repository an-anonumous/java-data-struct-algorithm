package leetcode.Hot100;

import org.junit.Assert;
import org.junit.Test;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 */
public class H79 {
    
    @Test
    public void test1() {
        H79 h79 = new H79();
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        Assert.assertEquals( true, h79.exist( board, "ABCCED" ) );
        Assert.assertEquals( true, h79.exist( board, "SEE" ) );
        Assert.assertEquals( false, h79.exist( board, "ABCB" ) );
    }
    
    public boolean exist( char[][] board, String word ) {
        
        boolean[][] used = new boolean[board.length][board[0].length];
        for (boolean[] arr : used) {
            for (boolean b : arr) {
                b = false;
            }
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs( board, i, j, word, 0, used )) {
                    return true;
                }
            }
        }
        return false;
    }
    
    protected boolean dfs( char[][] board, int r, int c, String word, int index, boolean[][] used ) {
        // 所有的字符已经匹配完毕
        if (index >= word.length()) {
            return true;
        }
        
        // 越界
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return false;
        }
        
        // 匹配下一个字符
        if (board[r][c] == word.charAt( index ) && used[r][c] == false) {
            boolean res = false;
            
            used[r][c] = true;
            res = res || dfs( board, r + 1, c, word, index + 1, used );
            res = res || dfs( board, r - 1, c, word, index + 1, used );
            res = res || dfs( board, r, c + 1, word, index + 1, used );
            res = res || dfs( board, r, c - 1, word, index + 1, used );
            used[r][c] = false;
            
            if (res) {
                return true;
            }
        }
        
        return false;
    }
    
}

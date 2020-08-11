package leetcode.daily;

/**
 * 给定一个二维的矩阵，包含'X'和'O'（字母 O）。找到所有被 'X' 围绕的区域，并将这些区域里所有的'O' 用 'X' 填充。
 *
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * 解释:
 * 被围绕的区间不会存在于边界上，换句
 *
 */
public class L130 {
    /**
     * 任何边界上的 O 都不会被填充为 X。 我们可以想到，所有的不被包围的 O 都直接或间接与边界上的 O 相连。我们可以利用这个性质判断 O \
     * 是否在边界上，具体地说：
     *      对于每一个边界上的 O，我们以它为起点，标记所有与它直接或间接相连的字母 O； 最后我们遍历这个矩阵，对于每一个字母：如果该字母被标记过，
     *      则该字母为没有被字母 X 包围的字母 O，我们将其还原为字母 O；如果该字母没有被标记过，则该字母为被字母 X 包围的字母 O，
     *      我们将其修改为字母 X。
     *
     * @param board
     */
    public void solve( char[][] board ) {
        if (board == null || board.length == 0) {
            return;
        }
        final int R = board.length;
        final int C = board[0].length;
        
        // 0行
        for (int i = 0; i < C; i++) {
            if (board[0][i] == 'O') {
                dfs( board, 0, i );
            }
        }
        // R-1行
        for (int i = 0; i < C; i++) {
            if (board[R - 1][i] == 'O') {
                dfs( board, R - 1, i );
            }
        }
        
        // 0列
        for (int i = 0; i < R; i++) {
            if (board[i][0] == 'O') {
                dfs( board, i, 0 );
            }
        }
        // C-1列
        for (int i = 0; i < R; i++) {
            if (board[i][C - 1] == 'O') {
                dfs( board, i, C - 1 );
            }
        }
        
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
        
    }
    
    /**
     * 可以根据board数组中的字符判断，是否已经被访问过，所以不需要visited数组。
     *
     * @param board
     * @param r
     * @param c
     */
    private void dfs( char[][] board, int r, int c ) {
        final int R = board.length;
        final int C = board[0].length;
        if (r < 0 || r > R - 1 || c < 0 || c > C - 1) {
            return;
        }
        
        if (board[r][c] == 'O') {
            board[r][c] = 'A';
            dfs( board, r + 1, c );
            dfs( board, r - 1, c );
            dfs( board, r, c - 1 );
            dfs( board, r, c + 1 );
        } else {
            return;
        }
    }
}

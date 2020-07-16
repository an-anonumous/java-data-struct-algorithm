package leetcode.sword.finger.offer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 面试题13. 机器人的运动范围
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
 * 请问该机器人能够到达多少个格子？
 *
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 */
public class MS13 {
    public int movingCount( int m, int n, int k ) {
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                visited[i][j] = false;
            }
        }
        return dfs( visited, 0, 0, k );
    }

    private int dfs( boolean[][] visited, int r, int c, int k ) {
        if (visited==null || visited[0]==null || r<0 || c<0 || r >= visited.length || c >= visited[0].length) {
            return 0;
        }
        if (visited[r][c]) {
            return 0;
        }
        int count = 0;
        if (canEnter( r, c, k )) {
            count += 1;
            visited[r][c] = true;

            if (r+1<visited.length) {
                count += dfs( visited, r+1, c, k );
            }
//            if (r-1 >= 0) {
//                count += dfs( visited, r-1, c, k );
//            }
//            if (c-1 >= 0) {
//                count += dfs( visited, r, c-1, k );
//            }
            if (c+1<visited[0].length) {
                count += dfs( visited, r, c+1, k );
            }

        }
        return count;
    }

    @Test
    public void test1() {
        MS13 ms13 = new MS13();
        assertEquals( 3, ms13.movingCount( 2, 3, 1 ) );
        assertEquals( 3, ms13.movingCount2( 2, 3, 1 ) );
    }

    @Test
    public void test2() {
        MS13 ms13 = new MS13();
        assertEquals( 1, ms13.movingCount( 3, 3, 0 ) );
        assertEquals( 1, ms13.movingCount2( 3, 3, 0 ) );
    }

    @Test
    public void test3() {
        MS13 ms13 = new MS13();
        assertEquals( true, ms13.canEnter( 35, 37, 18 ) );
        assertEquals( false, ms13.canEnter( 35, 38, 18 ) );
    }

    boolean canEnter( int r, int c, int k ) {
        int res = 0;
        while (r>0) {
            res += r%10;
            r /= 10;
        }

        while (c>0) {
            res += c%10;
            c /= 10;
        }

        if (res<=k) {
            return true;
        }

        return false;
    }

    /**
     * 递推
     * 考虑到方法一提到搜索的方向只需要朝下或朝右，我们可以得出一种递推的求解方法。     *
     *
     * 算法
     * 定义 vis[i][j] 为 (i, j) 坐标是否可达，如果可达返回 1，否则返回 0。
     * 首先 (i, j) 本身需要可以进入，因此需要先判断 i 和 j 的数位之和是否大于 k ，如果大于的话直接设置 vis[i][j] 为不可达即可。
     * 否则，前面提到搜索方向只需朝下或朝右，因此 (i, j) 的格子只会从 (i - 1, j) 或者 (i, j - 1) 两个格子走过来（不考虑边界条件），
     * 那么 vis[i][j] 是否可达的状态则可由如下公式计算得到：
     *
     *
     * 即只要有一个格子可达，那么 (i, j) 这个格子就是可达的，因此我们只要遍历所有格子，递推计算出它们是否可达然后用变量 ans
     * 记录可达的格子数量即可。
     *
     * 初始条件 vis[i][j] = 1 ，递推计算的过程中注意边界的处理。
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount2( int m, int n, int k ) {
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                visited[i][j] = false;
            }
        }

        int num = 0;
        for (int i = 0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                if (i==0 && j==0) {
                    visited[i][j] = true;
                    num++;
                    continue;
                }
                if (canEnter( i, j, k ) && ( ( i-1 >= 0 && visited[i-1][j] ) || ( j-1 >= 0 && visited[i][j-1] ) )) {
                    visited[i][j] = true;
                    num++;
                }
            }
        }
        return num;
    }
}

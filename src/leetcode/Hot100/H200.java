package leetcode.Hot100;

import org.junit.Assert;
import org.junit.Test;

/**
 * 200. 岛屿数量
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 *
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 *
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 *
 */
public class H200 {
    
    @Test
    public void test1() {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        H200 h200 = new H200();
        System.out.println( h200.numIslands( grid ) );
    }
    
    @Test
    public void test2() {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        H200 h200 = new H200();
        Assert.assertEquals( 1, h200.ufsNumIslands( grid ) );
    }
    
    /**
     * 通过并查集实现
     *
     * @param grid
     * @return
     */
    public int ufsNumIslands( char[][] grid ) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        final int R = grid.length;
        final int C = grid[0].length;
        
        UnionFindSet unionFindSet = new UnionFindSet( R * C );
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
    
                    unionFindSet.union( i * C + j );
    
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        unionFindSet.union( i * C + j, ( i - 1 ) * C + j );
                    }
                    if (i + 1 < R && grid[i + 1][j] == '1') {
                        unionFindSet.union( i * C + j, ( i + 1 ) * C + j );
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        unionFindSet.union( i * C + j, i * C + j - 1 );
                    }
                    if (j + 1 < C && grid[i][j + 1] == '1') {
                        unionFindSet.union( i * C + j, i * C + j + 1 );
                    }
    
                }
            }
        }
        return unionFindSet.getSetsNum();
    
    }
    
    @Test
    public void test3() {
        char[][] grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        H200 h200 = new H200();
        Assert.assertEquals( 3, h200.ufsNumIslands( grid ) );
    }
    
    /**
     * 本题本质上是数图中联通分量的个数。
     *
     * 遍历数组对于每一个为‘1’的节点，联通分量计数+1，从该点开始使用dfs或者bfs将能够到达的节点全部改为‘0’。
     *
     * @param grid
     * @return
     */
    public int numIslands( char[][] grid ) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    num++;
                    dfs( grid, i, j ); // 深度优先遍历将这块岛屿全部标记为0
                }
            }
        }
        return num;
    }
    
    protected void dfs( char[][] grid, int r, int c ) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[r].length || grid[r][c] == '0') {
            return;
        }
        
        grid[r][c] = '0';  // 标记已经访问过
        
        if (r - 1 >= 0 && grid[r - 1][c] == '1') {
            dfs( grid, r - 1, c );
        }
        
        if (c + 1 < grid[0].length && grid[r][c + 1] == '1') {
            dfs( grid, r, c + 1 );
        }
        
        if (r + 1 < grid.length && grid[r + 1][c] == '1') {
            dfs( grid, r + 1, c );
        }
        
        if (c - 1 >= 0 && grid[r][c - 1] == '1') {
            dfs( grid, r, c - 1 );
        }
        
    }
    
    /**
     * 并查集
     */
    protected static class UnionFindSet {
        // parents[k]代表下标为k的元素的双亲节点的下标
        private final int[] parents;
        
        /**
         * 将每一个元素初始化为只包含他自身一个节点的集合（树），每个集合用根节点来代表。
         *
         * @param capacity
         */
        public UnionFindSet( int capacity ) {
            parents = new int[capacity];
            for (int i = 0; i < parents.length; i++) {
                // parents[i] = i;
                parents[i] = -1;
            }
        }
        
        /**
         * 合并根节点，防止树变成一个链表
         *
         * @param indexa
         * @param indexb
         */
        public void union( int indexa, int indexb ) {
            // System.out.println( "union(int, int): indexa" + indexa + " , idexb= " + indexb );
            if (parents[indexa] == -1 && parents[indexb] == -1) {
                parents[indexa] = indexa;
                parents[indexb] = indexa;
                return;
            }
            
            if (parents[indexa] == -1) {
                int rootb = find( indexb );
                parents[indexa] = rootb;
                return;
            }
            if (parents[indexb] == -1) {
                int roota = find( indexa );
                parents[indexb] = roota;
                return;
            }
            int roota = find( indexa );
            int rootb = find( indexb );
            if (roota != rootb) {
                parents[rootb] = roota;
            }
        }
        
        /**
         * 查找下标为index的元素属于哪个集合，即该元素所在树的根节点下标。同时，缩小树的深度。
         *
         * @param index
         * @return
         */
        public int find( int index ) {
            // System.out.println( "find(): index=" + index + Arrays.toString( parents ) );
    
            if (parents[index] == -1) {
                return index;
            }
    
            // 查找代表该节点的根节点
            int root = index;
            while (parents[root] != root) {
                root = parents[root];
            }
    
            // 路径压缩
            while (parents[index] != root) {
                int parent = parents[index];
                parents[index] = root;
                index = parent;
            }
            
            return root;
        }
        
        /**
         * 返回当前并查集中有多少个集合
         *
         * @return
         */
        public int getSetsNum() {
            // System.out.println( Arrays.toString( parents ) );
            int num = 0;
            for (int i = 0; i < parents.length; i++) {
                if (i == parents[i]) {
                    ++num;
                }
            }
            return num;
        }
    
        /**
         * 处理只有一个节点的孤岛
         *
         * @param i
         */
        public void union( int i ) {
            if (parents[i] == -1) {
                parents[i] = i;
            }
        }
    }
    
}

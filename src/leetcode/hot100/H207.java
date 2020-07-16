package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 207. 课程表
 *
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 *
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *
 * 提示：
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 *
 */
public class H207 {
    
    @Test
    public void test1() {
        H207 h207 = new H207();
        Assert.assertTrue( h207.canFinish( 2, new int[][]{{1, 0}} ) );
    }
    
    /**
     * 拓扑排序
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish( int numCourses, int[][] prerequisites ) {
        // 邻接链表
        List<List<Integer>> adjacent = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacent.add( new LinkedList<>() );
        }
        
        // 入度表
        int[] inDegress = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            inDegress[i] = 0;
        }
        
        // 初始化邻接链表
        for (int[] prerequisite : prerequisites) {
            inDegress[prerequisite[1]]++;
            adjacent.get( prerequisite[0] ).add( prerequisite[1] );
        }
        
        int zero = 0;
        
        // 拓扑排序
        Queue<Integer> queue = new ArrayDeque();
        // 将入度为0的所有节点入队
        for (int i = 0; i < inDegress.length; i++) {
            if (inDegress[i] == 0) {
                queue.add( i );
                zero++;
            }
        }
        // 删除入度为0的节点以及从它出发的边，然后将新的入度为0的节点入队
        while (!queue.isEmpty()) {
            int index = queue.poll();
            for (int node : adjacent.get( index )) {
                if (--inDegress[node] == 0) {
                    queue.add( node );
                    zero++;
                }
            }
        }
        
        // 如果所有节点的入度都可以变为0,则该图中没有环
        return zero == numCourses;
    }
    
    
    @Test
    public void test2() {
        H207 h207 = new H207();
        Assert.assertFalse( h207.canFinish( 2, new int[][]{{1, 0}, {0, 1}} ) );
    }
    
    /**
     * 将图转换为邻接链表，然后从每个节点出发深度优先搜索是否有环路
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean dfsCanFinish( int numCourses, int[][] prerequisites ) {
        // 邻接链表
        List<List<Integer>> adjacent = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacent.add( new LinkedList<>() );
        }
        
        // 初始化邻接链表
        for (int[] prerequisite : prerequisites) {
            adjacent.get( prerequisite[0] ).add( prerequisite[1] );
        }
        
        for (int i = 0; i < numCourses; i++) {
            if (!dfs( i, adjacent, new boolean[numCourses] )) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 从root节点开始dfs遍历，如果在一个联通分量里面有节点被两次访问到，则该图中存在环路
     *
     * @param root
     * @param adjacent
     * @param visited
     * @return
     */
    protected boolean dfs( int root, List<List<Integer>> adjacent, boolean[] visited ) {
        if (visited[root]) {
            return false;
        }
        visited[root] = true;
        for (int index : adjacent.get( root )) {
            if (!dfs( index, adjacent, visited )) {
                return false;
            }
        }
        return true;
        
    }
    
    //======================================================================
    
    /**
     * 并查集只适合于无向图有无环路的判断，此题是有向图
     * 0->1,0->2,1->2
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean ufscanFinish( int numCourses, int[][] prerequisites ) {
        UnionFindSet ufs = new UnionFindSet( numCourses );
        for (int[] prerequisite : prerequisites) {
            int a = ufs.find( prerequisite[0] );
            int b = ufs.find( prerequisite[1] );
            if (a == b) {
                return false;
            }
            ufs.union( a, b );
        }
        return true;
    }
    
    /* 并查集 */
    protected static class UnionFindSet {
        private final int[] parents;
        
        public UnionFindSet( int capacity ) {
            parents = new int[capacity];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
        }
        
        public void union( int a, int b ) {
            if (a < 0 || a >= parents.length || b < 0 || b >= parents.length) {
                throw new IndexOutOfBoundsException();
            }
            int roota = find( a );
            int rootb = find( b );
            if (roota != rootb) {
                parents[roota] = rootb;
            }
        }
        
        /**
         * 查找指定下标的元素所在的集合，返回树的根节点
         *
         * @param index
         * @return
         */
        public int find( int index ) {
            if (index < 0 || index >= parents.length) {
                throw new IndexOutOfBoundsException();
            }
            
            // 查找根节点
            int root = index;
            while (parents[root] != root) {
                root = parents[root];
            }
            
            // 路径压缩
            while (index != root) {
                int parent = parents[index];
                parents[index] = root;
                index = parent;
            }
            
            return root;
        }
    }
}

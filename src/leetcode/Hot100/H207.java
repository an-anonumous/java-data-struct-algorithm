package leetcode.Hot100;

import org.junit.Assert;
import org.junit.Test;

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
    
    public boolean canFinish( int numCourses, int[][] prerequisites ) {
        
    }
    
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
    
    @Test
    public void test2() {
        H207 h207 = new H207();
        Assert.assertFalse( h207.canFinish( 2, new int[][]{{1, 0}, {0, 1}} ) );
    }
    
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

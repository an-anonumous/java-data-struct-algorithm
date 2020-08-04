package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 207. 课程表
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完
 * 成课程 1 ，我们用一个匹配来表示他们：[0,1]
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
 */
public class L207 {
    
    @Test
    public void test1() {
        Assert.assertTrue( canFinish( 2, new int[][]{{1, 0}} ) );
    }
    
    @Test
    public void test2() {
        // System.out.println( canFinish( 2, new int[][]{{1, 0}, {0, 1}} ) );
        Assert.assertFalse( canFinish( 2, new int[][]{{1, 0}, {0, 1}} ) );
    }
    
    /**
     * 拓扑排序
     *
     * 注意此题给的prerequisites数组并不是邻接连表，需要转换为邻接链表后再继续。
     *
     * 生成的序列的逆序才是课程的学习顺序
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish( int numCourses, int[][] prerequisites ) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        
        // 转换为令接连表
        ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<LinkedList<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add( new LinkedList<Integer>() );
        }
        for (int i = 0; i < prerequisites.length; i++) {
            adjacencyList.get( prerequisites[i][0] ).add( prerequisites[i][1] );
        }
        
        // 初始化入度表
        int inDegrees[] = new int[numCourses];
        for (int i = 0; i < adjacencyList.size(); i++) {
            for (int j = 0; j < adjacencyList.get( i ).size(); j++) {
                inDegrees[adjacencyList.get( i ).get( j )]++;
            }
        }
        
        // 标志数组
        boolean free[] = new boolean[numCourses];
        Arrays.fill( free, false );
        
        while (true) {
            boolean flag = false;
            for (int i = 0; i < numCourses; i++) {
                if (inDegrees[i] == 0 && free[i] == false) {
                    flag = true;
                    
                    free[i] = true;
                    for (int j = 0; j < adjacencyList.get( i ).size(); j++) {
                        inDegrees[adjacencyList.get( i ).get( j )]--;
                    }
                }
            }
            if (!flag) { // 没有入度为0的节点
                break;
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (free[i] == false) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * 拓扑排序
     *
     * 优化时间效率
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2( int numCourses, int[][] prerequisites ) {
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        
        // 转换为令接连表
        ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<LinkedList<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add( new LinkedList<Integer>() );
        }
        for (int i = 0; i < prerequisites.length; i++) {
            adjacencyList.get( prerequisites[i][1] ).add( prerequisites[i][0] );
        }
        
        // 初始化入度表
        int inDegrees[] = new int[numCourses];
        for (int i = 0; i < adjacencyList.size(); i++) {
            for (int j = 0; j < adjacencyList.get( i ).size(); j++) {
                inDegrees[adjacencyList.get( i ).get( j )]++;
            }
        }
        
        // 用来存放入度为0的节点
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.add( i );
            }
        }
        
        int free = 0;
        while (!queue.isEmpty()) {
            int i = queue.poll();
            free++;
            for (int j = 0; j < adjacencyList.get( i ).size(); j++) {
                int k = adjacencyList.get( i ).get( j );
                inDegrees[k]--;
                if (inDegrees[k] == 0) {
                    queue.add( k );
                }
            }
        }
        if (free == numCourses) {
            return true;
        }
        return false;
    }
    
}

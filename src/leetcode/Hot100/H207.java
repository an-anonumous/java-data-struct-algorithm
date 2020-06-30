package leetcode.Hot100;

import java.util.*;

public class H207 {
    
    public boolean canFinish( int numCourses, int[][] prerequisites ) {
        // 邻接链表
        List<List<Integer>> adjacent = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjacent.add( new LinkedList<>() );
        }
        
        // 入度表
        int inDegress[] = new int[numCourses];
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
        for (int i = 0; i < inDegress.length; i++) {
            if (inDegress[i] == 0) {
                queue.add( i );
                zero++;
            }
        }
        while (!queue.isEmpty()) {
            int index = queue.poll();
            for (int node : adjacent.get( index )) {
                if (--inDegress[node] == 0) {
                    queue.add( node );
                    zero++;
                }
            }
        }
        
        if (zero == numCourses) {
            return true;
        } else {
            return false;
        }
    }
    
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
}

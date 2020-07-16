package leetcode.hot100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 56. 合并区间
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 */
public class H56 {
    
    @Test
    public void test() {
        H56 h56 = new H56();
        int[][] result = h56.merge( new int[][]{{8, 10}, {1, 3}, {2, 6}, {15, 18}} );
        for (int i = 0; i < result.length; i++) {
            System.out.println( Arrays.toString( result[i] ) );
        }
    }
    
    /**
     * 如果我们按照区间的左端点排序，那么在排完序的列表中，可以合并的区间一定是连续的
     *
     * @param intervals
     * @return
     */
    public int[][] merge( int[][] intervals ) {
        
        ArrayList<int[]> result = new ArrayList<>();
        
        Arrays.sort( intervals, new Comparator<int[]>() {
            @Override
            public int compare( int[] o1, int[] o2 ) {
                return o1[0] - o2[0];
            }
        } );
        
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0], right = intervals[i][1];
            
            int j = i + 1;
            while (j < intervals.length && intervals[j][0] <= right) {
                right = Math.max( right, intervals[j][1] );
                j++;
            }
            result.add( new int[]{left, right} );
            i = j;
        }
        
        int[][] answer = new int[result.size()][];
        for (int h = 0; h < result.size(); h++) {
            answer[h] = result.get( h );
        }
        
        return answer;
    }
}

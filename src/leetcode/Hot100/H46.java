package leetcode.Hot100;

import org.junit.Test;

import java.util.*;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class H46 {
    
    public static void main( String[] args ) {
        H46 h46 = new H46();
        List<List<Integer>> lists = h46.permute( new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9} );
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> integers = lists.get( i );
            System.out.print( integers );
        }
    }
    
    @Test
    public void test() {
        H46 h46 = new H46();
        List<List<Integer>> lists = h46.permute( new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9} );
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> integers = lists.get( i );
            System.out.println( integers );
        }
    }
    
    public List<List<Integer>> permute( int[] nums ) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Deque<Integer> path = new ArrayDeque<>();
        boolean used[] = new boolean[nums.length];
        traceBack( nums, 0, path, used, result );
        return result;
    }
    
    /**
     *
     * @param nums      原始输入数组
     * @param depth     当前部分解中已有的元素个数
     * @param path      暂存目前部分解
     * @param used      标记那些元素已经被使用过
     * @param result    记录所有的排列
     */
    private void traceBack( int[] nums, int depth, Deque<Integer> path, boolean[] used, LinkedList<List<Integer>> result ) {
        // 当前排列中已经包含所有的元素
        if (depth == nums.length) {
            result.add( new ArrayList<>( path ) );
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.addLast( nums[i] );
            used[i] = true;
            traceBack( nums, depth + 1, path, used, result );
            
            // 回溯
            used[i] = false;
            path.removeLast();
        }
    }
}

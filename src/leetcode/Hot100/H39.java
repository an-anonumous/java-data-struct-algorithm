package leetcode.Hot100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 39. 组合总和
 *
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 */

public class H39 {
    
    /**
     * 思路：
     *
     * https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     *
     * 对于输入: candidates = [2,3,6,7]，target = 7。
     * 候选数组里有 2 ，如果找到了 7 - 2 = 5 的所有组合，再在之前加上 2 ，就是 7 的所有组合；
     * 同理考虑 3，如果找到了 7 - 3 = 4 的所有组合，再在之前加上 3 ，就是 7 的所有组合，依次这样找下去；
     *
     * @param candidates    候选数组，已排序
     * @param start         本次调用中搜索的起点，在数组已经排序的情况下，通过限制搜索范围来避免出现重复解
     * @param residue       当前生成的部分解与目标值得差距。
     * @param path          暂存部分解
     * @param lists         保存所有解
     */
    private void traceBack( int[] candidates, int start, int residue, ArrayList<Integer> path, List<List<Integer>> lists ) {
        // 递归边界
        if (residue < 0) {
            return;
        }
        
        // 已经得到一个解
        if (residue == 0) {
            lists.add( new ArrayList<>( path ) );
            return;
        }
        
        // int sum = 0, min = candidates[start];
        // for (int i = start; i < candidates.length; i++) {
        //     sum += candidates[i];
        //     if (candidates[i] < min) {
        //         min = candidates[i];
        //     }
        // }
        //
        // 此题中的所有数字都可以无限次的使用，所以不能这么剪枝
        //
        // // 剪枝
        // if (sum < residue) {
        //     return;
        // }
        //
        // // 剪枝
        // if (residue < min) {
        //     return;
        // }
        
        for (int i = start; i < candidates.length; i++) {
            
            // 剪枝，candidates数组中[i+1,length]都比candidates[i]大
            if (candidates[i] > residue) {
                break;
            }
            
            path.add( candidates[i] );
            traceBack( candidates, i, residue - candidates[i], path, lists );
            path.remove( path.size() - 1 );                                     // 回溯
        }
    }
    
    public List<List<Integer>> combinationSum( int[] candidates, int target ) {
        List<List<Integer>> lists = new LinkedList<>();
        Arrays.sort( candidates );
        traceBack( candidates, 0, target, new ArrayList<>(), lists );
        return lists;
    }
    
    @Test
    public void test1() {
        H39 h39 = new H39();
        System.out.println( h39.combinationSum( new int[]{2, 3, 6, 7}, 7 ) );
        System.out.println( h39.combinationSum( new int[]{2, 3, 5}, 8 ) );
    }
    
    @Test
    public void test2() {
        H39 h39 = new H39();
        System.out.println( h39.combinationSum( new int[]{1}, 4 ) );
    }
}

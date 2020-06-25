package leetcode.Hot100;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
public class H78 {
    
    /**
     * 开始假设输出子集为空，每一步都向子集添加新的整数，并生成新的子集。
     * 任何集合都有一个子集空集合，先在结果中放置空集合，然后遍历所有元素，每个当前元素在已经生成的所有子集中有放、不放两种可能。
     *
     * 如果已经生成前n-1个元素的所有子集subsets(n-1)，那么前n个元素的所有子集为subsets(n-1)+对subsets(n-1)中每一个集合中加上nums[n]的子集组成
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets( int[] nums ) {
        // 保存所有已经生成的子集
        List<List<Integer>> result = new LinkedList<>();
        
        // 空集合，任何集合都有一个子集：空集合
        result.add( new ArrayList<>() );
        
        // 遍历每一个元素
        for (int num : nums) {
            // 暂存由当前元素生成的新子集
            List<List<Integer>> temp = new LinkedList<>();
            
            //用当前元素扩张已经生成的子集
            for (List<Integer> subset : result) {
                ArrayList<Integer> set = new ArrayList<>( subset );
                set.add( num );
                temp.add( set );
            }
            
            for (List<Integer> list : temp) {
                result.add( list );
            }
        }
        return result;
    }
    
    @Test
    public void test() {
        H78 h78 = new H78();
        System.out.println( h78.traceBackSubsets( new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0} ) );
    }
    
    public List<List<Integer>> traceBackSubsets( int[] nums ) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        traceBack( nums, 0, new LinkedList<>(), result );
        return result;
    }
    
    @Test
    public void test1() {
        H78 h78 = new H78();
        System.out.println( h78.binSubsets( new int[]{1, 2, 3} ) );
        // System.out.println( h78.binSubsets( new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0} ) );
    }
    
    /**
     * 字典排序（二进制排序） 子集
     * 将每个子集映射到长度为 n 的位掩码中，其中第 i 位掩码为 1，表示第 i 个元素在子集中；如果第 i 位掩码为 0，表示第 i 个元素不在子集中。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> binSubsets( int[] nums ) {
        List<List<Integer>> subsets = new LinkedList<>();
        
        int subSetNum = 1 << nums.length;
        for (int i = 0; i < subSetNum; i++) {
            // 每个数字i代表一个子集
            ArrayList<Integer> subset = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (( ( 1 << j ) & i ) != 0) {
                    // if (( ( i >> j ) & 1 ) == 1) {
                    subset.add( nums[j] );
                }
            }
            subsets.add( subset );
        }
        return subsets;
    }
    
    /**
     * 群举
     *
     * 每次递归调用处理一个元素，选或者不选
     *
     * @param nums      输入数组
     * @param depth     当前处理的元素下标
     * @param path      暂存正在生成的子集合
     * @param result    保存所有的子集
     */
    protected void traceBack( int[] nums, int depth, LinkedList<Integer> path, List<List<Integer>> result ) {
        if (depth == nums.length) {
            result.add( new ArrayList<>( path ) );
            return;
        }
        
        // 不放入nums[depth]
        traceBack( nums, depth + 1, path, result );
        
        // 放入
        path.addLast( nums[depth] );
        traceBack( nums, depth + 1, path, result );
        path.removeLast();
    }
}

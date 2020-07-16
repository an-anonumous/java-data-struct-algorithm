package leetcode.hot100;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 15. 三数之和
 *
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class H15 {
    
    /**
     * 为了防止重复枚举，先对数组进行排序。第一层循环由小到大依次选一个数，然后内循环在这个数的左侧，使用双指针查找所有满足条件的数。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum( int[] nums ) {
        List<List<Integer>> answer = new LinkedList<List<Integer>>();
        
        // 先对数组排序
        Arrays.sort( nums );
        
        for (int i = 0; i < nums.length; i++) {
            
            // 跳过连续的同一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int L = i + 1, R = nums.length - 1, target = 0 - nums[i];
            
            while (L < R) {
                if (L > i + 1 && nums[L] == nums[L - 1]) {
                    ++L;
                    continue;
                }
                
                if (nums[L] + nums[R] == target) {
                    LinkedList list = new LinkedList<Integer>();
                    list.addLast( nums[i] );
                    list.addLast( nums[L] );
                    list.addLast( nums[R] );
                    answer.add( list );
                    ++L;
                    --R;
                    
                } else if (nums[L] + nums[R] < target) {
                    ++L;
                } else {
                    --R;
                }
            }
            
        }
        
        return answer;
    }
    
    public List<List<Integer>> threeSum2( int[] nums ) {
        List<List<Integer>> answer = new LinkedList<List<Integer>>();
        
        // 先对数组排序
        Arrays.sort( nums );
        
        for (int i = 0; i < nums.length; i++) {
            
            // 跳过连续的同一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                
                int index = Arrays.binarySearch( nums, j + 1, nums.length, 0 - nums[i] - nums[j] );
                if (index >= 0) {
                    
                    LinkedList list = new LinkedList<Integer>();
                    list.addLast( nums[i] );
                    list.addLast( nums[j] );
                    list.addLast( nums[index] );
                    answer.add( list );
                }
            }
        }
        
        return answer;
    }
}

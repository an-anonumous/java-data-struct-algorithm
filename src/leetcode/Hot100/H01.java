package leetcode.Hot100;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 */

public class H01 {
    public static void main( String[] args ) {
        int[] nums = new int[]{2, 7, 11, 15};
        
        H01 solution1 = new H01();
        int[] indexs = solution1.twoSum( nums, 9 );
        System.out.println( Arrays.toString( indexs ) );
    }
    
    /**
     * 一遍哈希表
     * 事实证明，我们可以一次完成。在进行迭代并将元素插入到表中的同时，我们还会回过头来检查表中是否已经存在当前元素所对应的目标元素。
     * 如果它存在，那我们已经找到了对应解，并立即将其返回。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum( int[] nums, int target ) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer integer = map.get( target - nums[i] );
            if (integer != null && integer != i) {
                return new int[]{i, integer};
            }
            
            // 防止数组中有重复数据时，可能会出现target - nums[i]与nums[i]相等且nums[i]*2==target，后面的下标替换了前面的下标，
            // 导致map中始终只有最后一个值得下标，所以先看看map中有没有要找的元素。
            map.put( nums[i], i );
        }
        return null;
    }
    
    @Test
    public void test() {
        int[] nums = new int[]{3, 2, 4};
        
        H01 solution1 = new H01();
        int[] indexs = solution1.twoSum( nums, 6 );
        System.out.println( Arrays.toString( indexs ) );
    }
    
    /**
     * 暴力法很简单，遍历每个元素 xx，并查找是否存在一个值与 target - xtarget−x 相等的目标元素。
     *
     * @param nums
     * @param target
     * @return
     */
    // public int[] twoSum(int[] nums , int target) {
    //     int[] result = new int[2];
    //
    //     for (int i = 0; i < nums.length; i++) {
    //         for (int j = 0; j < nums.length; j++) {
    //             if (i == j) {
    //                 continue;
    //             } else {
    //                 if (nums[i] + nums[j] == target) {
    //                     result[0] = i;
    //                     result[1] = j;
    //                     return result;
    //                 }
    //             }
    //         }
    //     }
    //
    //     return null;
    // }
    
    /**
     * 两遍哈希表
     * 为了对运行时间复杂度进行优化，我们需要一种更有效的方法来检查数组中是否存在目标元素。如果存在，我们需要找出它的索引。
     * 保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。通过以空间换取速度的方式，我们可以将查找时间从 O(n)
     * 降低到 O(1)。哈希表正是为此目的而构建的，它支持以 近似恒定的时间进行快速查找。我用“近似”来描述，是因为一旦出现冲突，
     * 查找用时可能会退化到O(n)。但只要你仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为 O(1)
     * <p>
     * 一个简单的实现使用了两次迭代。在第一次迭代中，我们将每个元素的值和它的索引添加到表中。然后，在第二次迭代中，我们将
     * 检查每个元素所对应的目标元素（target - nums[i]）是否存在于表中。注意，该目标元素不能是 nums[i] 本身！
     *
     * 时间复杂度：O(n)
     * 我们把包含有 nn 个元素的列表遍历两次。由于哈希表将查找时间缩短到 O(1)O(1) ，所以时间复杂度为 O(n)O(n)。
     *
     * 空间复杂度：O(n)
     * 所需的额外空间取决于哈希表中存储的元素数量，该表中存储了 nn 个元素。
     *
     * @param nums
     * @param target
     * @return
     */
    // public int[] twoSum(int[] nums , int target) {
    //     Map<Integer, Integer> map = new HashMap<>();//用来存放数组元素值与数组下标的映射
    //     for (int i = 0; i < nums.length; i++) {
    //         map.put(nums[i] , i);
    //     }
    //     for (int i=0;i<nums.length;i++){
    //         int residue = target - nums[i];
    //         Integer integer = map.get(residue);
    //         if (integer!=null&&integer!=i)
    //         {
    //             return new int[]{i,integer};
    //         }
    //     }
    //     return null;
    // }
    
    /**
     * 如果没有负数可以使用数组代替map
     *
     * @param nums
     * @param target
     * @return
     */
    // public int[] twoSum(int[] nums , int target) {
    //     if (nums == null || nums.length == 0) {
    //         return null;
    //     }
    //
    //     int arr[] = new int[target + 1];
    //     for (int i = 0; i < arr.length; i++) {
    //         arr[i] = -1;
    //     }
    //
    //     for (int i = 0; i < nums.length; i++) {
    //         if (nums[i] <= target) {
    //             arr[nums[i]] = i;
    //         }
    //     }
    //     for (int i = 0; i < nums.length; i++) {
    //         int residue = target - nums[i];
    //         if (residue <= target && arr[residue] != -1 && i != arr[residue]) {
    //             return new int[]{i , arr[residue]};
    //         }
    //     }
    //     return null;
    // }
    @Test
    public void test2() {
        int[] nums = new int[]{3, 3};
    
        H01 solution1 = new H01();
        int[] indexs = solution1.twoSum( nums, 6 );
        System.out.println( Arrays.toString( indexs ) );
    }
}

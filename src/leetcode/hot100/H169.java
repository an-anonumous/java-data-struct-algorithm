package leetcode.hot100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素
 *
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 */
public class H169 {
    /**
     * 我们使用哈希映射（HashMap）来存储每个元素以及出现的次数。对于哈希映射中的每个键值对，键表示一个元素，值表示该元素出现的次数。
     * 我们用一个循环遍历数组 nums 并将数组中的每个元素加入哈希映射中。在这之后，我们遍历哈希映射中的所有键值对，返回值最大的键。
     * 我们同样也可以在遍历数组 nums 时候使用打擂台的方法，维护最大的值，这样省去了最后对哈希映射的遍历。
     *
     * @param nums
     * @return
     */
    public int majorityElement( int[] nums ) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey( nums[i] )) {
                map.put( nums[i], 1 );
            } else {
                map.put( nums[i], map.get( nums[i] ) + 1 );
            }
        }
        
        int maxcounter = 1, num = nums[0];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxcounter) {
                maxcounter = entry.getValue();
                num = entry.getKey();
            }
        }
        return num;
    }
    
    /**
     * 如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，那么下标为 n/2 的元素（下标从 0 开始）一定是众数。
     *
     * @param nums
     * @return
     */
    public int majorityElement1( int[] nums ) {
        Arrays.sort( nums );
        return nums[nums.length / 2];
    }
    
}

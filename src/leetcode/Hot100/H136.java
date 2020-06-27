package leetcode.Hot100;

import java.util.HashMap;
import java.util.Map;

/**
 * 136. 只出现一次的数字
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class H136 {
    public int singleNumber( int[] nums ) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey( nums[i] )) {
                map.put( nums[i], 1 );
            } else {
                map.put( nums[i], 2 );
            }
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }
    
    /**
     * 异或运算的性质
     * 1.任何数和0做异或运算，结果仍然是原来的数，即 a⊕0=a。
     * 2.任何数和其自身做异或运算，结果是 0，即 a⊕a=0。
     * 3.异或运算满足交换律和结合律，即 a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
     *
     * 根据题意，结合异或运算的性质，将数组中所有的元素一起做异或运算，出现两次的数异或后为0,0与其它数异或运算结构为原数。
     *
     * @param nums
     * @return
     */
    public int xorSingleNumber( int[] nums ) {
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }
}

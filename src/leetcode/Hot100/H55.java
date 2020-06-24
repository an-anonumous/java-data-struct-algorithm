package leetcode.Hot100;

import org.junit.Assert;
import org.junit.Test;

/**
 * 55. 跳跃游戏
 *
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 *
 * 示例 2:
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置
 *
 */
public class H55 {
    
    @Test
    public void test1() {
        H55 h55 = new H55();
        Assert.assertTrue( h55.dfsCanJump( new int[]{2, 3, 1, 1, 4} ) );
        Assert.assertFalse( h55.dfsCanJump( new int[]{3, 2, 1, 0, 4} ) );
        
    }
    
    @Test
    public void test3() {
        H55 h55 = new H55();
        Assert.assertTrue( h55.greedyCanJump( new int[]{2, 3, 1, 1, 4} ) );
        Assert.assertFalse( h55.greedyCanJump( new int[]{3, 2, 1, 0, 4} ) );
        Assert.assertTrue( h55.greedyCanJump( new int[]{3, 0, 8, 2, 0, 0, 1} ) );
        
    }
    
    /**
     * 贪心解法
     *
     * 对于每一个可以到达的位置 x，它使得x+1,x+2,⋯,x+nums[x] 这些连续的位置都可以到达。
     *
     * 我们依次遍历数组中的每一个位置，并实时维护 最远可以到达的位置。对于当前遍历到的位置 xx，如果它在 最远可以到达的位置 的范围内，那么我们就可以从
     * 起点通过若干次跳跃到达该位置，因此我们可以用 x+nums[x] 更新 最远可以到达的位置。
     *
     * 1 ms
     *
     * @param nums
     * @return
     */
    public boolean greedyCanJump( int[] nums ) {
        int max = 0;// 记录当前最远可达的下标
        for (int i = 0; i < nums.length && i <= max; i++) {
            if (nums[i] + i > max) {
                max = nums[i] + i;
            }
            if (max >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
    
    @Test
    public void test2() {
        H55 h55 = new H55();
        Assert.assertTrue( h55.dfsCanJump( new int[]{3, 0, 8, 2, 0, 0, 1} ) );
        
    }
    
    // 606 ms
    public boolean dfsCanJump( int[] nums ) {
        boolean[] map = new boolean[nums.length];
        for (int i = 0; i < map.length; i++) {
            map[i] = true;
        }
        return dfs( nums, 0, map );
    }
    
    /**
     *
     * @param nums      输入数组
     * @param index     当前位置下标
     * @param map       在数组中暂存已经计算过得节点，减少重复计算
     * @return
     */
    public boolean dfs( int[] nums, int index, boolean[] map ) {
        
        // 如果已经到达最后一个位置，则可以到达最后一个元素，返回true
        if (index == nums.length - 1) {
            return true;
        }
        
        // 不能继续跳的位置
        if (index >= nums.length || nums[index] == 0) {
            map[index] = false;
            return false;
        }
        
        for (int i = nums[index]; i >= 1; i--) {
            if (i + index < nums.length && map[i + index] && dfs( nums, index + i, map )) {
                return true;
            }
        }
        map[index] = false;
        return false;
    }
}






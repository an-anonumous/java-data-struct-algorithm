package leetcode.zhousai202;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 5489. 两球之间的磁力
 *
 * 在代号为 C-137 的地球上，Rick 发现如果他将两个球放在他新发明的篮子里，它们之间会形成特殊形式的磁力。Rick 有 n 个空的篮子，第 i 个篮子
 * 的位置在 position[i] ，Morty 想把 m 个球放到这些篮子里，使得任意两球间 最小磁力 最大。已知两个球如果分别位于 x 和 y ，那么它们之间的
 * 磁力为 |x - y| 。给你一个整数数组 position 和一个整数 m ，请你返回最大化的最小磁力。
 *
 * 示例 1：
 * 输入：position = [1,2,3,4,7], m = 3
 * 输出：3
 * 解释：将 3 个球分别放入位于 1，4 和 7 的三个篮子，两球间的磁力分别为 [3, 3, 6]。最小磁力为 3 。我们没办法让最小磁力大于 3 。
 *
 * 示例 2：
 * 输入：position = [5,4,3,2,1,1000000000], m = 2
 * 输出：999999999
 * 解释：我们使用位于 1 和 1000000000 的篮子时最小磁力最大。
 *
 * 提示：
 * n == position.length
 * 2 <= n <= 10^5
 * 1 <= position[i] <= 10^9
 * 所有 position 中的整数 互不相同 。
 * 2 <= m <= position.length
 */
public class Z5489 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 3, maxDistance( new int[]{1, 2, 3, 4, 7}, 3 ) );
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 999999999, maxDistance( new int[]{5, 4, 3, 2, 1, 1000000000}, 2 ) );
    }
    
    @Test
    public void test3() {
        Assert.assertEquals( 999999999, maxDistance( new int[]{5, 4, 3, 2, 1, 1000000000}, 2 ) );
    }
    
    /**
     * 根据题意，可能的相邻距离取值最小间隔为，[最小的两个元素只差,平均值]，使用二分法确定最大的可行间隔。
     *
     * @param position
     * @param m
     * @return
     */
    public int maxDistance( int[] position, int m ) {
        Arrays.sort( position );
        if (m == 2) {
            return position[position.length - 1] - position[0];
        }
        int max = position[position.length - 1] - position[0] / ( m - 1 );
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < position.length; i++) {
            if (position[i] - position[i - 1] < min) {
                min = position[i] - position[i - 1];
            }
        }
        
        int result = Integer.MIN_VALUE; // 记录结果
        int l = min, r = max;
        // 使用二分法，逐步确定尽可能大的可行的相邻球间距离的最小值
        while (l <= r) {
            int mid = ( l + r ) / 2;
            if (check( position, m, mid )) {
                result = Math.max( result, mid );
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l - 1;
    }
    
    /**
     * 检查相邻的球最小距离为dist是否可行
     *
     * @param position
     * @param m
     * @param dist
     * @return
     */
    private boolean check( int[] position, int m, int dist ) {
        int count = 0;
        int target = position[0] + dist;
        for (int i = 1; i < position.length; i++) {
            if (position[i] >= target) {
                ++count;
                target = position[i] + dist;
            }
        }
        return count >= m - 1;
    }
}

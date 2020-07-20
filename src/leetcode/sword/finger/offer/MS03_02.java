package leetcode.sword.finger.offer;

import org.junit.Assert;
import org.junit.Test;

/**
 * 面试题3（二）：不修改数组找出重复的数字
 * 题目：在一个长度为n+1的数组里的所有数字都在1到n的范围内，所以数组中至
 * 少有一个数字是重复的。请找出数组中任意一个重复的数字，但不能修改输入的
 * 数组。例如，如果输入长度为8的数组{2, 3, 5, 4, 3, 2, 6, 7}，那么对应的
 * 输出是重复的数字2或者3。
 */
public class MS03_02 {
    
    @Test
    public void test1() {
        int repeatNumber = findRepeatNumber0( new int[]{2, 3, 5, 4, 3, 2, 6, 7} );
        Assert.assertTrue( repeatNumber == 2 || repeatNumber == 3 );
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 4, findRepeatNumber0( new int[]{3, 2, 1, 4, 4, 5, 6, 7} ) );
    }
    
    @Test
    public void test3() {
        Assert.assertEquals( 1, findRepeatNumber0( new int[]{1, 2, 3, 4, 5, 6, 7, 1, 8} ) );
    }
    
    @Test
    public void test4() {
        Assert.assertEquals( 8, findRepeatNumber0( new int[]{1, 7, 3, 4, 5, 6, 8, 2, 8} ) );
    }
    
    @Test
    public void test5() {
        Assert.assertEquals( 1, findRepeatNumber0( new int[]{1, 1} ) );
    }
    
    @Test
    public void test6() {
        Assert.assertEquals( 3, findRepeatNumber0( new int[]{3, 2, 1, 3, 4, 5, 6, 7} ) );
    }
    
    @Test
    public void test7() {
        int repeatNumber = findRepeatNumber0( new int[]{1, 2, 2, 6, 4, 5, 6} );
        Assert.assertTrue( repeatNumber == 2 || repeatNumber == 6 );
    }
    
    @Test
    public void test8() {
        Assert.assertEquals( 2, findRepeatNumber0( new int[]{1, 2, 2, 6, 4, 5, 2} ) );
    }
    
    /**
     * 此题仍然可借助hash表来完成，下面是一个不需要辅助空间的解法。
     *
     * 在长度为n+1的数组中填充1~n共n个数字，必定有重复出现的数字。如果数组中范围在[m,n]的数字个数大于(m-n+1)的话，
     * [m,n]中必有重复数字。我们可以采用二分法逐渐缩小范围，直至找出重复数字为止。
     *
     * 在上一题中长度为n的数组中有n个数，比如如下数组[0,4,4,4,4,5,6,7,8,9]中【0，4】有5个数，【5,9】也有5个数，使用上面的方法找不出重
     * 复的数字。
     *
     * 本题中在长度为n+1的数组中放n个数，至少有一个是重复的[1,2,3,4,5,6,7,8,9,10,2],范围在【1,5】内共有6个数，【6,10】有5个数，所以
     * 【1,5】范围内至少有一个数字重复。继续在【1,5】范围内查找。
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber0( int[] nums ) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int low = 1, high = nums.length - 1;
        int mid = ( low + high ) / 2;
        while (low < high) {
            int l = 0, r = 0;
            
            // 统计左右两个区间的数字个数
            for (int num : nums) {
                if (num >= low && num <= mid) {
                    l++;
                } else {
                    ++r;
                }
            }
            
            // 如果区间内数字的个数大于区间长度，则该区间必有数字重复
            if (l > mid - low + 1) {
                high = mid;
            } else {
                low = mid + 1;
            }
            mid = ( low + high ) / 2;
            
        }
        return low;
    }
}

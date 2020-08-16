package leetcode.zhousai202;

import org.junit.Assert;
import org.junit.Test;

/**
 * 5488. 使数组中所有元素相等的最小操作数
 *
 * 存在一个长度为 n 的数组 arr ，其中 arr[i] = (2 * i) + 1 （ 0 <= i < n ）。 一次操作中，你可以选出两个下标，记作 x 和 y （ 0 <= x,
 * y < n ）并使 arr[x] 减去 1 、arr[y] 加上 1 （即 arr[x] -=1 且 arr[y] += 1 ）。最终的目标是使数组中的所有元素都 相等 。题目测试用
 * 例将会 保证 ：在执行若干步操作后，数组中的所有元素最终可以全部相等。给你一个整数 n，即数组的长度。请你返回使数组 arr 中所有元素相等所需的
 * 最小操作数 。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：2
 * 解释：arr = [1, 3, 5]
 * 第一次操作选出 x = 2 和 y = 0，使数组变为 [2, 3, 4]
 * 第二次操作继续选出 x = 2 和 y = 0，数组将会变成 [3, 3, 3]
 *
 * 示例 2：
 * 输入：n = 6
 * 输出：9
 *
 * 提示：
 * 1 <= n <= 10^4
 */
public class Z5488 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 2, minOperations( 3 ) );
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 9, minOperations( 6 ) );
    }
    
    @Test
    public void test3() {
        Assert.assertEquals( 6, minOperations( 5 ) );
    }
    
    /**
     * 先找出数组中的中位数，以中位数arr[k]为中心,arr[k-i]+i,arr[k+i]-i就可使数组中的值全部相等，操作数为所有的i取值相加。
     *
     * @param n
     * @return
     */
    public int minOperations( int n ) {
        
        if (n == 1) {
            return 0;
        }
        
        int ops = 0;
        if (( n % 2 ) == 1) {             // n为奇数
            int mid = 2 * ( n / 2 ) + 1;  // 计算中位数
            --mid;                        // 中位数自身不需要操作
            while (mid >= 0) {
                ops += mid;
                mid -= 2;
            }
            
        } else {// n为偶数
            int mid = ( 2 * ( n / 2 ) + 1 + 2 * ( n / 2 - 1 ) + 1 ) / 2;
            --mid;
            while (mid >= 0) {
                ops += mid;
                mid -= 2;
            }
        }
        return ops;
    }
}

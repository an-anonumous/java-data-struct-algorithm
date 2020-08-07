package leetcode.sword.finger.offer;

import org.junit.Assert;
import org.junit.Test;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 * <p>
 * 示例 1：
 * 输入：[3,4,5,1,2]
 * 输出：1
 * <p>
 * 示例 2：
 * 输入：[2,2,2,0,1]
 * 输出：0
 */
public class MS11 {
    
    @Test
    public void test3() {
        Assert.assertEquals( 0, minArray2( new int[]{1, 0, 1, 1, 1} ) );
    }
    
    @Test
    public void test4() {
        Assert.assertEquals( 0, minArray2( new int[]{1, 1, 1, 0, 1} ) );
    }
    
    @Test
    public void test5() {
        Assert.assertEquals( 1, minArray2( new int[]{1, 1, 1, 2, 3} ) );
    }
    
    @Test
    public void test6() {
        Assert.assertEquals( 1, minArray2( new int[]{1, 1, 1, 2, 3, 1, 1} ) );
    }
    
    @Test
    public void test7() {
        Assert.assertEquals( 1, minArray2( new int[]{4, 3, 4, 4, 4} ) );
    }
    
    
    /**
     * 解题思路：
     * 循环二分： 设置 i, j 指针分别指向 numbers 数组左右两端，m=(i+j)/2 为每次二分的中点（ "/" 代表向下取整除法，
     * 因此恒有 i≤m<j ），可分为以下三种情况：
     * 1）当 numbers[m] > numbers[j]时： m 一定在左排序数组中，即旋转点 x 一定在[m+1,j] 闭区间内，因此执行 i=m+1；
     * 2）当 numbers[m] < numbers[j] 时： m 一定在 右排序数组 中，即旋转点 x 一定在[i,m] 闭区间内，因此执行 j=m；
     * 3) 当 numbers[m] == numbers[j] 时： 无法判断 m 在哪个排序数组中，即无法判断旋转点 x 在 [i,m] 还是[m+1,j] 区间中。
     * 解决方案： 执行 j=j−1 缩小判断范围 （分析见以下内容） 。
     *
     * 返回值： 当 i=j 时跳出二分循环，并返回 numbers[i] 即可。
     *
     * @param numbers
     * @return
     */
    public int minArray2( int[] numbers ) {
        int i = 0, j = numbers.length-1, m = 0;
        while (i<j) {
            m = ( i+j )/2;
            if (numbers[m]>numbers[j]) {
                i = m+1;
            } else if (numbers[m]<numbers[j]) {
                j = m;//有可能numbers[m]就是最小值
            } else {
                // 此时numbers[i],numbers[j],numbers[m] 相同，从右向左暴力搜索
                --j;
            }
        }
        return numbers[i];
    }
    
    @Test
    public void test1() {
        MS11 ms11 = new MS11();
        System.out.println( ms11.minArray( new int[]{3, 4, 5, 1, 2} ) );
    }
    
    @Test
    public void test2() {
        MS11 ms11 = new MS11();
        System.out.println( ms11.minArray( new int[]{1, 3, 3} ) );
    }
    
    /**
     * 从两端向中间找
     *
     * @param numbers
     * @return
     */
    public int minArray( int[] numbers ) {
        int head = 0, tail = numbers.length - 1;
        while (head <= tail) {
            
            if (head + 1 < numbers.length && numbers[head] > numbers[head + 1]) {
                return numbers[head + 1];
            }
            
            if (tail - 1 >= 0 && numbers[tail] < numbers[tail - 1]) {
                return numbers[tail];
            }
            
            head++;
            tail--;
        }
        return numbers[0];
    }
    
    public int minNumberInRotateArray( int[] array ) {
        int left = 0, right = array.length - 1;
        while (left != right) {
            int mid = ( left + right ) / 2;
            if (array[mid] > array[right]) {
                // 此时mid 在左边有序数组中
                left = mid + 1;
            } else if (array[mid] < array[right]) {
                // 此时mid在右侧递增数组中，mid有可能就是最小值
                right = mid;
            } else {
                // 此时array[right],array[mid] 三者相等，无法判断最小值在那个区间，只能顺序暴力搜索。
                // 由于left有可能是最小值，而right指向的值不唯一，所以从右向左遍历
                // 1,3,3
                // left++;有可能会删除唯一的最小元素
                --right;
            }
        }
        return array[left];
    }
    
}


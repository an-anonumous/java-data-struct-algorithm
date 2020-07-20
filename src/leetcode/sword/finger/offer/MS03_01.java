package leetcode.sword.finger.offer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 找出数组中重复的数字。
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0~n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 * 限制：
 * 2 <= n <= 100000
 */
public class MS03_01 {
    public static void main( String[] args ) {
        MS03_01 ms0301 = new MS03_01();
        int number = ms0301.findRepeatNumber1( new int[]{2, 3, 1, 0, 2, 5, 3} );
        System.out.println( number );
    }
    
    /**
     * 使用hash表
     *
     * 因为长度为n的数组下边范围我0~n-1,并且数组中的数也是在0~n-1，所以新建一个数map(用数组做hash表)，用下标表示num[i],
     * map[n]表示数组n在num中出现的次数，先遍历nums建立map,然后遍历map找到第一个map[j]>1的值，j就是nums中的第一个重复数字。
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber0( int[] nums ) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException( "Illegal parameter" );
        }
        
        int[] map = new int[nums.length];
        for (int i = 0; i < map.length; i++) {
            map[i] = 0;
        }
        
        for (int i = 0; i < nums.length; i++) {
            map[nums[i]]++;
        }
        
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 1) {
                return i;
            }
        }
        
        throw new RuntimeException( "no repeated number " );
    }
    
    /**
     * 减少一次扫描
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber1( int[] nums ) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException( "Illegal parameter" );
        }
        
        boolean[] exist = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (exist[nums[i]] == true) {
                return nums[i];
            } else {
                exist[nums[i]] = true;
            }
        }
        throw new RuntimeException( "no repeated number " );
    }
    
    
    /**
     * 先试用快速排序，对数组元素排序，然后再次扫描寻找第一个重复的元素
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber2( int[] nums ) {
        quickSort( nums, 0, nums.length - 1 );
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return Integer.MIN_VALUE;
    }
    
    @Test
    public void testSort() {
        int[] arr = {1, 8, 3, 6, 1, 36, 7, 245, 0};
        quickSort( arr, 0, arr.length - 1 );
        System.out.println( Arrays.toString( arr ) );
    }
    
    /**
     * 快速排序
     *
     * @param nums
     * @param start
     * @param end
     */
    private void quickSort( int[] nums, final int start, final int end ) {
        if (start >= end || nums == null || nums.length == 0 || start < 0 || start >= nums.length || end < 0 || end >= nums.length) {
            return;
        }
        
        int temp = nums[start]; // 基准
        int r = end, l = start;
        while (l < r) {
            while (l < r && nums[r] > temp) {
                --r;
            }
            
            // 找到右侧第一个小于temp的数字下标
            if (l < r) {
                nums[l] = nums[r];
                ++l;
            }
            
            while (l < r && nums[l] < temp) {
                ++l;
            }
            
            // 左侧第一个大于temp的数
            if (l < r) {
                nums[r] = nums[l];
                --r;
            }
        }
        
        nums[l] = temp;
        
        quickSort( nums, start, l - 1 );
        quickSort( nums, l + 1, end );
        
    }
    
    /**
     * 重排数组
     *
     * 在一趟扫描中让数字k保存在下标为k的位置，同时检查是否重复
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber3( int[] nums ) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int index = 0;
        while (index < nums.length) {
            if (nums[index] == index) {
                ++index;
            } else {
                int m = nums[index];
                if (nums[m] == m) {
                    return m;
                } else {
                    nums[index] = nums[m];
                    nums[m] = m;
                }
            }
        }
        return -1;
    }
}

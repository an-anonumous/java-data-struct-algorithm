package leetcode.daily;

import org.junit.Test;

/**
 * 剑指 Offer 45. 把数组排成最小的数
 *
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 * 示例 1:
 * 输入: [10,2]
 * 输出: "102"
 *
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *
 * 提示:
 * 0 < nums.length <= 100
 *
 * 说明:
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 *
 */
public class L45 {
    private void quickSort( String strs[], final int L, final int R ) {
        // 递归结束条件，待排序子串为空或者只剩一个元素
        if (L >= R) {
            return;
        }
        
        String temp = strs[L];
        int l = L, r = R;
        while (l < r) {
            while (l < r && compare( strs[r], temp )) {
                --r;
            }
            strs[l] = strs[r];
            
            while (l < r && compare( temp, strs[l] )) {
                ++l;
            }
            strs[r] = strs[l];
        }
        strs[l] = temp;
        
        quickSort( strs, L, l - 1 );
        quickSort( strs, l + 1, R );
    }
    
    /**
     * 比较两个字符串那个放在前面组成的数字更大
     *
     * @param str1
     * @param str2
     * @return
     */
    private boolean compare( String str1, String str2 ) {
        return ( str1 + str2 ).compareTo( str2 + str1 ) >= 0;
    }
    
    @Test
    public void test1() {
        System.out.println( minNumber( new int[]{3, 30, 34, 5, 9} ) );
    }
    
    @Test
    public void test2() {
        System.out.println( minNumber( new int[]{2, 30} ) );
    }
    
    @Test
    public void test3() {
        System.out.println( minNumber( new int[]{1, 1, 1} ) );
    }
    
    
    public String minNumber( int[] nums ) {
        String strs[] = new String[nums.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = String.valueOf( nums[i] );
        }
        
        quickSort( strs, 0, strs.length - 1 );
        
        StringBuilder builder = new StringBuilder();
        for (String str : strs) {
            builder.append( str );
        }
        return builder.toString();
    }
}

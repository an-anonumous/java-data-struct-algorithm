package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 696. 计数二进制子串
 *
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * 重复出现的子串要计算它们出现的次数。
 *
 * 示例 1 :
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 *
 * 示例 2 :
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 *
 * 注意：
 * s.length 在1到50,000之间。
 * s 只包含“0”或“1”字符。
 */
public class L696 {
    
    @Test
    public void test() {
        Assert.assertEquals( 6, countBinarySubstrings( "00110011" ) );
    }
    
    
    public int countBinarySubstrings( String s ) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // List<Integer> list = new LinkedList();  // 超时
        List<Integer> list = new ArrayList<>();
        
        int index = 0;
        char ch = s.charAt( index );
        while (index < s.length()) {
            
            int count = 0;
            while (index < s.length() && s.charAt( index ) == ch) {
                count++;
                index++;
            }
            list.add( count );
            
            if (index < s.length()) {
                ch = s.charAt( index );
            }
            
        }
        
        int result = 0;
        for (int i = 1; i < list.size(); ++i) {
            result += Math.min( list.get( i ), list.get( i - 1 ) );
        }
        return result;
    }
}

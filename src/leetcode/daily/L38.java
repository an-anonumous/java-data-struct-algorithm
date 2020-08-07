package leetcode.daily;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 剑指 Offer 38. 字符串的排列
 *
 * 输入一个字符串，打印出该字符串中字符的所有排列。你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 * 限制：
 * 1 <= s 的长度 <= 8
 *
 */
public class L38 {
    
    @Test
    public void test1() {
        System.out.println( Arrays.toString( permutation( "abc" ) ) );
    }
    
    @Test
    public void test2() {
        System.out.println( Arrays.toString( permutation( "aab" ) ) );
    }
    
    /**
     * 回溯法
     *
     * 固定位置找元素
     *
     * @param s
     * @return
     */
    public String[] permutation( String s ) {
        if (s == null && s.length() == 0) {
            return new String[0];
        }
        
        Set<String> list = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        boolean[] used = new boolean[s.length()];
        
        traceBack( s, builder, used, list );
        
        String[] result = new String[list.size()];
        int i = 0;
        for (String str : list) {
            result[i++] = str;
        }
        
        return result;
    }
    
    private void traceBack( String s, StringBuilder builder, boolean[] used, Set<String> list ) {
        if (s.length() == builder.length()) {
            list.add( builder.toString() );
        }
        
        for (int j = 0; j < s.length(); j++) {
            Set<Character> set = new HashSet<>();
            if (used[j] == false) {
                char ch = s.charAt( j );
                
                // 剪枝
                if (set.contains( ch )) {
                    continue;
                } else {
                    set.add( ch );
                }
                
                builder.append( ch );
                used[j] = true;
                
                traceBack( s, builder, used, list );
                
                used[j] = false;
                builder.deleteCharAt( builder.length() - 1 );
            }
            
        }
    }
}

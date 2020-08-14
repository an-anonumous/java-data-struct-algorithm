package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

/**
 * 647. 回文子串
 *
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。具有不同开始位置或结束位置的子串，即使是由相同的字符组成，
 * 也会被计为是不同的子串。
 *
 * 示例 1:
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 *
 * 示例 2:
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * 注意:
 *
 * 输入的字符串长度不会超过1000。
 */
public class H647 {
    
    @Test
    public void test() {
        Assert.assertEquals( 3, countSubstrings( "abc" ) );
    }
    
    /**
     * 从中心往两侧延伸
     *
     * 思路:
     * 在长度为 N 的字符串中，可能的回文串中心位置有 2N-1 个：字母，或两个字母中间。从每一个回文串中心开始统计回文串数量。回文区间 [a, b]
     * 表示 S[a], S[a+1], ..., S[b] 是回文串，根据回文串定义可知 [a+1, b-1] 也是回文区间。
     *
     * @param s
     * @return
     */
    public int countSubstrings( String s ) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int result = 0;
        
        final int N = s.length();
        for (int i = 0; i < 2 * N; i++) {
            int left = i >> 1;
            int right = left + ( i & 1 );//+的优先级比&高
            // int left = i / 2;
            // int right = left + i % 2;
            while (left >= 0 && right < N && s.charAt( left ) == s.charAt( right )) {
                --left;
                ++right;
                
                ++result;
            }
        }
        return result;
    }
}

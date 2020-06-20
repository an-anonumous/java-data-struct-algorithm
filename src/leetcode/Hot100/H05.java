package leetcode.Hot100;

import org.junit.Assert;
import org.junit.Test;

/**
 * 5. 最长回文子串
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2： *
 * 输入: "cbbd"
 * 输出: "bb"
 */


public class H05 {
    @Test
    public void test1() {
        H05 h05 = new H05();
        String s = h05.longestPalindrome( "babad" );
        System.out.println( s );
    }
    
    /**
     * 带有剪枝的暴力解法勉强通过
     *
     * O(N^3)
     *
     * @param s
     * @return
     */
    public String longestPalindrome( String s ) {
        if (s == null) {
            return null;
        } else if (s.length() <= 1) {
            return s;
        }
        
        int begin = 0, end = 0, maxlen = 1;
        
        /***********************超时****************************/
        // for (int i = 0; i < s.length(); i++) {
        //     for (int j = i; j < s.length(); j++) {
        //         if (isPalindromeString( s.substring( i, j + 1 ) )) {
        //             if (j - i + 1 > maxlen) {
        //                 maxlen = j - i + 1;
        //                 begin = i;
        //                 end = j;
        //             }
        //         }
        //     }
        // }
        
        /*******************************剪枝********************************/
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (j - i + 1 > maxlen && isPalindromeString( s.substring( i, j + 1 ) )) {
                    if (j - i + 1 > maxlen) {
                        maxlen = j - i + 1;
                        begin = i;
                        end = j;
                    }
                }
            }
        }
        
        return s.substring( begin, end + 1 );
    }
    
    /**
     * 判断一个字符串是否是回文串
     *
     * @param str
     * @return
     */
    public boolean isPalindromeString( String str ) {
        if (str.length() <= 1) {
            return true;
        } else if (str.length() == 2 && str.charAt( 0 ) == str.charAt( 1 )) {
            return true;
        }
        
        int front = 0, tail = str.length() - 1;
        while (front < tail) {
            if (str.charAt( front ) == str.charAt( tail )) {
                front++;
                tail--;
            } else {
                return false;
            }
        }
        return true;
    }
    
    public boolean isPalindromeString( String str, int front, int tail ) {
        if (front - tail + 1 <= 1) {
            return true;
        }
        
        while (front < tail) {
            if (str.charAt( front ) == str.charAt( tail )) {
                front++;
                tail--;
            } else {
                return false;
            }
        }
        return true;
    }
    
    @Test
    public void test2() {
        H05 h05 = new H05();
        String s = h05.longestPalindrome( "a" );
        System.out.println( s );
    }
    
    @Test
    public void test3() {
        H05 h05 = new H05();
        String s = h05.longestPalindrome( "" );
        System.out.println( s );
    }
    
    @Test
    public void test() {
        H05 h05 = new H05();
        Assert.assertEquals( true, h05.isPalindromeString( "bab" ) );
        Assert.assertEquals( true, h05.isPalindromeString( "b" ) );
        Assert.assertEquals( true, h05.isPalindromeString( "bb" ) );
    }
    
    // 回文串长度可能为偶数也可能为奇数
    
    // 0, 1, 2, 3, 4, 5, 6, 7
    // -1, 0, 1, 2, 3, 4, 5, 6
    // -1,-1, 0, 1, 2, 3, 4, 5
    // -1,-1,-1, 0, 1, 2, 3, 4
    // -1,-1,-1,-1, 0, 1, 2, 3
    // -1,-1,-1,-1,-1, 0, 1, 2
    // -1,-1,-1,-1,-1,-1, 0, 1
    // -1,-1,-1,-1,-1,-1,-1, 0
    
    /**
     * 动态规划解法
     *
     * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
     *
     * @param s
     * @return
     */
    public String dpLongestPalindrome( String s ) {
        if (s == null) {
            return null;
        } else if (s.length() <= 1) {
            return s;
        }
        
        int start = 0, end = 0, maxlen = 1;
        
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        
        // d为c-r之差
        for (int d = 0; d < length; d++) {
            //每次循环处理一条对角线
            for (int r = 0; r < length && r + d < length; r++) {
                int c = r + d;
                if (d == 0) {//长度为1的子串
                    dp[r][c] = true;
                } else if (d == 1) {//长度为2的子串
                    dp[r][c] = ( s.charAt( r ) == s.charAt( c ) );
                } else {
                    dp[r][c] = ( s.charAt( r ) == s.charAt( c ) && dp[r + 1][c - 1] );
                }
                
                if (dp[r][c] == true && c - r + 1 > maxlen) {
                    start = r;
                    end = c;
                }
            }
        }
        return s.substring( start, end + 1 );
    }
    
    @Test
    public void test4() {
        H05 h05 = new H05();
        System.out.println( h05.LongestPalindrome2( "babad" ) );
    }
    
    /**
     * 中心扩展算法
     *
     * 枚举所有的「回文中心」并尝试「扩展」，直到无法扩展为止，此时的回文串长度即为此「回文中心」下的最长回文串长度。
     * 我们对所有的长度求出最大值，即可得到最终的答案。
     *
     * @param s
     * @return
     */
    public String LongestPalindrome2( String s ) {
        if (s == null) {
            return null;
        } else if (s.length() <= 1) {
            return s;
        }
        
        int maxlen = 1, start = 0, end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // 1. 假设回文串长度为奇数
            int L = i, R = i;
            while (L >= 0 && R < s.length() && s.charAt( L ) == s.charAt( R )) {
                L--;
                R++;
            }
            int len = R - L - 1;
            if (len > maxlen) {
                maxlen = len;
                start = L + 1;
                end = R - 1;
            }
            
            // 2. 回文串长度为偶数
            L = i;
            R = i + 1;
            while (L >= 0 && R < s.length() && s.charAt( L ) == s.charAt( R )) {
                L--;
                R++;
            }
            len = R - L - 1;
            if (len > maxlen) {
                maxlen = len;
                start = L + 1;
                end = R - 1;
            }
        }
        // System.out.println( "stat=" + start + " , end=" + end );
        return s.substring( start, end + 1 );
    }
}

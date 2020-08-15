package leetcode.sword.finger.offer;

import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer 19. 正则表达式匹配
 *
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 *
 * 示例 1: *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 */
public class MS19 {
    
    @Test
    public void test1() {
        Assert.assertFalse( isMatch( "aa", "a" ) );
    }
    
    @Test
    public void test2() {
        Assert.assertTrue( isMatch( "aa", "a*" ) );
    }
    
    @Test
    public void test3() {
        Assert.assertTrue( isMatch( "ab", ".*" ) );
    }
    
    @Test
    public void test4() {
        Assert.assertTrue( isMatch( "aab", "c*a*b" ) );
    }
    
    @Test
    public void test5() {
        Assert.assertFalse( isMatch( "mississippi", "mis*is*p*." ) );
    }
    
    @Test
    public void test6() {
        Assert.assertTrue( isMatch( "a", "ab*" ) );
    }
    
    @Test
    public void test7() {
        Assert.assertTrue( isMatch( "", ".*" ) );
    }
    
    /**
     * 大力出奇迹
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch( String s, String p ) {
        if (( s == null || s.length() == 0 ) && ( p == null || p.length() == 0 )) {
            return true;
        }
        if (!( s == null || s.length() == 0 ) && ( p == null || p.length() == 0 )) {
            return false;
        }
        
        return isMatch( s, 0, p, 0 );
    }
    
    private boolean isMatch( String s, int i, String p, int j ) {
        if (s.length() == i && j == p.length()) {
            return true;
        }
        if (s.length() != i && j == p.length()) {
            return false;
        }
        
        if (j + 1 < p.length() && p.charAt( j + 1 ) == '*') {
            // 如果下一个字符为*，当前p[j]在s串中可以出现零到多次
            if (i < s.length() && ( s.charAt( i ) == p.charAt( j ) || p.charAt( j ) == '.' )) {
                return isMatch( s, i + 1, p, j + 2 ) || isMatch( s, i + 1, p, j ) || isMatch( s, i, p, j + 2 );
            } else {
                return isMatch( s, i, p, j + 2 );//匹配零次
            }
        } else {
            if (i < s.length() && ( s.charAt( i ) == p.charAt( j ) || p.charAt( j ) == '.' )) {
                return isMatch( s, i + 1, p, j + 1 );
            }
            return false;
        }
    }
    
    @Test
    public void test21() {
        Assert.assertFalse( dpIsMatch( "aa", "a" ) );
    }
    
    @Test
    public void test22() {
        Assert.assertTrue( dpIsMatch( "aa", "a*" ) );
    }
    
    @Test
    public void test23() {
        Assert.assertTrue( dpIsMatch( "ab", ".*" ) );
    }
    
    @Test
    public void test24() {
        Assert.assertTrue( dpIsMatch( "aab", "c*a*b" ) );
    }
    
    @Test
    public void test25() {
        Assert.assertFalse( dpIsMatch( "mississippi", "mis*is*p*." ) );
    }
    
    @Test
    public void test26() {
        Assert.assertTrue( dpIsMatch( "a", "ab*" ) );
    }
    
    @Test
    public void test27() {
        Assert.assertTrue( dpIsMatch( "", ".*" ) );
    }
    
    /**
     * 题目中的匹配是一个「逐步匹配」的过程：我们每次从字符串 p 中取出一个字符或者「字符 + 星号」的组合，并在 s 中进行匹配。对于 p 中一
     * 个字符而言，它只能在 s 中匹配一个字符，匹配的方法具有唯一性；而对于p 中字符 + 星号的组合而言，它可以在 s 中匹配任意自然数个字符，
     * 并不具有唯一性。因此我们可以考虑使用动态规划，对匹配的方案进行枚举。
     *
     * https://leetcode-cn.com/problems/regular-expression-matching/solution/zheng-ze-biao-da-shi-pi-pei-by-leetcode
     * -solution/
     *
     * @param s
     * @param p
     * @return
     */
    public boolean dpIsMatch( String s, String p ) {
        if (( s == null || s.length() == 0 ) && ( p == null || p.length() == 0 )) {
            return true;
        }
        if (!( s == null || s.length() == 0 ) && ( p == null || p.length() == 0 )) { // "",".*"
            return false;
        }
        
        final int N = s.length();
        final int M = p.length();
        
        boolean dp[][] = new boolean[N + 1][M + 1];  // 用 dp[i][j] 表示 s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配
        dp[0][0] = true;                             // 两个空字符串是可以匹配的
        for (int i = 0; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (j > 0 && p.charAt( j - 1 ) == '*') {
                    if (j > 1 && i > 0 && ( j > 1 && p.charAt( j - 2 ) == '.' || p.charAt( j - 2 ) == s.charAt( i - 1 ) )) {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    }
                    if (j >= 2) {
                        dp[i][j] = dp[i][j] || dp[i][j - 2];
                    }
                    
                } else {
                    if (i > 0 && j > 0 && ( p.charAt( j - 1 ) == '.' || p.charAt( j - 1 ) == s.charAt( i - 1 ) )) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[N][M];
    }
    
}

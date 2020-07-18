package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

/**
 * 97. 交错字符串
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * 示例 1:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 *
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 */
public class L97 {
    
    @Test
    public void test1() {
        Assert.assertTrue( isInterleave( "aabcc", "dbbca", "aadbbcbcac" ) );
    }
    
    @Test
    public void test2() {
        Assert.assertFalse( isInterleave( "aabcc", "dbbca", "aadbbbaccc" ) );
    }
    
    /**
     * 解决这个问题的正确方法是动态规划。 首先如果 |s1| + |s| != |s3|，那 s3 必然不可能由 s1和 s2 交错组成。在 |s1| + |s2| = |s3|
     * 时，我们可以用动态规划来求解。我们定义 f(i, j) 表示 s1的前 i 个元素和 s2  的前 j 个元素是否能交错组成 s3 的前 i + j 个元素。
     * 如果 s1的第 i 个元素和 s3的第 i + j 个元素相等，那么 s1的前 i 个元素和 s2  的前 j 个元素是否能交错组成 s3 的前 i + j 个元素
     * 取决于 s1 的前 i - 1 个元素和 s2 的前 j 个元素是否能交错组成 s3 的前 i+j−1 个元素，即此时 f(i,j) 取决于 f(i−1,j) ，在此情况
     * 下如果 f(i−1,j) 为真，则 f(i, j)也为真。同样的，如果 s2的第 j 个元素和 s3的第 i+j 个元素相等并且 f(i,j−1) 为真，则 f(i,j)
     * 也为真。于是我们可以推导出这样的动态规划转移方程：
     *
     * f(i,j)=(s1[i-1]==s3[i+j-1] && f(i-1,j) ) || (s2[j-1]==s3[i+j-1] && f(i,j-1) )
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave( String s1, String s2, String s3 ) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        
        final int m = s1.length();
        final int n = s2.length();
        
        if (m + n != s3.length()) {
            return false;
        }
        
        // 动态规划
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // 初始化边界
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            dp[0][j] = s2.charAt( j - 1 ) == s3.charAt( 0 + j - 1 ) && dp[0][j - 1];
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = s1.charAt( i - 1 ) == s3.charAt( i - 1 ) && dp[i - 1][0];
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] =
                        ( s1.charAt( i - 1 ) == s3.charAt( i + j - 1 ) && dp[i - 1][j] )
                                || ( s2.charAt( j - 1 ) == s3.charAt( i + j - 1 ) && dp[i][j - 1] );
            }
        }
        return dp[m][n];
    }
    
    @Test
    public void test3() {
        Assert.assertTrue( isInterleave2( "aabcc", "dbbca", "aadbbcbcac" ) );
    }
    
    @Test
    public void test4() {
        Assert.assertFalse( isInterleave2( "aabcc", "dbbca", "aadbbbaccc" ) );
    }
    
    @Test
    public void test5() {
        Assert.assertFalse( isInterleave2( "a", "", "c" ) );
    }
    
    /**
     * 优化空间
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave2( String s1, String s2, String s3 ) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        
        final int m = s1.length();
        final int n = s2.length();
        
        
        if (m + n != s3.length()) {
            return false;
        }
        
        // 动态规划
        boolean[] dp = new boolean[n + 1];
        
        // 初始化边界
        dp[0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i > 0) {
                    dp[j] = s1.charAt( i - 1 ) == s3.charAt( i + j - 1 ) && dp[j];
                }
                if (j > 0) {
                    dp[j] = dp[j] || ( s2.charAt( j - 1 ) == s3.charAt( i + j - 1 ) && dp[j - 1] );
                }
            }
        }
        return dp[n];
    }
}

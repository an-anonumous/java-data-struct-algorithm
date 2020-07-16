package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class H139 {
    
    @Test
    public void test1() {
        H139 h139 = new H139();
        ArrayList<String> dict = new ArrayList<>();
        dict.add( "leet" );
        dict.add( "code" );
        Assert.assertEquals( true, h139.wordBreak( "leetcode", dict ) );
        Assert.assertEquals( true, h139.dpWordBreak( "leetcode", dict ) );
    }
    
    @Test
    public void test2() {
        H139 h139 = new H139();
        ArrayList<String> dict = new ArrayList<>();
        dict.add( "apple" );
        dict.add( "pen" );
        Assert.assertEquals( true, h139.wordBreak( "applepenapple", dict ) );
        Assert.assertEquals( true, h139.dpWordBreak( "applepenapple", dict ) );
    }
    
    @Test
    public void test3() {
        H139 h139 = new H139();
        ArrayList<String> dict = new ArrayList<>();
        dict.add( "cats" );
        dict.add( "dog" );
        dict.add( "and" );
        dict.add( "sand" );
        dict.add( "cat" );
        Assert.assertEquals( false, h139.wordBreak( "catsandog", dict ) );
        Assert.assertEquals( false, h139.dpWordBreak( "catsandog", dict ) );
    }
    
    
    @Test
    public void test4() {
        H139 h139 = new H139();
        ArrayList<String> dict = new ArrayList<>();
        dict.add( "a" );
        dict.add( "aa" );
        dict.add( "aaa" );
        dict.add( "aaaa" );
        dict.add( "aaaaa" );
        dict.add( "aaaaaa" );
        dict.add( "aaaaaaa" );
        dict.add( "aaaaaaaa" );
        dict.add( "aaaaaaaaa" );
        Assert.assertEquals( false, h139.wordBreak(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", dict ) );
        Assert.assertEquals( false, h139.dpWordBreak(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", dict ) );
    }
    
    @Test
    public void test5() {
        H139 h139 = new H139();
        ArrayList<String> dict = new ArrayList<>();
        dict.add( "aaa" );
        dict.add( "aaaa" );
        Assert.assertEquals( true, h139.wordBreak( "aaaaaaa", dict ) );
        Assert.assertEquals( true, h139.dpWordBreak( "aaaaaaa", dict ) );
    }
    
    @Test
    public void test6() {
        H139 h139 = new H139();
        ArrayList<String> dict = new ArrayList<>();
        dict.add( "a" );
        dict.add( "b" );
        // Assert.assertEquals( true, h139.wordBreak( "ab", dict ) );
        Assert.assertEquals( true, h139.dpWordBreak( "ab", dict ) );
    }
    
    /**
     * 动态规划
     *
     * 使用辅助数组boolean dp[s.length()],dp[k]代表前k+1个字符组成的子串是否满足题目要求，dp[k]=dict.contains(s[k-j+1,k]) && dp[k-j]
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean dpWordBreak( String s,  List<String> wordDict ) {
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) {
            dict.add( word );
        }
        
        // dp[k]代表[0,k]子串是否能够全部分解为字典中的单词
        boolean[] dp = new boolean[s.length()];
        for (boolean b : dp) {
            b = false;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (dict.contains( s.substring( 0, i + 1 ) )) {
                dp[i] = true;
                continue;
            }
            
            for (int j = 1; j <= i; j++) {
                if (dict.contains( s.substring( i - j + 1, i + 1 ) ) && dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length() - 1];
    }
    
    /**
     * 递归穷举
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak( String s, List<String> wordDict ) {
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) {
            dict.add( word );
        }
        
        Map<Integer, Boolean> memo = new HashMap<>();
        
        for (int i = 0; i <= s.length(); i++) {
            if (traceBack( s, 0, i, wordDict, memo )) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 先尝试从当前子问题开始分割第一个字符串，尝试所有可能的分隔位置，如果可以，递归求解分割点以后的子问题；
     * 如果失败返回false，在上一层尝试别其他分割方法。
     *
     * memo的key为子问题的起始位置，如果可以将子串全部划分为dict中的单词，则value为true；如果尝试了从当前key位置
     * 起所有可能的分割方案，没有找到合适的解，则存false.
     *
     * @param str           待分割的字符串
     * @param start         当前子问题的起始位置
     * @param curr          当前子问题尝试的分割位置
     * @param wordDict      单词表
     * @param memo          加速备忘录
     * @return
     */
    private boolean traceBack( String str, int start, int curr, List<String> wordDict, Map<Integer, Boolean> memo ) {
        if (start >= str.length()) {
            return true;
        }
        
        // 备忘录加速
        if (memo.containsKey( start )) {
            return memo.get( start );
        }
        
        if (!wordDict.contains( str.substring( start, curr ) )) {
            return false;
        }
        
        for (int i = curr; i <= str.length(); i++) {
            if (traceBack( str, curr, i, wordDict, memo )) {
                memo.put( curr, true );
                return true;
            }
        }
        
        // 此处不能是memo.put( stat, false );,上面的for循环代表的是对curr开始的子问题已经搜索完毕，对于start除了当前的curr切分点外后面可能还有。
        memo.put( curr, false );
        return false;
    }
    
}

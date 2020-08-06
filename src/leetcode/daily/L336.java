package leetcode.daily;

import org.junit.Test;

import java.util.*;

/**
 * 336. 回文对
 *
 * 给定一组 互不相同 的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 *
 * 示例 1：
 * 输入：["abcd","dcba","lls","s","sssll"]
 * 输出：[[0,1],[1,0],[3,2],[2,4]]
 * 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 *
 * 示例 2：
 * 输入：["bat","tab","cat"]
 * 输出：[[0,1],[1,0]]
 * 解释：可拼接成的回文串为 ["battab","tabbat"]
 *
 */
public class L336 {
    
    private Map<String, Integer> map = new HashMap<>();
    
    @Test
    public void test1() {
        System.out.println( palindromePairs( new String[]{"abcd", "dcba", "lls", "s", "sssll"} ).toString() );
    }
    
    @Test
    public void test2() {
        System.out.println( palindromePairs( new String[]{"bat", "tab", "cat"} ).toString() );
    }
    
    @Test
    public void test3() {
        System.out.println( palindromePairs( new String[]{"a", ""} ).toString() );
    }
    
    /**
     * 枚举前缀和后缀
     *
     * https://leetcode-cn.com/problems/palindrome-pairs/solution/hui-wen-dui-by-leetcode-solution/
     *
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs( String[] words ) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            map.put( ( new StringBuilder( word ) ).reverse().toString(), i );
        }
        
        List<List<Integer>> result = new LinkedList<>();
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 1; j < words[i].length(); j++) {
                
                if (isPalindrome( words[i].substring( 0, j ) )) {
                    // String s = new StringBuilder( words[i].substring( j ) ).reverse().toString();
                    String s = words[i].substring( j );
                    if (map.containsKey( s )) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add( map.get( s ) );
                        list.add( i );
                        result.add( list );
                    }
                }
                
                if (isPalindrome( words[i].substring( j ) )) {
                    // String s = new StringBuilder( words[i].substring( 0, j ) ).reverse().toString();
                    String s = words[i].substring( 0, j );
                    if (map.containsKey( s )) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add( i );
                        list.add( map.get( s ) );
                        result.add( list );
                    }
                }
                
            }
            
            if (map.containsKey( words[i] )) {
                int index = map.get( words[i] );
                if (index != i) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add( i );
                    list.add( index );
                    result.add( list );
                    
                }
            }
            
            if (isPalindrome( words[i] ) && map.containsKey( "" )) {
                int index = map.get( "" );
                if (index != i) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add( i );
                    list.add( index );
                    result.add( list );
                    
                    
                    ArrayList<Integer> list2 = new ArrayList<>();
                    list2.add( index );
                    list2.add( i );
                    result.add( list2 );
                }
            }
        }
        return result;
    }
    
    /**
     * 判断字符串是否为回文串
     *
     * @param str
     * @return
     */
    private boolean isPalindrome( String str ) {
        if (str == null || str.length() == 0 || str.length() == 1) {
            return true;
        }
        
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt( i++ ) != str.charAt( j-- )) {
                return false;
            }
        }
        return true;
    }
    
}

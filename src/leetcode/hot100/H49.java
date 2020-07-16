package leetcode.hot100;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 */

public class H49 {
    public List<List<String>> groupAnagrams( String[] strs ) {
        List<List<String>> result = new LinkedList<>();
    
        // 将每个字符串按字母排序后，作为key值
        HashMap<String, List<String>> map = new HashMap<>();
    
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort( array );
            String key = String.valueOf( array );
            if (map.containsKey( key )) {
                map.get( key ).add( str );
            } else {
                LinkedList<String> list = new LinkedList<>();
                list.add( str );
                map.put( key, list );
            }
        }
        
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            result.add( entry.getValue() );
        }
        
        return result;
    }
}

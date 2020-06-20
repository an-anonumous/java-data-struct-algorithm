package leetcode.Hot100;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *  拼音九键键盘
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
public class H17 {
    @Test
    public void test1() {
        H17 h17 = new H17();
        System.out.println(h17.letterCombinations( "23" ));
        System.out.println(h17.letterCombinations( "" ));
    }
    
    Map<String, String> phone = new HashMap<String, String>() {{
        put( "2", "abc" );
        put( "3", "def" );
        put( "4", "ghi" );
        put( "5", "jkl" );
        put( "6", "mno" );
        put( "7", "pqrs" );
        put( "8", "tuv" );
        put( "9", "wxyz" );
    }};
    
    /**
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations( String digits ) {
        List<String> list = new LinkedList<>();
        if (digits!=null&&digits.length()>0) {
            backtrack( digits, 0, new StringBuilder(), list );
        }
        return list;
    }
    
    /**
     *
     * @param digits  输入的数字串
     * @param index   当前正在处理第几个数字
     * @param builder 正在生成的解
     * @param list    保存所有的解
     */
    private void backtrack( String digits, int index,StringBuilder builder, List<String> list ) {
        // 递归结束条件
        if (index >= digits.length()) {
            list.add( builder.toString() );
            return;
        }
    
        String s = phone.get(  digits.substring( index,index+1 )  );
        for (int i = 0; i < s.length(); i++) {
            builder.append( s.charAt( i) );
            backtrack( digits,index+1,builder,list );
            
            builder.delete( index, 1+index); // 回溯
            
        }
    }
}

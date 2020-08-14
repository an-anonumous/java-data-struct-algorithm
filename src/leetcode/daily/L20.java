package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 * 输入: "()"
 * 输出: true
 *
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 *
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 *
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 *
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 */
public class L20 {
    
    @Test
    public void test1() {
        Assert.assertTrue( isValid( "()" ) );
    }
    
    @Test
    public void test2() {
        Assert.assertTrue( isValid( "()[]{}" ) );
    }
    
    @Test
    public void test3() {
        Assert.assertFalse( isValid( "(]" ) );
    }
    
    @Test
    public void test4() {
        Assert.assertFalse( isValid( "([)]" ) );
    }
    
    @Test
    public void test5() {
        Assert.assertTrue( isValid( "{[]}" ) );
    }
    
    public boolean isValid( String s ) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (isLeft( s.charAt( i ) )) {
                stack.push( s.charAt( i ) );
            } else {
                if (!stack.isEmpty() && isMatch( stack.pop(), s.charAt( i ) )) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }
    
    /**
     *
     * @param ch
     * @return
     */
    private boolean isLeft( char ch ) {
        switch (ch) {
            case '{':
            case '(':
            case '[':
                return true;
            default:
                return false;
        }
    }
    
    private boolean isMatch( char ch1, char ch2 ) {
        switch (ch1) {
            case '(':
                if (ch2 == ')') {
                    return true;
                } else {
                    return false;
                }
            case '[':
                if (ch2 == ']') {
                    return true;
                } else {
                    return false;
                }
            case '{':
                if (ch2 == '}') {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
}

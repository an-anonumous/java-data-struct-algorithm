package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 20. 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
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
public class H20 {
    
    @Test
    public void test1() {
        H20 h20 = new H20();
        Assert.assertTrue( h20.isValid( "()" ) );
        Assert.assertTrue( h20.isValid( "()[]{}" ) );
        Assert.assertFalse( h20.isValid( "(]" ) );
        Assert.assertFalse( h20.isValid( "([)]" ) );
        Assert.assertTrue( h20.isValid( "{[]}" ) );
        Assert.assertFalse( h20.isValid( "[" ) );
        Assert.assertFalse( h20.isValid( "]" ) );
        
    }
    
    public boolean isValid( String s ) {
        if (s == null) {
            return false;
        }
        
        if (s != null && s.length() == 0) {
            return true;
        }
        
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt( i );
            if (isLeft( ch )) {
                stack.push( ch );
            } else {
                if (!stack.empty()) {
                    Character ch2 = stack.pop();
                    if (isMatched( ch2, ch )) {
                        continue;
                    }
                }
                return false;
            }
        }
        
        if (stack.empty()) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean isMatched( char l, char r ) {
        switch (l) {
            case '[':
                if (r == ']') {
                    return true;
                } else {
                    return false;
                }
            case '{':
                if (r == '}') {
                    return true;
                } else {
                    return false;
                }
            case '(':
                if (r == ')') {
                    return true;
                } else {
                    return false;
                }
            default:
                return false;
        }
    }
    
    /**
     * 判断字符ch是否是左括号
     *
     * @param ch
     * @return
     */
    private boolean isLeft( char ch ) {
        switch (ch) {
            case '(':
            case '[':
            case '{':
                return true;
            default:
                return false;
        }
    }
    
    @Test
    public void test2() {
        H20 h20 = new H20();
        Assert.assertFalse( h20.isValid( "([]" ) );
    }
}

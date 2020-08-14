package leetcode.daily;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class L43 {
    
    @Test
    public void test1() {
        System.out.println( multiply( "123", "456" ) );
    }
    
    @Test
    public void test2() {
        System.out.println( multiply( "2", "3" ) );
    }
    
    @Test
    public void test3() {
        System.out.println( multiply( "103", "98" ) );
    }
    
    public String multiply( String num1, String num2 ) {
        
        if (num1 == null || num2 == null || "0".equals( num1 ) || "0".equals( num2 )) {
            return "0";
        }
        
        String result = "0";// 结果
        
        int base = 0;
        for (int i = num1.length() - 1; i >= 0; --i) {   // 取出乘数的每一位
            int a = num1.charAt( i ) - '0';
            int carry = 0;                               // 存储乘数每一位与被乘数上一位计算的进位
            Deque<Character> stack = new ArrayDeque<>(); // 从个位暂存每一位的结果
            for (int j = num2.length() - 1; j >= 0; --j) {
                int b = num2.charAt( j ) - '0';
                int c = a * b + carry;
                carry = c / 10;
                c = c % 10;
                stack.push( (char) ( c + '0' ) );
            }
            if (carry > 0) {
                stack.push( (char) ( carry + '0' ) );
            }
            StringBuilder builder = new StringBuilder();
            while (!stack.isEmpty()) {
                builder.append( stack.pop() );
            }
            for (int j = 0; j < base; j++) {
                builder.append( '0' );
            }
            ++base;
            result = add( result, builder.toString() );
            // System.out.println( "result=" + result + ", mutil=" + builder.toString() );
        }
        return result;
    }
    
    /**
     * 先计算两个数的和
     *
     * @param num1
     * @param num2
     * @return
     */
    public String add( String num1, String num2 ) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        
        Deque<Character> stack = new ArrayDeque<>();
        
        int carry = 0;
        while (i >= 0 && j >= 0) {
            int sum = num1.charAt( i-- ) - '0' + num2.charAt( j-- ) - '0' + carry;
            carry = sum / 10;
            sum = sum % 10;
            stack.push( (char) ( '0' + sum ) );
        }
        while (i >= 0) {
            if (carry > 0) {
                int n = num1.charAt( i-- ) - '0' + carry;
                stack.push( (char) ( n % 10 + '0' ) );
                carry = n / 10;
            } else {
                stack.push( num1.charAt( i-- ) );
            }
        }
        while (j >= 0) {
            if (carry > 0) {
                int n = num2.charAt( j-- ) - '0' + carry;
                stack.push( (char) ( n % 10 + '0' ) );
                carry = n / 10;
            } else {
                stack.push( num2.charAt( j-- ) );
            }
        }
        if (carry > 0) {
            stack.push( (char) ( carry + '0' ) );
        }
        
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append( stack.pop() );
        }
        return builder.toString();
    }
}

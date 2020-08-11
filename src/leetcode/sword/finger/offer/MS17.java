package leetcode.sword.finger.offer;

import org.junit.Test;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 * 说明：
 * 用返回一个整数列表来代替打印
 * n 为正整数
 *
 */
public class MS17 {
    
    @Test
    public void test() {
        printNumbers( 1000000000 );
    }
    
    public void printNumbers( int n ) {
        if (n <= 0) {
            return;
        }
        
        char[] number = new char[n];
        for (int i = 0; i < number.length; i++) {
            number[i] = '0';
        }
        while (increasement( number )) {
            printNumber( number );
        }
        return;
    }
    
    /**
     * 在字符串上模拟字符+1
     *
     * @param number
     * @return
     */
    private boolean increasement( char[] number ) {
        int carry = 0;// 进位
        for (int i = number.length - 1; i >= 0; --i) {
            int n = number[i] - '0' + carry;
            if (i == number.length - 1) {
                ++n;
            }
            if (n >= 10) {
                if (i == 0) {
                    return false;
                }
                carry = 1;
                n = n - 10;
                number[i] = (char) ( '0' + n );
            } else {
                number[i] = (char) ( '0' + n );
                carry = 0;
                break;// 没有进位，已经没必要在计算下去了
            }
            
        }
        return true;
    }
    
    private void printNumber( char[] number ) {
        if (number == null || number.length == 0) {
            System.out.println();
        }
        int i = 0;
        
        for (; i < number.length; i++) {
            char c = number[i];
            if (c == '0') {
                continue;
            } else {
                break;
            }
        }
        
        for (; i < number.length; i++) {
            char c = number[i];
            System.out.print( c );
        }
        System.out.println();
    }
    
    // ===============================================================================
    
    @Test
    public void test2() {
        printNumbers2( 10000 );
    }
    
    public void printNumbers2( int n ) {
        if (n <= 0) {
            return;
        }
        
        char[] number = new char[n];
        for (int i = 0; i < number.length; i++) {
            number[i] = '0';
        }
        traceBack( number, 0 );
    }
    
    private void traceBack( char[] number, int index ) {
        if (index >= number.length) {
            printNumber( number );
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[index] = (char) ( '0' + i );
            traceBack( number, index + 1 );
        }
    }
}

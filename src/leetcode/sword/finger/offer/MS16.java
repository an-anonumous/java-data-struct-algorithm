package leetcode.sword.finger.offer;

import org.junit.Test;

/**
 * 剑指 Offer 16. 数值的整数次方
 *
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 * 示例 1:
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 *
 * 示例 2:
 * 输入: 2.10000, 3
 * 输出: 9.26100
 *
 * 示例 3:
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */
public class MS16 {
    
    @Test
    public void test1() {
        System.out.println( myPow( 2.0, 10 ) );
    }
    
    @Test
    public void test2() {
        System.out.println( myPow( 1.0, -10 ) );
    }
    
    @Test
    public void test3() {
        System.out.println( myPow( 1.0, -2147483648 ) );
    }
    
    public double myPow( double x, long n ) {
        long exp = Math.abs( n );
        
        if (n < 0 && Math.abs( x - 0.0 ) < 0.000001) {
            throw new RuntimeException( "invalid input" );
        }
        
        double result = pow( x, exp );
        if (n < 0) {
            return 1.0 / result;
        } else {
            return result;
        }
    }
    
    private double pow( double x, long exp ) {
        if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return x;
        }
        
        double result = pow( x, exp >> 1 );
        if (( exp & 1 ) == 1) {
            return result * result * x;
        }
        return result * result;
    }
}

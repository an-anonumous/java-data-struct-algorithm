package leetcode.sword.finger.offer;

import org.junit.Assert;
import org.junit.Test;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
 * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 */
public class MS20 {
    
    @Test
    public void test1() {
        Assert.assertTrue( isNumber( "+100" ) );
    }
    
    @Test
    public void test2() {
        Assert.assertTrue( isNumber( "5e2" ) );
    }
    
    @Test
    public void test3() {
        Assert.assertTrue( isNumber( "-123" ) );
    }
    
    @Test
    public void test4() {
        Assert.assertTrue( isNumber( "3.1416" ) );
    }
    
    @Test
    public void test5() {
        Assert.assertTrue( isNumber( "-1E-16" ) );
    }
    
    @Test
    public void test6() {
        Assert.assertTrue( isNumber( "0123" ) );
    }
    
    @Test
    public void test7() {
        Assert.assertFalse( isNumber( "12e" ) );
    }
    
    @Test
    public void test8() {
        Assert.assertFalse( isNumber( "1a3.14" ) );
    }
    
    @Test
    public void test9() {
        Assert.assertFalse( isNumber( "1.2.3" ) );
    }
    
    @Test
    public void test10() {
        Assert.assertFalse( isNumber( "+-5" ) );
    }
    
    @Test
    public void test11() {
        Assert.assertFalse( isNumber( "12e+5.4" ) );
    }
    
    public boolean isNumber( String s ) {
        if (s == null) {
            return false;
        }
        s.trim();
        
        final int N = s.length();
        if (N == 0) {
            return false;
        }
        int i = 0;
        
        if (i < N && ( s.charAt( i ) == '-' || s.charAt( i ) == '+' )) {
            ++i;
        }
        
        while (i < N && s.charAt( i ) >= '0' && s.charAt( i ) <= '9') {
            ++i;
        }
        
        if (i < N && s.charAt( i ) == '.') {
            ++i;
        }
        
        while (i < N && s.charAt( i ) >= '0' && s.charAt( i ) <= '9') {
            ++i;
        }
        
        if (i < N && ( s.charAt( i ) == 'e' || s.charAt( i ) == 'E' )) {
            ++i;
        }
        
        if (i < N && ( s.charAt( i ) == '-' || s.charAt( i ) == '+' )) {
            ++i;
        }
        
        while (i < N && s.charAt( i ) >= '0' && s.charAt( i ) <= '9') {
            ++i;
        }
        if (i == N) {
            return true;
        }
        return false;
    }
}

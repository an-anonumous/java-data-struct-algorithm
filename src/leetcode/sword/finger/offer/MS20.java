package leetcode.sword.finger.offer;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
    
    @Test
    public void test12() {
        Assert.assertTrue( isNumber( "1 " ) );
    }
    
    /**
     * 暴力解法
     *
     * @param s
     * @return
     */
    public boolean isNumber( String s ) {
        if (s == null) {
            return false;
        }
        s = s.trim();
        
        final int N = s.length();
        if (N == 0) {
            return false;
        }
        int i = 0;
        
        boolean hasA = false, hasB = false, hasEC = false;
        
        if (i < N && ( s.charAt( i ) == '-' || s.charAt( i ) == '+' )) {
            ++i;
        }
        
        while (i < N && s.charAt( i ) >= '0' && s.charAt( i ) <= '9') {
            ++i;
            if (!hasA) {
                hasA = true;
            }
        }
        
        if (i < N && s.charAt( i ) == '.') {
            ++i;
            while (i < N && s.charAt( i ) >= '0' && s.charAt( i ) <= '9') {
                ++i;
                if (!hasB) {
                    hasB = true;
                }
            }
        }
        
        if (i < N && ( s.charAt( i ) == 'e' || s.charAt( i ) == 'E' )) {
            ++i;
            if (i < N && ( s.charAt( i ) == '-' || s.charAt( i ) == '+' )) {
                ++i;
            }
            
            while (i < N && s.charAt( i ) >= '0' && s.charAt( i ) <= '9') {
                ++i;
                if (!hasEC) {
                    hasEC = true;
                }
            }
        } else {
            hasEC = true;
        }
        
        if (i == N && ( hasA || hasB ) && hasEC) {
            return true;
        }
        return false;
    }
    
    public boolean isNumber2( String s ) {
        Map<State, Map<CharType, State>> transfer = new HashMap<State, Map<CharType, State>>();
        Map<CharType, State> initialMap = new HashMap<CharType, State>() {
            private static final long serialVersionUID = 4217034657965767480L;
    
            {
                put( CharType.CHAR_SPACE, State.STATE_INITIAL );
                put( CharType.CHAR_NUMBER, State.STATE_INTEGER );
                put( CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT );
                put( CharType.CHAR_SIGN, State.STATE_INT_SIGN );
            }
        };
        transfer.put( State.STATE_INITIAL, initialMap );
        Map<CharType, State> intSignMap = new HashMap<CharType, State>() {
            private static final long serialVersionUID = -3676091677088736233L;
            
            {
                put( CharType.CHAR_NUMBER, State.STATE_INTEGER );
                put( CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT );
            }
        };
        transfer.put( State.STATE_INT_SIGN, intSignMap );
        Map<CharType, State> integerMap = new HashMap<CharType, State>() {
            private static final long serialVersionUID = 5445681646572823145L;
            
            {
                put( CharType.CHAR_NUMBER, State.STATE_INTEGER );
                put( CharType.CHAR_EXP, State.STATE_EXP );
                put( CharType.CHAR_POINT, State.STATE_POINT );
                put( CharType.CHAR_SPACE, State.STATE_END );
            }
        };
        transfer.put( State.STATE_INTEGER, integerMap );
        Map<CharType, State> pointMap = new HashMap<CharType, State>() {
            private static final long serialVersionUID = 3470419663538033357L;
            
            {
                put( CharType.CHAR_NUMBER, State.STATE_FRACTION );
                put( CharType.CHAR_EXP, State.STATE_EXP );
                put( CharType.CHAR_SPACE, State.STATE_END );
            }
        };
        transfer.put( State.STATE_POINT, pointMap );
        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {
            private static final long serialVersionUID = 1592227181929669999L;
            
            {
                put( CharType.CHAR_NUMBER, State.STATE_FRACTION );
            }
        };
        transfer.put( State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap );
        Map<CharType, State> fractionMap = new HashMap<CharType, State>() {
            private static final long serialVersionUID = 2053854437695189478L;
            
            {
                put( CharType.CHAR_NUMBER, State.STATE_FRACTION );
                put( CharType.CHAR_EXP, State.STATE_EXP );
                put( CharType.CHAR_SPACE, State.STATE_END );
            }
        };
        transfer.put( State.STATE_FRACTION, fractionMap );
        Map<CharType, State> expMap = new HashMap<CharType, State>() {
            private static final long serialVersionUID = 5901095856009106221L;
            
            {
                put( CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER );
                put( CharType.CHAR_SIGN, State.STATE_EXP_SIGN );
            }
        };
        transfer.put( State.STATE_EXP, expMap );
        Map<CharType, State> expSignMap = new HashMap<CharType, State>() {
            private static final long serialVersionUID = 8816104438232088229L;
            
            {
                put( CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER );
            }
        };
        transfer.put( State.STATE_EXP_SIGN, expSignMap );
        Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {
            private static final long serialVersionUID = -3265739589865001068L;
            
            {
                put( CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER );
                put( CharType.CHAR_SPACE, State.STATE_END );
            }
        };
        transfer.put( State.STATE_EXP_NUMBER, expNumberMap );
        Map<CharType, State> endMap = new HashMap<CharType, State>() {
            private static final long serialVersionUID = 1864064938325525057L;
            
            {
                put( CharType.CHAR_SPACE, State.STATE_END );
            }
        };
        transfer.put( State.STATE_END, endMap );
        
        int length = s.length();
        State state = State.STATE_INITIAL;
        
        for (int i = 0; i < length; i++) {
            CharType type = toCharType( s.charAt( i ) );
            if (!transfer.get( state ).containsKey( type )) {
                return false;
            } else {
                state = transfer.get( state ).get( type );
            }
        }
        return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
    }
    
    public CharType toCharType( char ch ) {
        if (ch >= '0' && ch <= '9') {
            return CharType.CHAR_NUMBER;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.CHAR_EXP;
        } else if (ch == '.') {
            return CharType.CHAR_POINT;
        } else if (ch == '+' || ch == '-') {
            return CharType.CHAR_SIGN;
        } else if (ch == ' ') {
            return CharType.CHAR_SPACE;
        } else {
            return CharType.CHAR_ILLEGAL;
        }
    }
    
    private static enum State {
        STATE_INITIAL,
        STATE_INT_SIGN,
        STATE_INTEGER,
        STATE_POINT,
        STATE_POINT_WITHOUT_INT,
        STATE_FRACTION,
        STATE_EXP,
        STATE_EXP_SIGN,
        STATE_EXP_NUMBER,
        STATE_END,
    }
    
    private static enum CharType {
        CHAR_NUMBER,
        CHAR_EXP,
        CHAR_POINT,
        CHAR_SIGN,
        CHAR_SPACE,
        CHAR_ILLEGAL,
    }
    
}
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
    
    /**
     * 有限状态自动机
     *
     * 预备知识
     * 确定有限状态自动机（以下简称「自动机」）是一类计算模型。它包含一系列状态，这些状态中：有一个特殊的状态，被称作「初始状态」。还有一系
     * 列状态被称为「接受状态」，它们组成了一个特殊的集合。其中，一个状态可能既是「初始状态」，也是「接受状态」。起初，这个自动机处于「初始
     * 状态」。随后，它顺序地读取字符串中的每一个字符，并根据当前状态和读入的字符，按照某个事先约定好的「转移规则」，从当前状态转移到下一个
     * 状态；当状态转移完成后，它就读取下一个字符。当字符串全部读取完毕后，如果自动机处于某个「接受状态」，则判定该字符串「被接受」；否则，
     * 判定该字符串「被拒绝」。
     *
     * 注意：如果输入的过程中某一步转移失败了，即不存在对应的「转移规则」，此时计算将提前中止。在这种情况下我们也判定该字符串「被拒绝」。
     *
     * 一个自动机，总能够回答某种形式的「对于给定的输入字符串 S，判断其是否满足条件 P」的问题。在本题中，条件 P 即为「构成合法的表示数值的
     * 字符串」。自动机驱动的编程，可以被看做一种暴力枚举方法的延伸：它穷尽了在任何一种情况下，对应任何的输入，需要做的事情。自动机在计算机
     * 科学领域有着广泛的应用。在算法领域，它与大名鼎鼎的字符串查找算法「KMP」算法有着密切的关联；在工程领域，它是实现「正则表达式」的基础。
     *
     * 问题描述
     *          在 C++ 文档 中，描述了一个合法的数值字符串应当具有的格式。具体而言，它包含以下部分：
     *          符号位，即 ++、-− 两种符号
     *          整数部分，即由若干字符 0-90−9 组成的字符串
     *          小数点
     *          小数部分，其构成与整数部分相同
     *          指数部分，其中包含开头的字符 e（大写小写均可）、可选的符号位，和整数部分
     *          相比于 C++ 文档而言，本题还有一点额外的不同，即允许字符串首末两端有一些额外的空格。
     *
     * 在上面描述的五个部分中，每个部分都不是必需的，但也受一些额外规则的制约，如：
     *      如果符号位存在，其后面必须跟着数字或小数点。小数点的前后两侧，至少有一侧是数字。
     * 思路与算法
     *
     * 根据上面的描述，现在可以定义自动机的「状态集合」了。那么怎么挖掘出所有可能的状态呢？一个常用的技巧是，用「当前处理到字符串的哪个部分」当作状态的表述。根据这一技巧，不难挖掘出所有状态：
     *
     * 起始的空格
     * 符号位
     * 整数部分
     * 左侧有整数的小数点
     * 左侧无整数的小数点（根据前面的第二条额外规则，需要对左侧有无整数的两种小数点做区分）
     * 小数部分
     * 字符 \text{e}e
     * 指数部分的符号位
     * 指数部分的整数部分
     * 末尾的空格
     * 下一步是找出「初始状态」和「接受状态」的集合。根据题意，「初始状态」应当为状态 1，而「接受状态」的集合则为状态 3、状态 4、状态 6、状态 9 以及状态
     * 10。换言之，字符串的末尾要么是空格，要么是数字，要么是小数点，但前提是小数点的前面有数字。
     *
     * 最后，需要定义「转移规则」。结合数值字符串应当具备的格式，将自动机转移的过程以图解的方式表示出来：
     *
     *
     *
     * 比较上图与「预备知识」一节中对自动机的描述，可以看出有一点不同：
     *
     * 我们没有单独地考虑每种字符，而是划分为若干类。由于全部 1010 个数字字符彼此之间都等价，因此只需定义一种统一的「数字」类型即可。对于正负号也是同理。
     * 在实际代码中，我们需要处理转移失败的情况。例如当位于状态 1（起始空格）时，没有对应字符 \text{e}e
     * 的状态。为了处理这种情况，我们可以创建一个特殊的拒绝状态。如果当前状态下没有对应读入字符的「转移规则」，我们就转移到这个特殊的拒绝状态。一旦自动机转移到这个特殊状态，我们就可以立即判定该字符串不「被接受」。
     *
     * @param s
     * @return
     */
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
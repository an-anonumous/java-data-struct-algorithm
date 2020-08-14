package alibaba.al20200814;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class A02 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 9, conunts( new char[]{'9', '0'} ) );
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 1, conunts( new char[]{'0', '1'} ) );
    }
    
    /**
     * 刘惠阿里笔试第二题
     *
     * 对于任意一个数字字符串S,1<=i<=|S|,0<=S[i]<=9,定义如下两个操作：
     * 1. 对于任意的长度为2的连续子串，S[i],S[i+1],将S[i]的值 减一，同时将S[i+1]的值 加一，每个字符修取值范围必须在[0.9]之间，
     * 如23操作后为14,对于子串09操作无效；
     * 2. 对于任意的长度为2的连续子串，S[i],S[i+1],将S[i]的值 加一，同时将S[i+1]的值 减一，每个字符修取值范围必须在[0.9]之间，
     * 如23操作后为32，对于90子串则为非法操作。
     *
     * 如果两个字符串可以通过上述两个操作相互转换则这两个串是相似的。给定一个字符串求有多少个相似的串，输出很大需要模上10000 00007输出
     *
     * 注意相同的串只算一次
     *
     * @param data
     * @return
     */
    public long conunts( char[] data ) {
        if (data == null || data.length == 0) {
            return 0;
        }
        
        // long result = 1;
        BigInteger result = new BigInteger( "1" );
        for (int i = 0; i < data.length - 1; i++) {
            // +,- 可以执行操作二的次数
            int ops = Math.min( 9 - ( data[i] - '0' ), data[i + 1] - '0' - 0 );
            // -,+ 可以执行操作一的次数
            ops += Math.min( data[i] - '0' - 0, 9 - ( data[i + 1] - '0' ) );
            // result *= ops;
            result = result.multiply( new BigInteger( String.valueOf( ops ) ) );
        }
        // return result;
        return result.mod( new BigInteger( String.valueOf( "1000000007" ) ) ).intValue();
    }
}

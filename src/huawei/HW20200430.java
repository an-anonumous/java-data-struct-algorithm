package huawei;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取字符串无重复排列组合数量
 *
 * 要考虑到字符串有重复的字母
 * 字符数量阶乘 / 每一个字母数量阶乘
 * 使用 map 记录每一个字符的 count。
 *
 */
public class HW20200430 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 12, permutationNum( "baac" ) );
    }
    
    public int permutationNum( String str ) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt( i );
            if (map.containsKey( ch )) {
                map.put( ch, map.get( ch ) + 1 );
            } else {
                map.put( ch, 1 );
            }
        }
        
        int result = factorial( str.length() );
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int num = entry.getValue();
            if (num > 1) {
                result /= factorial( num );
            }
        }
        return result;
    }
    
    
    private int factorial( int n ) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
}

package leetcode.daily;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 93. 复原IP地址
 *
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 *
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class L93 {
    public static void main( String[] args ) {
        // System.out.println( Arrays.toString( "10.168.57.226".split( "\\." ) ) );
        System.out.println( "10.168.57.226".split( "\\." ).length );
    }
    
    @Test
    public void test1() {
        System.out.println( restoreIpAddresses( "25525511135" ) );
    }
    
    @Test
    public void test2() {
        System.out.println( restoreIpAddresses( "0000" ) );
    }
    
    @Test
    public void test3() {
        System.out.println( restoreIpAddresses( "1111" ) );
    }
    
    public List<String> restoreIpAddresses( String s ) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        
        List<String> list = new LinkedList<>();
        traceBack( s, 0, new StringBuilder(), list, 0 );
        return list;
    }
    
    private void traceBack( String s, int index, StringBuilder builder, List<String> list, int depth ) {
        if (depth >= 4 && index >= s.length()) {
            String ip = builder.toString().substring( 0, builder.length() - 1 );
            list.add( ip );
            return;
        }
        
        // 剪枝
        if (depth >= 4 || index >= s.length()) {
            return;
        }
        
        if (s.charAt( index ) == '0') {
            builder.append( "0." );
            traceBack( s, index + 1, builder, list, depth + 1 );
            builder.delete( builder.lastIndexOf( "0." ), builder.length() );
            return;
        }
        
        for (int i = index; i <= index + 2 && i < s.length(); i++) {
            String sub = s.substring( index, i + 1 );
            int n = Integer.valueOf( sub );
            if (n > 255 || n < 0) {
                continue;
            }
            builder.append( sub ).append( "." );
            traceBack( s, i + 1, builder, list, depth + 1 );
            builder.delete( builder.lastIndexOf( sub + "." ), builder.length() );
        }
    }
}

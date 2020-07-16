package leetcode.hot100;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例：
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 */
public class H22 {
    
    @Test
    public void test() {
        H22 h22 = new H22();
        System.out.println( h22.generateParenthesis( 3 ) );
    }
    
    public List<String> generateParenthesis( int n ) {
        List<String> answer = new LinkedList<>();
        traceBack( answer, new StringBuilder(), 0, 0, n );
        return answer;
    }
    
    /**
     * 使用回溯法生成序列
     *
     * @param answer   存储所有结果的链表
     * @param builder  当前正在生成的解
     * @param left     当前部分解中含有的左括号数
     * @param right    当前部分解中的右括号数
     * @param n
     */
    private void traceBack( List<String> answer, StringBuilder builder, int left, int right, final int n ) {
        // 递归结束条件
        if (right == n && left == n) {
            answer.add( builder.toString() );
            return;
        }
        
        // 尝试在当前位置生成左括号
        if (left < n) {
            builder.append( '(' );
            traceBack( answer, builder, left + 1, right, n );
            builder.delete( builder.length() - 1, builder.length() );  // 回溯
        }
        
        // 尝试在当前位置生成右括号
        if (left > right) {
            builder.append( ')' );
            traceBack( answer, builder, left, right + 1, n );
            builder.delete( builder.length() - 1, builder.length() );  // 回溯
        }
        
    }
}

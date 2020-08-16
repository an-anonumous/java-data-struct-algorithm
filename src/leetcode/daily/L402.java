package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 402. 移掉K位数字
 *
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 *
 * 示例 1 :
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 *
 * 示例 2 :
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 *
 * 示例 3 :
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class L402 {
    
    @Test
    public void test1() {
        Assert.assertEquals( "1219", removeKdigits( "1432219", 3 ) );
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( "200", removeKdigits( "10200", 1 ) );
    }
    
    @Test
    public void test3() {
        Assert.assertEquals( "0", removeKdigits( "10", 2 ) );
    }
    
    @Test
    public void test4() {
        Assert.assertEquals( "11", removeKdigits( "112", 1 ) );
    }
    
    /**
     * 单调栈
     *
     * 总体思路是从左边开始尽可能的让留下来的数字小，删除相邻数字中尽可能大的。A应该是最优先删除的。
     *      A        /\
     *      /\      /  \  /\
     *     /  \    /    \/  \  /
     *    /    \  /          \/
     *   /      \/
     *  /
     * /
     *
     * 对于两个相同长度的数字序列，最左边不同的数字决定了这两
     * 个数字的大小，例如，对于 A = 1axxx，B = 1bxxx，如果 a > b 则 A > B。知道了这个以后，我们可以想到，在删除数字时应该从左向
     * 右迭代。确定了迭代的顺序以后，就必须制定如何消除数字的标准，以便获得最小值。
     *
     * 让我们从一个简单的例子开始。给定一个数字序列，例如 425，如果要求我们只删除一个数字，那么从左到右，我们有 4、2 和 5 三个选择。
     * 我们将每一个数字和它的左邻居进行比较。从 2 开始，小于它的左邻居 4。则我们应该去掉数字 4。如果不这么做，则随后无论做什么，都
     * 不会得到最小数。如果我们保留数字 4，那么所有可能的组合都是以数字 4（即 42，45）开头的。相反，如果去掉 4，留下 2，我们得到的
     * 是以 2 开头的组合（即 25），这明显小于任何留下数字 4 的组合。
     *
     * 我们可以总结上述删除一个数字的规则，如下：给定一个数字序列 [D1，D2，D3，…，Dn]，如果数字 D2小于左邻居D1,则该删除D1以获得最
     * 小结果。
     *
     * 算法：
     * 上述的规则使得我们通过一个接一个的删除数字，逐步的接近最优解。这个问题可以用贪心算法来解决。上述规则阐明了我们如何接近最终答案
     * 的基本逻辑。一旦我们从序列中删除一个数字，剩下的数字就形成了一个新的问题，我们可以继续使用这个规则。我们会注意到，在某些情况下，
     * 规则对任意数字都不适用，即单调递增序列。在这种情况下，我们只需要删除末尾的数字来获得最小数。
     *
     * 我们可以利用栈来实现上述算法，存储当前迭代数字之前的数字。对于每个数字，如果该数字小于栈顶部，即该数字的左邻居，则弹出堆栈，即
     * 删除左邻居。否则，我们把数字推到栈上。我们重复上述步骤（1），直到任何条件不再适用，例如堆栈为空（不再保留数字）。或者我们已经删
     * 除了 k 位数字。
     *
     * @param num
     * @param k
     * @return
     */
    public String removeKdigits( String num, int k ) {
        Deque<Character> stack = new ArrayDeque<>();
        int i = 0, m = 0;
        while (i < num.length() && m < k) {
            while (!stack.isEmpty() && num.charAt( i ) < stack.peek() && m < k) {
                ++m;
                stack.pop();// 删除当前字符创最左边的高点
            }
            stack.push( num.charAt( i++ ) );
        }
        
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append( stack.pollLast() );
        }
        builder.append( num.substring( i ) );
        
        // 如果删除的字符数不够k，则从字符串最后删
        if (m < k && builder.length() > 0) {
            builder.delete( builder.length() - ( k - m ), builder.length() );
        }
        
        // 删除最前面的0
        while (builder.length() > 0 && builder.charAt( 0 ) == '0') {
            builder.delete( 0, 1 );
        }
        
        // 如果结果为空串则返回0
        if (builder.length() == 0) {
            return "0";
        }
        return builder.toString();
    }
}

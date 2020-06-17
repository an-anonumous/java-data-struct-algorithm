package SwordFingerOffer;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题14- I. 剪绳子
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 *
 * 2 <= n <= 58
 */
public class MS14_1 {

    /**
     * 暴力递归
     *
     * n=2:  1+1  -->1*1=1;
     * n=3:  2+1  -->2*1=2;
     * n=4:  2+2  -->2*2=4;
     *
     * @param n
     * @return
     */
    Map<Integer, Integer> map = new HashMap<>();

    public int recursive( int n ) {
// 从上面注释可以看出来，n为2,3,4时分割后的积比原数小，所以小于等于4时不再分割
        if (n<=4) {
            return n;
        }

        if (map.containsKey( n )) {
            return map.get( n );
        }

        int max = 0;
        for (int i = 1; i<n; i++) {
            max = Math.max( max, i*recursive( n-i ) );
        }

        map.put( n, max );
        return max;
    }

    public int cuttingRope( int n ) {
//        如果n为2,3，虽然分割后的积比原数字还小，但是题意必须分，且可以直接得出答案，故直接返回
        if (n==2) {
            return 1;
        } else if (n==3) {
            return 2;
        }

//        长度大于3的，暴力递归求解
        return recursive( n );
    }


    @Test
    public void Test() {
        MS14_1 ms14_1 = new MS14_1();
        Assert.assertEquals( 1, ms14_1.cuttingRope( 2 ) );
    }

    @Test
    public void Test1() {
        MS14_1 ms14_1 = new MS14_1();
        Assert.assertEquals( 36, ms14_1.cuttingRope( 10 ) );
    }
}

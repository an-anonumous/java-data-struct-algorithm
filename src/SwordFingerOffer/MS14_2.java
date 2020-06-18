package SwordFingerOffer;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

/**
 * 面试题14- II. 剪绳子 II
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。
 * 请问 k[0]*k[1]*...*k[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 示例 2: *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 *
 * 提示：
 * 2 <= n <= 1000
 */
public class MS14_2 {
    
    @Test
    public void Test() {
        MS14_2 ms14_1 = new MS14_2();
        Assert.assertEquals( 953271190, ms14_1.cuttingRope( 120 ) );
    }
    
    /**
     * n=2:  1+1  -->1*1=1;   				dp[2]=1;
     * n=3:  2+1  -->2*1=2;   				dp[3]=2;
     * n=4:  2+2  -->2*2=4;   				dp[4]=4;
     * n=5:  3+2  -->3*2=6;   				dp[5]=6;
     *
     * @param n
     * @return
     */
    public int cuttingRope( int n ) {
        if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        } else if (n == 4) {
            return 4;
        }
        
        // 如果绳子总长度大于3，剩下长度为小于等于3时，不拆要比拆分的值大
        
        // dp[k]代表长度为k的绳子，分割后的长度连乘的最大值
        BigInteger[] dp = new BigInteger[n + 1];
        for (int i = 0; i <= 4; i++) {
            dp[i] = new BigInteger( String.valueOf( i ) );
        }
        
        for (int i = 5; i <= n; i++) {
            dp[i] = new BigInteger( String.valueOf( 0 ) );
            for (int j = 1; j < i; j++) {
                BigInteger temp =
                        ( dp[i - j].multiply( BigInteger.valueOf( j ) ) ).mod( BigInteger.valueOf( 1000000007 ) );
                dp[i] = dp[i].max( temp );
            }
        }
        return (int) dp[n].mod( BigInteger.valueOf( 1000000007 ) ).intValue();
    }
}

package leetcode.hot100;

import org.junit.Assert;
import org.junit.Test;

/**
 * 70. 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 *
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 */
public class H70 {
    
    @Test
    public void test() {
        H70 h70 = new H70();
        Assert.assertEquals( 3, h70.climbStairs( 3 ) );
    }
    
    /**
     * f(x)=f(x−1)+f(x−2)
     *
     * @param n
     * @return
     */
    int climbStairs( int n ) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int a = 1, b = 2, c = a + b;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}

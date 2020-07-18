package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

/**
 * 714. 买卖股票的最佳时机含手续费
 *
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。 你可以无限次地完成交易，
 * 但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 示例 1:
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 *
 * 注意:
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 */
public class L714 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 8, maxProfit( new int[]{1, 3, 2, 8, 4, 9}, 2 ) );
    }
    
    /**
     * 使用辅助空间int dp[n][2],dp[i][0]表示第i天结束时手中已经没有股票，dp[i][1]表示第i天结束时手中持有股票。
     *
     * a. 对于dp[i][0]，第i天结束时手中没有股票可能是当天出出售了手中的股票，此时利润为dp[i-1][1]+prices[i];也可能是第i-1天结束时就
     * 已经没有了股票，此时利润为dp[i-1][0].
     *                          dp[i][0]=max(dp[i-1][1]+prices[i],dp[i-1][0]);
     * b. 对于dp[i][1]，第i天结束时手中还持有股票，可能是当天购买的，此时利润为：dp[i-1][0]-prices[i]-fee;也可能是i-1天就已经持有
     * 该股票了，dp[i-1][1].
     *                          dp[i][1]=max(dp[i-1][0]-prices[i]-fee,dp[i-1][1]);
     *
     * 边界：
     * dp[0][0]=0;
     * dp[0][1]=-prices[0]-fee;
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit( int[] prices, int fee ) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        final int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0] - fee;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max( dp[i - 1][1] + prices[i], dp[i - 1][0] );
            dp[i][1] = Math.max( dp[i - 1][0] - prices[i] - fee, dp[i - 1][1] );
        }
        return dp[n - 1][0];
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 8, maxProfit2( new int[]{1, 3, 2, 8, 4, 9}, 2 ) );
    }
    
    /**
     * 优化空间效率
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit2( int[] prices, int fee ) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        final int n = prices.length;
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = -prices[0] - fee;
        for (int i = 1; i < n; i++) {
            int a = dp[0], b = dp[1];
            dp[0] = Math.max( b + prices[i], a );
            dp[1] = Math.max( a - prices[i] - fee, b );
        }
        return dp[0];
    }
}

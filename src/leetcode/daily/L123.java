package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 123. 买卖股票的最佳时机 III
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。设计一个算法来计算你所能获取的最大利润。
 * 你最多可以完成 两笔 交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 *
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 */
public class L123 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 6, maxProfit( new int[]{3, 3, 5, 0, 0, 3, 1, 4} ) );
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 4, maxProfit( new int[]{1, 2, 3, 4, 5} ) );
    }
    
    @Test
    public void test3() {
        Assert.assertEquals( 0, maxProfit( new int[]{7, 6, 4, 3, 1} ) );
    }
    
    /**
     * 使用数组int dp[n][2 + 1][2]作为辅助空间(动态规划的表)，dp[i][k][0]表示在第i天结束时手中没有股票的最大收益，并且最大允许的交易次数为k;
     * dp[i][k][1]表示在第i天结束时手中持有股票时最大的利润，允许最大的交易次数为k.
     *
     * a. 第i天结束时手中没有股票，可能是第i天出售了手中的股票，此时收益为dp[i-1][k][1]+prices[i];也可能是前一天就已经不在持有股票了，
     * 利润为dp[i-1][k][0]。所以状态转移方程为：
     *                  dp[i][k][0]=max(dp[i-1][k][1]+prices[i],dp[i-1][k][0])
     * b. 第i天结束时手中持有股票，可能是当天购买的，此时利润为：dp[i-1][k][0]-prices[i]; 也可能是前一天就已经持有股票了，今天什么都没做,
     * 收益为：dp[i-1][k][1]。所以状态转移方程为：
     *                  dp[i][k][1]=max(dp[i-1][k-1][0]-prices[i],dp[i-1][k][1])
     *
     *  边界：
     *  dp[0][0][0]=0;
     *  dp[0][0][1]=-inf;
     *  dp[0][1][0]=0;
     *  dp[0][1][1]=-prices[i];
     *  dp[0][2][0]=0;
     *  dp[0][2][1]=-prices[i];
     *
     *  dp[*][0][0]=0;      // 不允许交易且不持有股票的情况下收益为0
     *  dp[*][0][1]=-inf;   // 不允许交易的情况下时不可能持有股票的
     *
     * @param prices
     * @return
     */
    public int maxProfit( int[] prices ) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        final int n = prices.length;
        
        int dp[][][] = new int[n][2 + 1][2];
        dp[0][0][0] = 0;
        dp[0][0][1] = -Integer.MIN_VALUE;
        dp[0][1][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][0] = 0;
        dp[0][2][1] = -prices[0];
        for (int i = 0; i < n; i++) {
            dp[i][0][0] = 0;      // 不允许交易且不持有股票的情况下收益为0
            dp[i][0][1] = -Integer.MIN_VALUE;   // 不允许交易的情况下时不可能持有股票的
        }
        
        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= 2; k++) {
                dp[i][k][0] = Math.max( dp[i - 1][k][1] + prices[i], dp[i - 1][k][0] );
                dp[i][k][1] = Math.max( dp[i - 1][k - 1][0] - prices[i], dp[i - 1][k][1] );
            }
        }
        
        for (int i = 0; i < n; i++) {
            System.out.println( Arrays.toString( dp[i][0] ) );
            System.out.println( Arrays.toString( dp[i][1] ) );
            System.out.println( Arrays.toString( dp[i][2] ) );
            System.out.println();
        }
        return dp[n - 1][2][0];
    }
}

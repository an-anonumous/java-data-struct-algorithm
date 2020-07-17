package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * 122. 买卖股票的最佳时机 II
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易
 * （多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * 提示：
 *
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 *
 */
public class L122 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 7, maxProfit( new int[]{7, 1, 5, 3, 6, 4} ) );
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 4, maxProfit( new int[]{1, 2, 3, 4, 5} ) );
    }
    
    @Test
    public void test3() {
        Assert.assertEquals( 0, maxProfit( new int[]{7, 6, 4, 3, 1} ) );
    }
    
    
    public int maxProfit( int[] prices ) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                sum += prices[i] - prices[i - 1];
            }
        }
        
        return sum;
    }
    
    @Test
    public void test4() {
        Assert.assertEquals( 7, dpMaxProfit( new int[]{7, 1, 5, 3, 6, 4} ) );
    }
    
    @Test
    public void test5() {
        Assert.assertEquals( 4, dpMaxProfit( new int[]{1, 2, 3, 4, 5} ) );
    }
    
    @Test
    public void test6() {
        Assert.assertEquals( 0, dpMaxProfit( new int[]{7, 6, 4, 3, 1} ) );
    }
    
    /**
     *
     * 使用dp[n][2]作为辅助空间，dp[i][0]表示第i天结束时手中持有股票情况下的最大利润；dp[i][1]表示第i天结束时手中没有股票的情况下最大的
     * 收益。
     *
     * a. 第i天结束时手中持有股票情况下,如果股票是第i天买的，则利润为：dp[i-1][1]-prices[i];如果不是第i天买入的则利润为：dp[i-1][0].
     *              dp[i][0]=max(dp[i-1][1]-prices[i],dp[i-1][0])
     * b. 第i天结束时手中没有股票的情况下，如果股票是当天出售的，则利润为dp[i-1][0]+prices[i];如果股票不是当天出售的则利润为：dp[i-1][1]。
     *              dp[i][1]=max(dp[i-1][0]+prices[i],dp[i-1][1]);
     *
     *
     * @param prices
     * @return
     */
    public int dpMaxProfit( int[] prices ) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        final int n = prices.length;
        int[][] dp = new int[n][2];
        
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max( dp[i - 1][1] - prices[i], dp[i - 1][0] );
            dp[i][1] = Math.max( dp[i - 1][0] + prices[i], dp[i - 1][1] );
        }
        
        for (int[] arr : dp) {
            System.out.println( Arrays.toString( arr ) );
        }
        
        return Math.max( dp[n - 1][0], dp[n - 1][1] );
    }
}

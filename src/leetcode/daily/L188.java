package leetcode.daily;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 188. 买卖股票的最佳时机 IV
 *
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *
 * 示例 2:
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 */
public class L188 {
    
    @Test
    public void test1() {
        Assert.assertEquals( 2, maxProfit( 2, new int[]{2, 4, 1} ) );
    }
    
    @Test
    public void test2() {
        Assert.assertEquals( 7, maxProfit( 2, new int[]{3, 2, 6, 5, 0, 3} ) );
    }
    
    @Test
    public void test3() throws IOException {
        String path = "E:\\codes\\java-data-struct-algorithm\\src\\leetcode\\daily\\L188-test3.data";
        BufferedReader reader = new BufferedReader( new FileReader( path ) );
        int k = Integer.valueOf( reader.readLine() );
        
        List<Integer> list = new LinkedList<>();
        for (String num : reader.readLine().split( "\\s*,\\s*" )) {
            list.add( Integer.valueOf( num.trim() ) );
        }
        
        int arr[] = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get( i );
        }
        
        System.out.println( maxProfit( k, arr ) );
    }
    
    /**
     * 一次交易由买入和卖出构成，至少需要两天。所以说有效的限制 k 应该不超过 n/2，如果超过，就没有约束作用了，相当于 k = +infinity。
     *
     * 使用int dp[n][K+1][2]作为辅助空间,dp[i][k][0]表示第i天结束的时候手中没有股票时的最大利润，且允许的最大交易次数为k;dp[i][k][1]
     * 表示第i天结束时，手中持有股票情况下的最大收益，且允许的最大最大交易次数为k.
     *
     * a. 对于dp[i][k][0]，第i天结束时手中没有股票可能是当天出售了持有的股票，此时利润为dp[i-1][k][1]+prices[i];也可能是昨天就已经空
     * 仓了，利润为dp[i-1][k][0].
     *              dp[i][k][0]=max(dp[i-1][k][1]+prices[i],dp[i-1][k][0]);
     *
     * b. 对于dp[i][k][1]，第i天结束时手中持有股票，可能是当天购买的股票，利润为dp[i-1][k-1][0]-prices[i];也可能是i-1天就已经持有股
     * 票了，利润为dp[i-1][k][1].
     *              dp[i][k][1]=max(dp[i-1][k-1][0]-prices[i],dp[i-1][k][1]);
     *
     * 边界
     * dp[0][0][0]=0;
     * dp[0][0][1]=-inf;
     * dp[0][1][0]=0;
     * dp[0][1][1]=-prices[0];
     * dp[0][2][0]=0;
     * dp[0][2][1]=-prices[0];
     * ....
     * ....
     * dp[0][K][0]=0;
     * dp[0][K][1]=-prices[0];
     *
     * dp[*][0][0]=0;
     * dp[*][0][1]=-inf;
     *
     * @param K
     * @param prices
     * @return
     */
    public int maxProfit( int K, int[] prices ) {
        if (K == 0 || prices == null || prices.length == 0) {
            return 0;
        }
        
        final int n = prices.length;
        K = Math.min( K, n / 2 );
        int[][][] dp = new int[n][K + 1][2];
        
        for (int i = 0; i < n; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;
        }
        for (int k = 1; k <= K; k++) {
            dp[0][k][0] = 0;
            dp[0][k][1] = -prices[0];
        }
        
        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= K; k++) {
                dp[i][k][0] = Math.max( dp[i - 1][k][1] + prices[i], dp[i - 1][k][0] );
                dp[i][k][1] = Math.max( dp[i - 1][k - 1][0] - prices[i], dp[i - 1][k][1] );
            }
        }
        
        // for (int i = 1; i < n; i++) {
        //     for (int k = 1; k <= K; k++) {
        //         System.out.println( Arrays.toString( dp[i][k] ) );
        //         System.out.println();
        //     }
        // }
        
        return dp[n - 1][K][0];
    }
    
    @Test
    public void test4() {
        Assert.assertEquals( 2, maxProfit2( 2, new int[]{2, 4, 1} ) );
    }
    
    @Test
    public void test5() {
        Assert.assertEquals( 7, maxProfit2( 2, new int[]{3, 2, 6, 5, 0, 3} ) );
    }
    
    @Test
    public void test6() throws IOException {
        String path = "E:\\codes\\java-data-struct-algorithm\\src\\leetcode\\daily\\L188-test3.data";
        BufferedReader reader = new BufferedReader( new FileReader( path ) );
        int k = Integer.valueOf( reader.readLine() );
        
        List<Integer> list = new LinkedList<>();
        for (String num : reader.readLine().split( "\\s*,\\s*" )) {
            list.add( Integer.valueOf( num.trim() ) );
        }
        
        int arr[] = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get( i );
        }
        
        System.out.println( maxProfit2( k, arr ) );
    }
    
    /**
     * 优化空间效率
     *
     * 由于每次更新状态只需要上一次的状态，所以dp数组长度可以有n变为1
     *
     * @param K
     * @param prices
     * @return
     */
    public int maxProfit2( int K, int[] prices ) {
        if (K == 0 || prices == null || prices.length == 0) {
            return 0;
        }
        
        final int n = prices.length;
        K = Math.min( K, n / 2 );
        int[][] dp = new int[K + 1][2];
        
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        for (int k = 1; k <= K; k++) {
            dp[k][0] = 0;
            dp[k][1] = -prices[0];
        }
        
        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= K; k++) {
                dp[k][0] = Math.max( dp[k][1] + prices[i], dp[k][0] );
                dp[k][1] = Math.max( dp[k - 1][0] - prices[i], dp[k][1] );
            }
        }
        
        return dp[K][0];
    }
}

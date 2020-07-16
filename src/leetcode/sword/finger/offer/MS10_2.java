package leetcode.sword.finger.offer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题10- II. 青蛙跳台阶问题
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 提示：
 *
 * 0 <= n <= 100
 */

/**
 * 解题思路：
 *
 * 设跳上 n 级台阶有 f(n) 种跳法。在所有跳法中，青蛙的最后一步只有两种情况： 跳上 1 级或 2 级台阶。
 * 当为 1 级台阶： 剩 n−1 个台阶，此情况共有 f(n−1) 种跳法；
 * 当为 2 级台阶： 剩 n−2 个台阶，此情况共有 f(n−2) 种跳法。
 * f(n) 为以上两种情况之和，即 f(n)=f(n-1)+f(n-2) ，以上递推性质为斐波那契数列。本题可转化为 求斐波那契数列第 n 项的值，
 * 与面试题10-I 斐波那契数列 等价，唯一的不同在于起始数字不同。
 *
 * 青蛙跳台阶问题： f(0)=1 , f(1)=1 , f(2)=2 ；
 * 斐波那契数列问题： f(0)=0 , f(1)=1 , f(2)=1 。
 *
 */
public class MS10_2 {
    
    Map<Integer, Integer> map = new HashMap<>();
    int callNum1;
    int callNum2;
    
    @Test
    public void test1() {
        System.out.println(new MS10_2().numWays(0));
        System.out.println(new MS10_2().numWays(2));
        System.out.println(new MS10_2().numWays(7));
        System.out.println(new MS10_2().numWays(1000));
    }
    
    @Test
    public void test() {
        MS10_2 ms102 = new MS10_2();
        int callNum1 = 0;
        System.out.println(ms102.numWays(10));
        System.out.println("\n callNum1 = " + ms102.callNum1);//17
        
        int callNum2 = 0;
        System.out.println(ms102.recursiveNumWays(10));
        System.out.println(" callNum2 = " + ms102.callNum2); //109
    }
    
    @Test
    public void test2() {
        MS10_2 ms102 = new MS10_2();
        int callNum1 = 0;
        System.out.println(ms102.numWays(20));
        System.out.println("\n callNum1 = " + ms102.callNum1);//37
        
        int callNum2 = 0;
        System.out.println(ms102.recursiveNumWays(20));
        System.out.println(" callNum2 = " + ms102.callNum2);//13529
    }
    
    @Test
    public void test3() {
        MS10_2 ms102 = new MS10_2();
        int callNum1 = 0;
        System.out.println(ms102.numWays(30));
        System.out.println("\n callNum1 = " + ms102.callNum1);// 57
        
        int callNum2 = 0;
        System.out.println(ms102.recursiveNumWays(30));
        System.out.println(" callNum2 = " + ms102.callNum2);// 1664079
    }
    
    /**
     * 记忆递归
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        // System.out.print("@");
        callNum1++;
        if (n == 0) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 1) {
            return 1;
        }
        
        // 减少重复计算
        if (map.containsKey(n)) {
            return map.get(n);
        }
        
        
        int steps = (numWays(n - 1) + numWays(n - 2)) % 1000000007;
        map.put(n , steps);
        return steps;
    }
    
    /**
     * 纯递归
     *
     * @param n
     * @return
     */
    public int recursiveNumWays(int n) {
        // System.out.print("#");
        callNum2++;
        if (n == 0) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 1) {
            return 1;
        }
        
        return (recursiveNumWays(n - 1) + recursiveNumWays(n - 2)) % 1000000007;
    }
    
    /**
     * 暴力动态规划
     *
     * @param n
     * @return
     */
    public int dpNumWays(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 1) {
            return 1;
        }
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        
        return dp[n];
    }
    
    public int dpNumWays2(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        
        int a = 1, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return sum;
    }
}

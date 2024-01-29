package org.example.base.helper.alg;

/**
 * @author panfudong
 * @description 股票买卖最佳时机
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 */
public class MaxProfitsTest {

    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][prices.length];
        int result = 0;
        for (int j = 1; j < prices.length; j++) {
            dp[0][j] = prices[j] - prices[0];
            result = Math.max(result, dp[0][j]);
        }

        for (int i = 1; i < prices.length - 1; i++) {
            for (int j = i+1; j < prices.length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j] - (prices[i] - prices[i -1]), dp[i][j - 1] + (prices[j] - prices[j - 1]));
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
    }
}

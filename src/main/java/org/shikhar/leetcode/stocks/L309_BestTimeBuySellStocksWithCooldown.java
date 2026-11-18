package org.shikhar.leetcode.stocks;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 * 
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * 
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 * 
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * 
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 * 
 * Input: prices = [1,2,3,0,2]    Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * 
 * Input: prices = [1]             Output: 0
 */
public class L309_BestTimeBuySellStocksWithCooldown {
    public static int maxProfit(int[] prices) {
        Map<String, Integer> dp = new HashMap<>();

        return dfs(prices, 0, true, dp);
    }

    private static int dfs(int[] prices, int index, boolean buying, Map<String, Integer> dp) {
        if (index >= prices.length) {
            return 0;
        }

        String key = index + "-" + buying;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        if (buying) {
            int buy = dfs(prices, index + 1, false, dp) - prices[index];
            int cooldown = dfs(prices, index + 1, buying, dp);
            dp.put(key, Math.max(buy, cooldown));
        } else {
            int sell = dfs(prices, index + 2, true, dp) + prices[index];
            int cooldown = dfs(prices, index + 1, buying, dp);
            dp.put(key, Math.max(sell, cooldown));
        }

        return dp.get(key);
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        assertEquals(L309_BestTimeBuySellStocksWithCooldown.maxProfit(new int[]{1,2,3,0,2}), 3, "Test Case 1");
        assertEquals(L309_BestTimeBuySellStocksWithCooldown.maxProfit(new int[]{1}), 0, "Test Case 2");
    }

    private static void assertEquals(int result, int expected, String s) {
        if (result != expected) {
            System.out.printf("Expected %d but got %d for input: %s\n", expected, result, s);
        } else {
            System.out.printf("Test passed for input: %s\n", s);
        }
    }
}

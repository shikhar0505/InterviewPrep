package com.shikhar.interviewprep.stocks;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 * 
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * 
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
 * However, you can sell and buy the stock multiple times on the same day, ensuring you never hold more than one share of the stock.
 * 
 * Find and return the maximum profit you can achieve.
 * 
 * Input: prices = [7,1,5,3,6,4]    Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 * 
 * Input: prices = [1,2,3,4,5]     Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 * 
 * Input: prices = [7,6,4,3,1]     Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 */
public class L122_BestTimeBuySellStocksII {
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                maxProfit += (prices[i + 1] - prices[i]);
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        assertEquals(L122_BestTimeBuySellStocksII.maxProfit(new int[]{7, 1, 5, 3, 6, 4}), 7, "Test Case 1");
        assertEquals(L122_BestTimeBuySellStocksII.maxProfit(new int[]{1, 2, 3, 4, 5}), 4, "Test Case 2");
        assertEquals(L122_BestTimeBuySellStocksII.maxProfit(new int[]{7, 6, 4, 3, 1}), 0, "Test Case 3");
    }

    private static void assertEquals(int result, int expected, String s) {
        if (result != expected) {
            System.out.printf("Expected %d but got %d for input: %s\n", expected, result, s);
        } else {
            System.out.printf("Test passed for input: %s\n", s);
        }
    }
}

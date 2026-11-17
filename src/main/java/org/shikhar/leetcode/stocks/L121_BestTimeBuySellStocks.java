package com.shikhar.interviewprep.stocks;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 * 
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 * 
 * Input: prices = [7,1,5,3,6,4]    Output: 5
 * Input: prices = [7,6,4,3,1]     Output: 0
 */
public class L121_BestTimeBuySellStocks {
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;

        int buyPrice = prices[0];
        for (int sellPrice : prices) {
            maxProfit = Math.max(maxProfit, sellPrice - buyPrice);
            buyPrice = Math.min(buyPrice, sellPrice);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        assertEquals(L121_BestTimeBuySellStocks.maxProfit(new int[]{7,1,5,3,6,4}), 5, "Test Case 1");
        assertEquals(L121_BestTimeBuySellStocks.maxProfit(new int[]{7,6,4,3,1}), 0, "Test Case 2");
        assertEquals(L121_BestTimeBuySellStocks.maxProfit(new int[]{10,1,5,6,7,1}), 6, "Test Case 3");
        assertEquals(L121_BestTimeBuySellStocks.maxProfit(new int[]{10,8,7,5,2}), 0, "Test Case 4");
    }

    private static void assertEquals(int result, int expected, String s) {
        if (result != expected) {
            System.out.printf("Expected %d but got %d for input: %s\n", expected, result, s);
        } else {
            System.out.printf("Test passed for input: %s\n", s);
        }
    }
}

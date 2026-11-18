package org.shikhar.leetcode.array;

/**
 * https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/description/
 * 
 * You are given an array of integers nums. Return the length of the longest subarray of nums which is either:
 * - strictly increasing
 * - or strictly decreasing.
 * 
 * Input: nums = [1,4,3,3,2]            Output: 2
 * Explanation:
 * The strictly increasing subarrays of nums are [1], [2], [3], [3], [4], and [1,4].
 * The strictly decreasing subarrays of nums are [1], [2], [3], [3], [4], [3,2], and [4,3].
 * 

Hence, we return 2.
 */
public class L3105_LongestStrictlyIncreasingStrictlyDecreasingSubarray {
    public static int longestMonotonicSubarray(int[] nums) {
        int incrCount = 0;
        int decrCount = 0;

        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            while (j < nums.length && nums[j] > nums[j - 1]) {
                j++;
            }
            incrCount = Math.max(incrCount, j - i);

            j = i + 1;
            while (j < nums.length && nums[j] < nums[j - 1]) {
                j++;
            }
            decrCount = Math.max(decrCount, j - i);
        }

        return Math.max(incrCount, decrCount);
    }

    public static void main(String[] args) {
        runTestCases();
    }

    private static void runTestCases() {
        assertEqual(2, longestMonotonicSubarray(new int[]{1, 4, 3, 3, 2}), "Test Case 1");
        assertEqual(5, longestMonotonicSubarray(new int[]{1, 2, 3, 4, 5}), "Test Case 2");
        assertEqual(5, longestMonotonicSubarray(new int[]{5, 4, 3, 2, 1}), "Test Case 3");
        assertEqual(1, longestMonotonicSubarray(new int[]{1}), "Test Case 4");
        assertEqual(2, longestMonotonicSubarray(new int[]{1, 2, 1}), "Test Case 5");
        assertEqual(1, longestMonotonicSubarray(new int[]{3, 3, 3, 3}), "Test Case 6");
        assertEqual(3, longestMonotonicSubarray(new int[]{3, 2, 1}), "Test Case 7");
    }

    private static void assertEqual(int expected, int actual, String testCase) {
        if (expected != actual) {
            throw new AssertionError("Test case failed: " + testCase + ". Expected: " + expected + ", but got: " + actual);
        } else {
            System.out.println("Test case passed: " + testCase);
        }
    }
}

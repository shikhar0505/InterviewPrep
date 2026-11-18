package org.shikhar.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class L1_TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> diff = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            diff.put(target - nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (diff.containsKey(nums[i]) && diff.get(nums[i]) != i) {
                return new int[] {i, diff.get(nums[i])};
            }
        }
        
        // this will never reach
        return new int[] {};
    }

    public static void main(String[] args) {
        runTestCases();
    }

    private static void runTestCases() {
        assertEqual(new int[]{0, 1}, twoSum(new int[]{2, 7, 11, 15}, 9), "Test Case 1");
        assertEqual(new int[]{1, 2}, twoSum(new int[]{3, 2, 4}, 6), "Test Case 2");
        assertEqual(new int[]{0, 1}, twoSum(new int[]{3, 3}, 6), "Test Case 3");
    }

    private static void assertEqual(int expected[], int actual[], String testCase) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("Test case failed: " + testCase + ". Expected: " + expected + ", but got: " + actual);
        } else {
            System.out.println("Test case passed: " + testCase);
        }
    }
}

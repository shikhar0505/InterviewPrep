package com.shikhar.interviewprep.palindrome;

/**
 * https://leetcode.com/problems/maximum-palindromes-after-operations/description/
 * 
 * You are given a 0-indexed string array words having length n and containing 0-indexed strings.
 * 
 * You are allowed to perform the following operation any number of times (including zero):
 * 
 * Choose integers i, j, x, and y such that 0 <= i, j < n, 0 <= x < words[i].length, 0 <= y < words[j].length, and swap
 * the characters words[i][x] and words[j][y].
 * 
 * Return an integer denoting the maximum number of palindromes words can contain, after performing some operations.
 * 
 * Note: i and j may be equal during an operation.
 */
public class L3035_MaxalindromesAfterOperations {
    public int maxPalindromesAfterOperations(String[] words) {
        int[] charCount = new int[26]; // Count of each character across all words
        int oddCount = 0; // Count of characters with odd occurrences

        // Count the frequency of each character in all words
        for (String word : words) {
            for (char c : word.toCharArray()) {
                charCount[c - 'a']++;
            }
        }

        // Count how many characters have an odd frequency
        for (int count : charCount) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }

        // The maximum number of palindromes is the total number of words minus the number of odd counts
        return words.length - Math.max(0, oddCount - 1);
    }
}

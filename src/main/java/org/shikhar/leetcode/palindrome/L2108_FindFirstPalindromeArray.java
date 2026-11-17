package com.shikhar.interviewprep.palindrome;

/**
 * https://leetcode.com/problems/find-first-palindromic-string-in-the-array/description/
 * 
 * Given an array of strings words, return the first palindromic string in the array. If there is no such string, return an empty string "".
 * A string is palindromic if it reads the same forward and backward.
 */
public class L2108_FindFirstPalindromeArray {
    public String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindrome(word)) {
                return word; // Return the first palindromic string found
            }
        }
        return ""; // No palindromic string found
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false; // Not a palindrome
            }
            left++;
            right--;
        }
        return true; // Is a palindrome
    }
}

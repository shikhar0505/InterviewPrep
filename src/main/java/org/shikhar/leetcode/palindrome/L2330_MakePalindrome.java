package com.shikhar.interviewprep.palindrome;

/**
 * https://leetcode.com/problems/valid-palindrome-iv/description/
 * 
 * You are given a 0-indexed string s consisting of only lowercase English letters. In one operation, you can change any character of s to any other character.
 * Return true if you can make s a palindrome after performing exactly one or two operations, or return false otherwise.
 */
public class L2330_MakePalindrome {
    
    public boolean makePalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        int mismatchCount = 0;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                mismatchCount++;
                if (mismatchCount > 2) {
                    return false; // More than two mismatches, cannot make it a palindrome
                }
            }
            left++;
            right--;
        }

        return true; // Can make it a palindrome with at most two operations
    }
}

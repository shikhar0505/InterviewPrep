package com.shikhar.interviewprep.palindrome;

/**
 * https://leetcode.com/problems/valid-palindrome/description
 * 
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing
 * all non-alphanumeric characters, it reads the same forward and backward.
 * Alphanumeric characters include letters and numbers.
 * 
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * 
 * Input: s = "A man, a plan, a canal: Panama"                  Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 */
public class L125_ValidPalindrome {
    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        
        for (int i = 0, j = s.length() - 1; i < j;) {
            if (!isAlphaNumeric(s.charAt(i))) {
                i++;
            } else if (!isAlphaNumeric(s.charAt(j))) {
                j--;
            } else {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;
    }

    private static boolean isAlphaNumeric(char c) {
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s)); // Output: true
    }
}

package com.shikhar.interviewprep.palindrome;

/**
 * https://leetcode.com/problems/valid-palindrome-ii/
 * 
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 */
public class L680_PalindromeWithDeletion {
    public static boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        L680_PalindromeWithDeletion.runTests();
    }

    private static void runTests() {
        // 1. Happy Path
        assertEquals(true, validPalindrome("abca"), "Standard case with one deletion");
        assertEquals(true, validPalindrome("racecar"), "Already a palindrome");
        assertEquals(true, validPalindrome("deified"), "Already a palindrome");
        assertEquals(false, validPalindrome("hello"), "Not a palindrome even with one deletion");

        // 2. Edge Case: Empty and Single Character
        assertEquals(true, validPalindrome(""), "Empty string is a palindrome");
        assertEquals(true, validPalindrome("a"), "Single character is a palindrome");

        // 3. Edge Case: Two Characters
        assertEquals(true, validPalindrome("aa"), "Two identical characters are a palindrome");
        assertEquals(true, validPalindrome("ab"), "Two different characters can be made a palindrome by deleting one");
        assertEquals(true, validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"), "Long string that becomes a palindrome with one deletion");

        System.out.println("ALL TESTS PASSED!");
    }

    private static void assertEquals(boolean expected, boolean actual, String message) {
        if (expected != actual) {
            throw new AssertionError("Test failed: " + message + ". Expected: " + expected + ", but got: " + actual);
        }
    }
}

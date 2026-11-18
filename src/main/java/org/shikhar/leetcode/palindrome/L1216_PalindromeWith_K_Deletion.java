package org.shikhar.leetcode.palindrome;

/**
 * https://leetcode.com/problems/valid-palindrome-iii/description/
 * 
 * Given a string s and an integer k, return true if s is a k-palindrome.
 * A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 */
public class L1216_PalindromeWith_K_Deletion {
   public boolean isValidPalindrome(String s, int k) {
        // Calculate the minimum number of deletions required to make the string a palindrome
        int n = s.length();

        // Create a 2D array to store the minimum deletions required for substrings
        int[][] dp = new int[n][n];

        for (int len = 1; len <= n; len++) {

            // Iterate over all substrings of length len
            for (int i = 0; i <= n - len; i++) {

                // Calculate the ending index of the substring
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    // If the characters at the start and end of the substring are the same, no deletion is needed
                    dp[i][j] = (len == 1) ? 0 : dp[i + 1][j - 1];
                } else {
                    // If the characters are different, we need to delete one of them and take the minimum deletions required for the remaining substring
                    dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        // Check if the minimum deletions required is less than or equal to k
        return dp[0][n - 1] <= k;
    } 

    public static void main(String[] args) {
        L1216_PalindromeWith_K_Deletion solution = new L1216_PalindromeWith_K_Deletion();
        solution.runTests();
    }

    private void runTests() {
        // 1. Happy Path
        assertEquals(true, isValidPalindrome("abcdeca", 2), "Standard case with two deletions");
        assertEquals(true, isValidPalindrome("abca", 1), "Standard case with one deletion");
        assertEquals(true, isValidPalindrome("racecar", 0), "Already a palindrome");
        assertEquals(false, isValidPalindrome("hello", 1), "Not a palindrome even with one deletion");

        // 2. Edge Case: Empty and Single Character
        assertEquals(true, isValidPalindrome("a", 0), "Single character is a palindrome");

        // 3. Edge Case: Two Characters
        assertEquals(true, isValidPalindrome("aa", 0), "Two identical characters are a palindrome");
        assertEquals(true, isValidPalindrome("ab", 1), "Two different characters can be made a palindrome by deleting one");
        assertEquals(false, isValidPalindrome("ab", 0), "Two different characters cannot be made a palindrome without deletion");

        System.out.println("ALL TESTS PASSED!");
    }

    private static void assertEquals(boolean expected, boolean actual, String message) {
        if (expected != actual) {
            throw new AssertionError("Test failed: " + message + ". Expected: " + expected + ", but got: " + actual);
        }
    }
}

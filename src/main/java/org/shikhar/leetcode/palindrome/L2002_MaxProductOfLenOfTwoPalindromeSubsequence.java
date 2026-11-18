package org.shikhar.leetcode.palindrome;

/**
 * https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/description/
 * 
 * Given a string s, find two disjoint palindromic subsequences of s such that the product of their lengths is maximized.
 * The two subsequences are disjoint if they do not both pick a character at the same index.
 * 
 * Return the maximum possible product of the lengths of the two palindromic subsequences.
 * 
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order
 * of the remaining characters. A string is palindromic if it reads the same forward and backward.
 */
public class L2002_MaxProductOfLenOfTwoPalindromeSubsequence {
    public int maxProduct(String s) {
        int n = s.length();
        int maxProduct = 0;

        // Iterate through all possible subsequences using bitmasking
        for (int mask1 = 1; mask1 < (1 << n); mask1++) {
            StringBuilder sb1 = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if ((mask1 & (1 << i)) != 0) {
                    sb1.append(s.charAt(i));
                }
            }
            String subseq1 = sb1.toString();
            if (!isPalindrome(subseq1)) continue;

            for (int mask2 = 1; mask2 < (1 << n); mask2++) {
                if ((mask1 & mask2) != 0) continue; // Ensure disjoint subsequences

                StringBuilder sb2 = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    if ((mask2 & (1 << i)) != 0) {
                        sb2.append(s.charAt(i));
                    }
                }
                String subseq2 = sb2.toString();
                if (!isPalindrome(subseq2)) continue;

                maxProduct = Math.max(maxProduct, subseq1.length() * subseq2.length());
            }
        }

        return maxProduct;
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

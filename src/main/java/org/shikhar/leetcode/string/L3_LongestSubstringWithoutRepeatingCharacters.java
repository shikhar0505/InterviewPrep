package com.shikhar.interviewprep.string;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description
 * 
 * Given a string s, find the length of the longest substring without duplicate characters.
 * 
 * Input: s = "abcabcbb"            Output: 3
 * Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.
 * 
 * Input: s = "bbbbb"               Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * 
 * Input: s = "pwwkew"              Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class L3_LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        int l = 0;
        Set<Character> set = new HashSet<>();

        int maxLength = 0;
        for (int r = 0; r < s.length(); r++) {

            // notice the `while` below
            while (set.contains(s.charAt(r))) {
                set.remove(s.charAt(l));
                l++;
            }
            set.add(s.charAt(r));
            maxLength = Math.max(maxLength, r - l + 1);
        }
        return maxLength;
    }
    
    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        assertEquals(L3_LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("abcabcbb"), 3, "abcabcbb");
        assertEquals(L3_LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("bbbbb"), 1, "bbbbb");
        assertEquals(L3_LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("pwwkew"), 3, "pwwkew");
        assertEquals(L3_LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("pwwkew"), 3, "pwwkew");
        assertEquals(L3_LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("zxyzxyz"), 3, "zxyzxyz");
        assertEquals(L3_LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring("xxxx"), 1, "xxxx");
    }

    private static void assertEquals(int actual, int expected, String s) {
        if (actual != expected) {
            System.out.printf("Expected %d but got %d for input: %s\n", expected, actual, s);
        } else {
            System.out.printf("Test passed for input: %s\n", s);
        }
    }
}

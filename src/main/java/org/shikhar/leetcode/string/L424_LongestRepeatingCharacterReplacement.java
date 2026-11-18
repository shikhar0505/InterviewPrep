package org.shikhar.leetcode.string;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-repeating-character-replacement/description/
 * 
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other
 * uppercase English character. You can perform this operation at most k times.
 * 
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 * 
 * Input: s = "ABAB", k = 2      Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * 
 * Input: s = "AABABBA", k = 1   Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achieve this answer too.
 */
public class L424_LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k) {
        Map<Character, Integer> freqMap = new HashMap<>();

        int left = 0;
        int result = 0;
        
        for (int right = 0; right < s.length(); right++) {

            // incr frequency of char at `right` pointer
            freqMap.put(s.charAt(right), freqMap.getOrDefault(s.charAt(right), 0) + 1);
            
            // check if the current window is valid or not
            // by checking if the number of characters to be replaced is less than or equal to k

            // here, Collections.max(freqMap.values()) = freq of the most freq char in the current window
            while ((right - left + 1) - Collections.max(freqMap.values()) > k) {

                // decr frequency of char at `left` pointer
                freqMap.put(s.charAt(left), freqMap.get(s.charAt(left)) - 1);
                
                // shrink the window from the left
                left++;
            }
            // update the result with the length of the current valid window
            result = Math.max(result, right - left + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        assertEquals(L424_LongestRepeatingCharacterReplacement.characterReplacement("ABAB", 2), 4, "Test Case 1");
        assertEquals(L424_LongestRepeatingCharacterReplacement.characterReplacement("AABABBA", 1), 4, "Test Case 2");
        assertEquals(L424_LongestRepeatingCharacterReplacement.characterReplacement("XYYX", 2), 4, "Test Case 3");
        assertEquals(L424_LongestRepeatingCharacterReplacement.characterReplacement("AAABABB", 1), 5, "Test Case 4");
    }

    private static void assertEquals(int actual, int expected, String s) {
        if (actual != expected) {
            System.out.printf("Expected %d but got %d for input: %s\n", expected, actual, s);
        } else {
            System.out.printf("Test passed for input: %s\n", s);
        }
    }
}

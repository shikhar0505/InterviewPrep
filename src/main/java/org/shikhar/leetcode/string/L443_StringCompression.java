package com.shikhar.interviewprep.string;

import java.util.Objects;

/**
 * https://leetcode.com/problems/string-compression/description/
 * 
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 * If the group's length is 1, append the character to s.
 * Otherwise, append the character followed by the group's length.
 * 
 * Input: chars = ["a","a","b","b","c","c","c"]
 * Output: 6
 * Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
 * After modifying the input array in-place, the first 6 characters of chars should be ["a","2","b","2","c","3"].
 */
public class L443_StringCompression {
    public static int compress(char[] chars) {
        int i = 0;
        int res = 0;
        while (i < chars.length) {
            int j = i + 1;
            while (j < chars.length && chars[j] == chars[i]) {
                j++;
            }
            int length = j - i;
            
            chars[res] = chars[i];
            res++;

            if (length > 1) {
                // ith char appeared more than once
                for (char c : String.valueOf(length).toCharArray()) {
                    chars[res] = c;
                    res++;
                }
            }
            i += length;
        }
        return res;
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        // 1. Happy Path
        assertEquals("a2bc5a3".length(), compress("aabcccccaaa".toCharArray()), "Standard compression");
        
        // 2. Edge Case: No compression benefit
        assertEquals("abc".length(), compress("abc".toCharArray()), "Should return original if not smaller");
        
        // 3. Edge Case: Empty and Null
        assertEquals("".length(), compress("".toCharArray()), "Empty string check");
        //assertEquals(0, compress(null), "Null pointer check");
        
        // 4. Edge Case: Single character & Uniform string
        assertEquals("a".length(), compress("a".toCharArray()), "Single character string");
        assertEquals("a5".length(), compress("aaaaa".toCharArray()), "All identical characters");
        
        // 5. Edge Case: Case sensitivity
        assertEquals("a2A2".length(), compress("aaAA".toCharArray()), "Case sensitivity preservation");

        System.out.println("ALL TESTS PASSED!");
    }

    private static void assertEquals(int expected, int actual, String testName) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError(String.format("FAILED [%s]: Expected '%s', but got '%s'", 
                    testName, expected, actual));
        }
        System.out.println("PASSED: " + testName);
    }
}

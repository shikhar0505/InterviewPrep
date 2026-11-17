package com.shikhar.interviewprep.string;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/permutation-in-string/description/
 * 
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * 
 * In other words, return true if one of s1's permutations is the substring of s2.
 * 
 * Input: s1 = "ab", s2 = "eidbaooo"    Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 * 
 * Input: s1 = "ab", s2 = "eidboaoo"    Output: false
 * 
 * Input: s1 = "abc", s2 = "lecabee"    Output: true
 * 
 * Input: s1 = "abc", s2 = "lecaabee"   Output: false
 */
public class L567_PermutationInString {
    public static boolean checkInclusion(String s1, String s2) {
        // s1 char freq mapping
        Map<Character, Integer> s1CharMap = getCharMap(s1);

        for (int i = 0; i + s1.length() - 1 < s2.length(); i++) {
            while (i < s2.length() && !s1CharMap.containsKey(s2.charAt(i))) {
                i++;
            }

             // i is either out-of-bounds or at beginning of a potential valid substring
            if (i < s2.length() && i + s1.length() - 1 < s2.length()) {
                // get the next window as long as s1
                int j = i + s1.length() - 1;

                // get char mapping of the window and compare
                Map<Character, Integer> s2CharMap = getCharMap(s2.substring(i, j + 1));

                if (s1CharMap.equals(s2CharMap)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static Map<Character, Integer> getCharMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        assertEquals(L567_PermutationInString.checkInclusion("ab", "eidbaooo"), true, "Test Case 1");
        assertEquals(L567_PermutationInString.checkInclusion("ab", "eidboaoo"), false, "Test Case 2");
        assertEquals(L567_PermutationInString.checkInclusion("abc", "lecabee"), true, "Test Case 3");
        assertEquals(L567_PermutationInString.checkInclusion("abc", "lecaabee"), false, "Test Case 4");
    }

    private static void assertEquals(boolean result, boolean expected, String s) {
        if (result != expected) {
            System.out.printf("Expected %b but got %b for input: %s\n", expected, result, s);
        } else {
            System.out.printf("Test passed for input: %s\n", s);
        }
    }
}

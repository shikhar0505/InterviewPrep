package org.shikhar.leetcode.string;

/**
 * https://leetcode.com/problems/sort-characters-by-frequency/description/
 * 
 * Given a string s, sort it in decreasing order based on the frequency of the characters.
 * The frequency of a character is the number of times it appears in the string.
 * 
 * Return the sorted string. If there are multiple answers, return any of them.
 * 
 * Input: s = "tree"            Output: "eert"
 * Explanation: 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 */
public class L451_SortCharactersByFrequency {
    public String frequencySort(String s) {
        int[] freq = new int[256];
        for (char c : s.toCharArray()) {
            freq[c]++;
        }

        StringBuilder sb = new StringBuilder();
        while (sb.length() < s.length()) {
            int maxFreq = 0;
            char maxFreqChar = 0;
            for (int i = 0; i < freq.length; i++) {
                if (freq[i] > maxFreq) {
                    maxFreq = freq[i];
                    maxFreqChar = (char) i;
                }
            }
            for (int i = 0; i < maxFreq; i++) {
                sb.append(maxFreqChar);
            }
            freq[maxFreqChar] = 0;
        }
        return sb.toString();
    }
}

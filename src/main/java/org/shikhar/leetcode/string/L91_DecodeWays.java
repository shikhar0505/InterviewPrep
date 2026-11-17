package com.shikhar.interviewprep.string;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/decode-ways/
 * 
 * Input: s = "12"
 * Output: 2
 * 
 * Explanation:
 * "12" could be decoded as "AB" (1 2) or "L" (12).
 */
public class L91_DecodeWays {
    public int numDecodings(String s) {
        // maps index to result already computed
        Map<Integer, Integer> cache = new HashMap<>();

        // start with decoding char at index 0
        return decodeAtIndex(s, 0, cache);
    }

    private int decodeAtIndex(String s, int index, Map<Integer, Integer> cache) {
        // lookup cache
        if (cache.containsKey(index)) {
            return cache.get(index);
        }
        // if at last index, means we could only represent it one way
        if (index == s.length()) {
            return 1;
        }
        // can't decode a leading 0
        if (s.charAt(index) == '0') {
            return 0;
        }

        // ways to decode starting at index i + 1
        int res = decodeAtIndex(s, index + 1, cache);

        // check for double digits, and decode starting at index + 2 accordingly
        if (index + 1 < s.length() && (s.charAt(index) == '1' ||
           s.charAt(index) == '2' && s.charAt(index + 1) < '7')) {
            res += decodeAtIndex(s, index + 2, cache);
        }

        // cache result
        cache.put(index, res);
        
        return res;
    }
}

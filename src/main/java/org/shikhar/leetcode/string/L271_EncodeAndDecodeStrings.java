package com.shikhar.interviewprep.string;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/encode-and-decode-strings/
 * 
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.
 * 
 * Input: dummy_input = ["Hello","World"]           Output: ["Hello","World"]
 * Explanation:
 * Machine 1:   Codec encoder = new Codec();
 *              String msg = encoder.encode(strs);
 * Machine 1 ---msg---> Machine 2
 * Machine 2:   Codec decoder = new Codec();
 *              String[] strs = decoder.decode(msg);
 */
public class L271_EncodeAndDecodeStrings {
    private static final String DELIMITER = "/:";

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encodedString = new StringBuilder();
        for (String s : strs) {
            encodedString
                .append(s.length())
                .append(DELIMITER)
                .append(s);
        }
        return encodedString.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> decodedStrings = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int delim = s.indexOf(DELIMITER, i);
            int length = Integer.parseInt(s.substring(i, delim));
            String str = s.substring(delim + 2, delim + 2 + length);
            decodedStrings.add(str);
            i = delim + 2 + length;
        }
        return decodedStrings;
    }
}

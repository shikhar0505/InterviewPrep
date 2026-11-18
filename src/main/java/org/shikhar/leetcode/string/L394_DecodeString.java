package org.shikhar.leetcode.string;

/**
 * https://leetcode.com/problems/decode-string/description/
 * 
 * Given an encoded string, return its decoded string.
 * 
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.
 * Note that k is guaranteed to be a positive integer.
 * 
 * You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
 * For example, there will not be input like 3a or 2[4].
 * 
 * The test cases are generated so that the length of the output will never exceed 10^5.
 * 
 * Input: s = "3[a]2[bc]"       Output: "aaabcbc"
 * Input: s = "3[a2[c]]"        Output: "accaccacc"
 * Input: s = "2[abc]3[cd]ef"   Output: "abcabccdcdcdef"
 * 
 * Recursive solution with:
 * - O(n * (maxK ^ countK)) time complexity: maxK = max val of k; countK = count of nested k values; n = max length of encoded string
 * - O(n) space complexity (internal call stack for recursion).
 */
public class L394_DecodeString {
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int j = i;
                while (s.charAt(j) != '[') {
                    j++;
                }
                int k = Integer.parseInt(s.substring(i, j));
                int count = 1;
                j++;
                int start = j;
                while (count > 0) {
                    if (s.charAt(j) == '[') {
                        count++;
                    } else if (s.charAt(j) == ']') {
                        count--;
                    }
                    j++;
                }
                String decodedString = decodeString(s.substring(start, j - 1));
                for (int m = 0; m < k; m++) {
                    sb.append(decodedString);
                }
                i = j;
            } else {
                sb.append(c);
                i++;
            }
        }
        return sb.toString();
    }
}

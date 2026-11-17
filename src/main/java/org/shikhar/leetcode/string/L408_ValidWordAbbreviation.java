package com.shikhar.interviewprep.string;

/**
 * https://leetcode.com/problems/valid-word-abbreviation/
 * 
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 * 
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 * 
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 * The following are not valid abbreviations:
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 * "s010n" (has leading zeros)
 * "s0ubstitution" (replaces an empty substring)
 */
public class L408_ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }
            
            // leading zero
            if (abbr.charAt(j) == '0' || !isDigit(abbr.charAt(j))) {
                return false;
            }

            int start = j;
            while (j < abbr.length() && isDigit(abbr.charAt(j))) {
                ++j;
            }
            int num = Integer.valueOf(abbr.substring(start, j));
            i += num;
        }

        return i == word.length() && j == abbr.length();
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}

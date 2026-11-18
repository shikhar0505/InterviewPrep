package org.shikhar.leetcode.parenthesis;

/**
 * https://leetcode.com/problems/valid-parenthesis-string/description
 * 
 * Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
 * 
 * The following rules define a valid string:
 * - Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * - Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * - Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * - '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
 * 
 * Input: s = "()"              Output: true
 * Input: s = "(*)"             Output: true
 * Input: s = "(*))"            Output: true
 * Input: s = "("               Output: false
 */
public class L678_ValidParenthesisString {
    public static boolean checkValidString(String s) {
        int openCount = 0;
        int closeCount = 0;

        for (int i = 0; i <= s.length() - 1; i++) {
            char l = s.charAt(i);
            char r = s.charAt(s.length() - 1 - i);
            
            if (l == '(' || l == '*') {
                openCount++;
            }
            else {
                openCount--;
            }

            if (r == ')' || r == '*') {
                closeCount++;
            } else {
                closeCount--;
            }

            if (openCount < 0 || closeCount < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        assertTrue(L678_ValidParenthesisString.checkValidString("()"), "()");
        assertTrue(L678_ValidParenthesisString.checkValidString("(*)"), "(*)");
        assertTrue(L678_ValidParenthesisString.checkValidString("(*))"), "(*))");
        assertFalse(L678_ValidParenthesisString.checkValidString("("), "(");
    }

    private static void assertTrue(boolean condition, String s) {
        if (!condition) {
            System.out.printf("Expected %s but got false\n", s);
        } else {
            System.out.printf("Test passed for input: %s\n", s);
        }
    }

    private static void assertFalse(boolean condition, String s) {
        if (condition) {
            System.out.printf("Expected %s but got true\n", s);
        } else {
            System.out.printf("Test passed for input: %s\n", s);
        }
    }
}

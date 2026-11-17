package com.shikhar.interviewprep.parenthesis;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/description
 * 
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * 
 * An input string is valid if:
 * - Open brackets must be closed by the same type of brackets.
 * - Open brackets must be closed in the correct order.
 * - Every close bracket has a corresponding open bracket of the same type.
 * 
 * Input: s = "()"         Output: true
 * Input: s = "()[]{}"     Output: true
 * Input: s = "(]"          Output: false
 * Input: s = "([])"        Output: true
 * Input: s = "([)]"        Output: false
 */
public class L20_ValidParentheses {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        assertTrue(L20_ValidParentheses.isValid("()"), "()");
        assertTrue(L20_ValidParentheses.isValid("()[]{}"), "()[]{}");
        assertFalse(L20_ValidParentheses.isValid("(]"), "(]");
        assertTrue(L20_ValidParentheses.isValid("([])"), "([])");
        assertFalse(L20_ValidParentheses.isValid("([)]"), "([)]");
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
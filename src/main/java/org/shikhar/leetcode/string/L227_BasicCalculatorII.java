package org.shikhar.leetcode.string;

/**
 * https://leetcode.com/problems/basic-calculator-ii
 * 
 * Input: s = " 3+5 / 2 "
 * Output: 5
 * 
 * Think of BODMAS.
 */
public class L227_BasicCalculatorII {
    public int calculate(String s) {
        int op1 = 0, op2 = 0, result = 0;
        char operation = '+'; // only because you can't have ''
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // operand
            if (isDigit(c)) {
                op1 = (op1 * 10) + (c - '0');
            }
            
            // operator (think of BODMAS)
            if (!isDigit(c) && c != ' ' || i == s.length() - 1) {
                if (operation == '*') {
                    op2 = op2 * op1;
                } else if (operation == '/') {
                    op2 = op2 / op1;
                }
                if (operation == '+' || operation == '-') {
                    result += op2;
                    op2 = op1 * (operation == '+' ? 1 : -1);
                }
                operation = c;
                op1 = 0;
            }
        }
        result += op2;
        return result;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}

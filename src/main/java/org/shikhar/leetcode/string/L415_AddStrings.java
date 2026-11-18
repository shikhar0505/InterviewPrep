package org.shikhar.leetcode.string;

/**
 * https://leetcode.com/problems/add-strings/description/
 * 
 * Input: num1 = "11", num2 = "123"
 * Output: "134"
 */
public class L415_AddStrings {
    public String addStrings(String num1, String num2) {
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        StringBuilder sum = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? (num1.charAt(i) - '0') : 0;
            int n2 = j >= 0 ? (num2.charAt(j) - '0') : 0;
            int s = n1 + n2 + carry;
            
            if (s < 10) {
                sum.append(s);
                carry = 0;
            } else {
                sum.append(s % 10);
                carry = 1;
            }
            i--;
            j--;
        }
        if (carry > 0) {
            sum.append(carry);
        }
        return sum.reverse().toString();
    }    
}

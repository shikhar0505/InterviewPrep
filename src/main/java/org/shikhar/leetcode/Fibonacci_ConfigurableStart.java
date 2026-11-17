package com.shikhar.interviewprep;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci_ConfigurableStart {
    public static int fib_twist(int n, int first, int second) {
        Map<Integer, Integer> memo = new HashMap<>();
        return helper(n, first, second, memo);
    }

    private static int helper(int k, int first, int second, Map<Integer, Integer> memo) {
        if (k == 0) {
            return first;
        }
        if (k == 1) {
            return second;
        }
        if (memo.containsKey(k)) {
            return memo.get(k);
        }

        int result = helper(k - 1, first, second, memo)
                     + helper(k - 2, first, second, memo);
        memo.put(k, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fib_twist(4, 2, 3));   // Output: 13
        System.out.println(fib_twist(0, 8, 13));  // Output: 8
        System.out.println(fib_twist(900, 0, 1)); // Handles large n safely
    }
}

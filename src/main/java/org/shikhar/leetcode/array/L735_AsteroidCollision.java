package org.shikhar.leetcode.array;

import java.util.Stack;

/**
 * https://leetcode.com/problems/asteroid-collision/description/
 * 
 * We are given an array asteroids of integers representing asteroids in a row. The indices of the asteroid in the array
 * represent their relative position in space.
 * 
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning
 * right, negative meaning left). Each asteroid moves at the same speed.
 * 
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If
 * both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * 
 * Input: asteroids = [5,10,-5]             Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 * 
 * Input: asteroids = [8,-8]                 Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 * 
 * Input: asteroids = [10,2,-5]               Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 * 
 * Input: asteroids = [3,5,-6,2,-1,4]          Output: [-6,2,4]
 * Explanation: The asteroid -6 makes the asteroid 3 and 5 explode, and then continues going left. On the other side,
 * the asteroid 2 destroys -1. Since 2 and 4 are both moving right, they never collide.
 */
public class L735_AsteroidCollision {
   public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> asteroidStack = new Stack<>();

        for (int asteroid : asteroids) {
            while (!asteroidStack.isEmpty() && asteroid < 0 && asteroidStack.peek() > 0) {
                int diff = asteroidStack.peek() + asteroid;
                if (diff < 0) {
                    asteroidStack.pop();
                } else if (diff > 0) {
                    asteroid = 0;
                } else {
                    asteroidStack.pop();
                    asteroid = 0;
                }
            }
            if (asteroid != 0) {
                asteroidStack.push(asteroid);
            }
        }

        return asteroidStack.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        assertEquals(L735_AsteroidCollision.asteroidCollision(new int[]{5, 10, -5}), new int[]{5, 10}, "Test Case 1");
        assertEquals(L735_AsteroidCollision.asteroidCollision(new int[]{8, -8}), new int[]{}, "Test Case 2");
        assertEquals(L735_AsteroidCollision.asteroidCollision(new int[]{10, 2, -5}), new int[]{10}, "Test Case 3");
        assertEquals(L735_AsteroidCollision.asteroidCollision(new int[]{3, 5, -6, 2, -1, 4}), new int[]{-6, 2, 4}, "Test Case 4");
    }

    private static void assertEquals(int[] result, int[] expected, String s) {
        if (result.length != expected.length) {
            System.out.printf("Expected %s but got %s for input: %s\n", arrayToString(expected), arrayToString(result), s);
            return;
        }
        for (int i = 0; i < result.length; i++) {
            if (result[i] != expected[i]) {
                System.out.printf("Expected %s but got %s for input: %s\n", arrayToString(expected), arrayToString(result), s);
                return;
            }
        }
        System.out.printf("Test passed for input: %s\n", s);
    }

    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }   
}

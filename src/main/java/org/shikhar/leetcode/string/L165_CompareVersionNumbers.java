package com.shikhar.interviewprep.string;

/**
 * https://leetcode.com/problems/compare-version-numbers/description/
 * 
 * Given two version strings, version1 and version2, compare them. A version string consists of revisions separated
 * by dots '.'. The value of the revision is its integer conversion ignoring leading zeros.
 * 
 * To compare version strings, compare their revision values in left-to-right order. If one of the version strings
 * has fewer revisions, treat the missing revision values as 0.
 * 
 * Return the following:
 * - If version1 < version2, return -1.
 * - If version1 > version2, return 1.
 * Otherwise, return 0.
 * 
 * Input: version1 = "1.2", version2 = "1.10"       Output: -1
 * Input: version1 = "1.0.1", version2 = "1.001"    Output: 0
 * Input: version1 = "1.0", version2 = "1.0.0.0"    Output: 0
 */
public class L165_CompareVersionNumbers {
    public static int compareVersion(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int n1 = nums1.length, n2 = nums2.length;

        // compare versions
        for (int i = 0; i < Math.max(n1, n2); ++i) {
            int i1 = i < n1 ? Integer.parseInt(nums1[i]) : 0;
            int i2 = i < n2 ? Integer.parseInt(nums2[i]) : 0;
            
            if (i1 != i2) {
                return i1 > i2 ? 1 : -1;
            }
        }
        //The versions are equal
        return 0;
    }

    public static void main(String[] args) {
        runTests();
    }
    
    private static void runTests() {
        assertEquals(L165_CompareVersionNumbers.compareVersion("1.2", "1.10"), -1, "Test Case 1");
        assertEquals(L165_CompareVersionNumbers.compareVersion("1.01", "1.001"), 0, "Test Case 2");
        assertEquals(L165_CompareVersionNumbers.compareVersion("1.0", "1.0.0.0"), 0, "Test Case 3");
    }

    private static void assertEquals(int actual, int expected, String testCase) {
        if (actual != expected) {
            throw new AssertionError(testCase + " failed: expected " + expected + ", but got " + actual);
        } else {
            System.out.println(testCase + " passed.");
        }
    }
}

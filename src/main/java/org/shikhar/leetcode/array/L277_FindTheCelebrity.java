package com.shikhar.interviewprep.array;

/**
 * https://leetcode.com/problems/find-the-celebrity/description/
 * 
 * The knows API is defined in the parent class Relation:
 * boolean knows(int a, int b); // Returns true if person a knows person b, otherwise false.
 * 
 * Input: graph = [[1,1,0],[0,1,0],[1,1,1]]             Output: 1
 * Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j,
 * otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because
 * both 0 and 2 know him but 1 does not know anybody.
 * 
 * Input: graph = [[1,0,1],[1,1,0],[0,1,1]]             Output: -1
 * Explanation: There is no celebrity.
 */
public class L277_FindTheCelebrity {
   public int findCelebrity(int n) {
        int possibleCelebrity = 0;
        for (int i = 1; i < n; i++) {
            if (knows(possibleCelebrity, i)) {
                possibleCelebrity = i;
            }
        }
        // check if possibleCelebrity is indeed a celebrity
        for (int i = 0; i < n; i++) {
            if (i != possibleCelebrity) {
                if (!knows(i, possibleCelebrity) || knows(possibleCelebrity, i)) {
                    return -1;
                }
            }
        }

        return possibleCelebrity;
    }

    private boolean knows(int a, int b) {
        // This method should be implemented to check if person a knows person b.
        // For the purpose of this example, we will return false.
        return false;
    }
}

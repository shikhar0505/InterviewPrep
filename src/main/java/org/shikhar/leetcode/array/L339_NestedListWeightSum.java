package com.shikhar.interviewprep.array;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/nested-list-weight-sum/description/
 * 
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also
 * be integers or other lists.
 * 
 * The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1]
 * has each integer's value set to its depth.
 * 
 * Return the sum of each integer in nestedList multiplied by its depth.
 * 
 * Input: nestedList = [[1,1],2,[1,1]]      Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
 * 
 * Input: nestedList = [1,[4,[6]]]          Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
 * 
 * Input: nestedList = [0]                  Output: 0
 */
public class L339_NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        // Time: O(N)       Space: O(D); D = max depth
        // return dfs(nestedList, 1);

        // Time: O(N)       Space: O(N)
        return depthSum_BFS(nestedList);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger i : nestedList) {
            if (i.isInteger()) {
                sum += i.getInteger() * depth;
            } else {
                sum += dfs(i.getList(), depth + 1);
            }
        }
        return sum;
    }

    public int depthSum_BFS(List<NestedInteger> nestedList) {
        Queue<NestedInteger> queue = new LinkedList<>();
        queue.addAll(nestedList);

        int depth = 1;
        int prodSum = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger nextedInt = queue.poll();
                if (nextedInt.isInteger()) {
                    prodSum += (depth * nextedInt.getInteger());
                } else {
                    for (NestedInteger j : nextedInt.getList()) {
                        queue.offer(j);
                    }
                }
            }
            depth++;
        }

        return prodSum;
    }

    public interface NestedInteger {
      // Constructor initializes an empty nested list.
      public NestedInteger();
 
      // Constructor initializes a single integer.
      public NestedInteger(int value);
 
      // @return true if this NestedInteger holds a single integer, rather than a nested list.
      public boolean isInteger();
 
      // @return the single integer that this NestedInteger holds, if it holds a single integer
      // The result is undefined if this NestedInteger holds a nested list
      public Integer getInteger();
 
      // Set this NestedInteger to hold a single integer.
      public void setInteger(int value);
 
      // Set this NestedInteger to hold a nested list and adds a nested integer to it.
      public void add(NestedInteger ni);
 
      // @return the nested list that this NestedInteger holds, if it holds a nested list
      // The result is undefined if this NestedInteger holds a single integer
      public List<NestedInteger> getList();
    }
}

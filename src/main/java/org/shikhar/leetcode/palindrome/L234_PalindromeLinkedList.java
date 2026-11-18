package org.shikhar.leetcode.palindrome;

/**
 * https://leetcode.com/problems/palindrome-linked-list/description/
 * 
 * Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 * 
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class L234_PalindromeLinkedList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next;}
    }
 
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true; // An empty list or a single node is a palindrome
        }

        // Step 1: Find the middle of the linked list using the slow and fast pointer technique
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the linked list
        ListNode prev = null;
        while (slow != null) {
            ListNode nextNode = slow.next;
            slow.next = prev;
            prev = slow;
            slow = nextNode;
        }

        // Step 3: Compare the first half and the reversed second half
        ListNode left = head;
        ListNode right = prev; // 'prev' is now the head of the reversed second half

        while (right != null) {
            if (left.val != right.val) {
                return false; // Values do not match, not a palindrome
            }
            left = left.next;
            right = right.next;
        }

        return true; // All values matched, it is a palindrome
    }
}

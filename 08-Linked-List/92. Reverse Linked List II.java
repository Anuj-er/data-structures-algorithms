// https://leetcode.com/problems/reverse-linked-list-ii

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Move leftpre to the node before "left"
        ListNode leftpre = dummy, current = head;
        for (int i = 0; i < left - 1; i++) {
            leftpre = leftpre.next;
            current = current.next;
        }

        // This is the start node of the sublist, it will become the "tail" after reversal
        ListNode sublistTail = current;

        // Reverse sublist
        ListNode prev = null;
        for (int i = 0; i <= right - left; i++) {
            ListNode nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        // Reconnect
        leftpre.next = prev;
        sublistTail.next = current;

        return dummy.next;
    }
}
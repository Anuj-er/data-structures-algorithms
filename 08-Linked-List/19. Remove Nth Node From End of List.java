// https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/

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


// By Size Calculation and Traversal
class Solution1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode current = head;
        int size = 0;
        while(current != null){
            size++;
            current = current.next;
        }
        if(n==size){
            return head.next;
        }
        current = head;
        for(int i=0;i<size-n-1;i++){
            current = current.next;
        }

        ListNode temp = current.next.next;
        current.next = temp;
        return head;
    }
}

// By Two Pointer Approach
class Solution2 {
    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode slow = head, fast = head;
        for(int i=0;i<n;i++){
            fast = fast.next;
        }
        if(fast == null){
            return head.next;
        }
        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }
        ListNode temp = slow.next.next;
        slow.next = temp;
        return head;
    }
}
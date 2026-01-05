// https://leetcode.com/problems/remove-duplicates-from-sorted-list/

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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = head;
        if(prev == null) return null;
        if(prev.next == null) return head;
        ListNode curr = prev.next;

        while(curr !=null){
            if(curr.val == prev.val) prev.next = curr.next;
            else prev = curr;

            curr = curr.next;
        }
        return head;
    }
}

// class Solution {
//     public ListNode deleteDuplicates(ListNode head) {
//         ListNode temp = head;
//         if(temp==null) return null;
//         while(temp.next!=null){
//             if(temp.next.val == temp.val){
//                 // Delete the next Node;
//                 if(temp.next.next!=null){
//                     // Delete if Third node is not null
//                     ListNode t = temp.next.next;
//                     temp.next = t;
//                 }else{
//                     temp.next = null;
//                 }
//             }else{
//                 temp = temp.next;
//             }
//         }
//         return head;
//     }
// }
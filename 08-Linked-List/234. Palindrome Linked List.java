// https://leetcode.com/problems/palindrome-linked-list/description/

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

class Solution{

    public ListNode reverse(ListNode head) {
        ListNode current = head ,prev = null;
        while(current != null){
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

    public boolean isPalindrome(ListNode head){
        ListNode slow = head,fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode newHead = reverse(slow.next);
        ListNode first = head , second = newHead;
        while(second != null){
            if(first.val != second.val){
                return false;
            }
            second = second.next;
            first = first.next;
        }
        reverse(newHead);
    return true;
    }
}


// class Solution {
//     public boolean isPalindrome(ListNode head) {
//         Stack<ListNode> myst = new Stack<>();
//         ListNode current = head;
//         while(current!=null){
//             myst.add(current);
//             current = current.next;
//         }
//         current = head;
//         while(current!=null){
//             if(myst.peek().val==current.val){
//                 myst.pop();
//             }else{
//                 return false;
//             }
//             current = current.next;
//         }
//         return true;
//     }
// }
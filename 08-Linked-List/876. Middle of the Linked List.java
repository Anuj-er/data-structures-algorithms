// http://leetcode.com/problems/middle-of-the-linked-list
/**
 * Definition for singly-linked list.
 */
// public class ListNode {
//     int val;
//     ListNode next;
//     ListNode(int x) { val = x; }
// }

class Solution {
    public ListNode middleNode(ListNode head) {
        int count = 0;
        ListNode temp = head;
        while(temp!=null){
            count++;
            temp = temp.next;
        }
        int middle = count/2;
        temp = head;
        for(int i=0;i<middle;i++){
            temp = temp.next;
        }
        return temp;
    }
}


// Also worked but the above one is better

// class Solution {
//     public ListNode middleNode(ListNode head) {
//         int count = -1;
//         ListNode temp = head;
//         while(temp!=null){
//             count++;
//             temp = temp.next;
//         }
//         int middle = count/2;
//         temp = head;
//         while(temp!=null){
//             count--;
//             temp = temp.next;
//             if(count == middle){
//                 head = temp;
//                 break;
//             }
//         }
//         return head;
//     }
// }
// https://leetcode.com/problems/merge-k-sorted-lists/description/

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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
        for(ListNode l : lists){
            if(l != null) pq.add(l);
        }

        ListNode head = new ListNode(1);
        ListNode temp = head;
        while(!pq.isEmpty()){
            ListNode q = pq.poll();
            if(q.next != null) pq.add(q.next);
            q.next = null;
            temp.next = q;
            temp = temp.next;
        }
        return head.next;
    }
}

// class Solution {
//     public ListNode mergeKLists(ListNode[] lists) {
//         PriorityQueue<Integer> pq = new PriorityQueue<>();
//         for(ListNode l : lists){
//             ListNode temp = l;
//             while(temp != null){
//                 pq.add(temp.val);
//                 temp = temp.next;
//             }
//         }
//         ListNode head = new ListNode(1);
//         ListNode temp = head;
//         while(!pq.isEmpty()){
//             ListNode q = new ListNode(pq.poll());
//             temp.next = q;
//             temp = temp.next;
//         }
//         return head.next;
//     }
// }
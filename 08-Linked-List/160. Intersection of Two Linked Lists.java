// https://leetcode.com/problems/intersection-of-two-linked-lists/description/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */


// Time Complexity: O(N+M) where N and M are the lengths of the two linked lists.
// Space Complexity: O(N) where N is the length of the first linked list, as we are using a hashmap to store the nodes of the first linked list.
public class Solution1 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<ListNode,Integer> map = new HashMap<>();
        ListNode current = headA;
        int i = 0;
        while(current != null){
            if(!map.containsKey(current)){
                map.put(current,i++);
            }
            current = current.next;
        }
        current = headB;
        while(current!=null){
            if(map.containsKey(current)){
                return current;
            }
            current = current.next;
        }
        return null;
    }
}

// Time Complexity: O(N+M) where N and M are the lengths of the two linked lists.
// Space Complexity: O(1) as we are using only a constant amount of extra space
public class Solution2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int c1 = 0, c2 = 0;
        ListNode current = headA;
        while (current != null) {
            c1++;
            current = current.next;
        }
        current = headB;
        while (current != null) {
            c2++;
            current = current.next;
        }
        ListNode temp1 = headA, temp2 = headB;
        int d = Math.abs(c1 - c2);
        if (c1 > c2) {
            for (int i = 0; i < d; i++)
                temp1 = temp1.next;
        } else {
            for (int i = 0; i < d; i++)
                temp2 = temp2.next;
        }

        while (temp1 != temp2) {
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return temp1;
    }
}

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


// This solution doesnt work because their are more larger numbers in testcases
class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        long n1 = 0,n2 = 0;
        ListNode current = l1;
        long n = 1;
        while(current!=null){
            n1 = n1 + current.val * n;
            n *= 10;
            current = current.next;
        }
        current = l2;
        n = 1;
        while(current!=null){
            n2 = n2 + current.val * n;
            n *= 10;
            current = current.next;
        }
        long sum = n1+n2;
        ListNode dummy = new ListNode(0);
        current = dummy;
        if(sum == 0) return new ListNode(0);
        while(sum>0){
            int digit = (int) sum%10;
            current.next = new ListNode(digit);
            current = current.next;
            sum = sum/10; 
        }
        return dummy.next;
    }
}

// By Carry Forward Method
// 
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        ListNode temp1 = l1, temp2 = l2;
        while (temp1 != null || temp2 != null || carry > 0) {
            int sum = carry;
            if (temp1 != null) {
                sum += temp1.val;
                temp1 = temp1.next;
            }
            if (temp2 != null) {
                sum += temp2.val;
                temp2 = temp2.next;
            }
            int digit = sum % 10;
            carry = sum / 10;
            current.next = new ListNode(digit);
            current = current.next;
        }
        return dummy.next;
    }
}

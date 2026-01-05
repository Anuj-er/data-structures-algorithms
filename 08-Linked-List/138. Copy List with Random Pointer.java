// https://leetcode.com/problems/copy-list-with-random-pointer/description/

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/


// Using HashMap
class Solution1 {
    public Node copyRandomList(Node head) {
        Node temp = head;

        HashMap<Node,Node> map = new HashMap<>();
        while(temp!=null){
            Node newNode = new Node(temp.val);
            map.put(temp,newNode);
            temp = temp.next;  
        }
        temp = head;
        while(temp!=null){
            Node copyNode = map.get(temp);
            copyNode.next = map.get(temp.next);
            copyNode.random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }
}


// Without using extra space
class Solution2{
    public Node copyRandomList(Node head){
        Node current = head;

        // Added in copy node in between the nodes
        while(current!=null){
            Node copy = new Node(current.val);
            Node temp = current.next;
            current.next = copy;
            copy.next = temp;
            current = current.next.next;
        }
        current = head;

        // Now we will point the randoms 
        while(current!=null){
            if(current.random!=null){
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        current = head;
        Node dummy = new Node(0);
        Node ans = dummy;
        // Now we remove the orignal list elements
        while(current!=null){
            ans.next = current.next;
            current.next = current.next.next;
            ans = ans.next;
            current = current.next;
        }
        return dummy.next;
    }
}

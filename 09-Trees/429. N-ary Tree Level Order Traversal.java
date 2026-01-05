// https://leetcode.com/problems/n-ary-tree-level-order-traversal/description/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {

        // BFS Approach using Queue

        // if root is null return empty list
        if(root==null) return new ArrayList<>();

        // creating a queue for BFS and adding root to it
        Queue<Node> myq = new LinkedList<>();
        myq.add(root);

        // creating a list of list to store the final answer
        List<List<Integer>> ans = new ArrayList<>();

        // iterating until queue is empty
        while(!myq.isEmpty()){

            // size of current level
            int size = myq.size();

            // list to store all the nodes of current level
            List<Integer> al = new ArrayList<>();

            // iterating for all nodes of current level
            for(int i=0;i<size;i++){
                Node front = myq.poll();

                // adding current node to the list
                al.add(front.val);

                // adding all children of current node to queue
                for(Node child: front.children){
                    myq.add(child);
                }
            }

            // adding current level list to the final answer
            ans.add(al);
        }
        return ans;
    }
}
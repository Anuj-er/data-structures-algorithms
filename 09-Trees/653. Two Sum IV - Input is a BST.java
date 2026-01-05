// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class BstIterator {
    Stack<TreeNode> st = new Stack<TreeNode>();

    boolean reverse = false;

    public BstIterator(TreeNode node, boolean isReverse){
        reverse = isReverse;
        pushAll(node);
    }

    public boolean hasNext(){
        return !st.isEmpty();
    }

    public int next(){
        TreeNode tmpNode = st.pop();
        if(reverse == false) pushAll(tmpNode.right);
        else pushAll(tmpNode.left);
        return tmpNode.val;
    }

    public void pushAll(TreeNode node){
        while(node!=null){
            st.push(node);
            if(reverse==false) node = node.left;
            else node = node.right;
        }
    }

}
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        BstIterator l = new BstIterator(root,false);
        BstIterator r = new BstIterator(root,true);

        int i = l.next();
        int j = r.next();


        while(i<j){
            if(i+j == k) return true;
            else if(i+j>k) j = r.next();
            else i = l.next();
        }

        return false;
    }
}


// class Solution {
//     Set<Integer> set = new HashSet<>();
//     int as = 0; 

//     public void traversal(TreeNode node,int k){
//         if(node == null) return ;

//         int find = k - node.val;

//         if(set.contains(find)){
//             as = 1;
//         }
//         set.add(node.val);

//         traversal(node.left,k);
//         traversal(node.right,k);
//     }

//     public boolean findTarget(TreeNode root, int k) {
//         traversal(root,k);
//         if(as == 1) return true;
//         return false;
//     }
// }
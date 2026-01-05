// https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/

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

// Approach 1: Inorder Traversal + List
// Time Complexity: O(N) where N is the number of nodes in the tree
// Space Complexity: O(N) for the list to store the inorder traversal
class Solution1 {
    List<Integer> ansL = new ArrayList<>();

    public void traversal(TreeNode node){
        if(node == null) return;

        traversal(node.left);
        ansL.add(node.val);
        traversal(node.right);

    }

    public int getMinimumDifference(TreeNode root) {
        traversal(root);
        int minDiff = Integer.MAX_VALUE;
        for(int i=1;i<ansL.size();i++){
            minDiff = Math.min(minDiff,ansL.get(i)-ansL.get(i-1));
        }
        return minDiff;
    }
}

// Approach 2: Inorder Traversal + Variable to store previous value
// Time Complexity: O(N) where N is the number of nodes in the tree
// Space Complexity: O(H) where H is the height of the tree due to recursion stack
class Solution2 {

    int prev = -1;
    int minDiff = Integer.MAX_VALUE;
    
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return minDiff;
    }

    public void inOrder(TreeNode node){
        if(node == null) return;
        
        inOrder(node.left);

        if(prev != -1){
            minDiff = Math.min(minDiff,node.val - prev);
        }

        prev = node.val;

        inOrder(node.right);
    }
}

// https://leetcode.com/problems/insert-into-a-binary-search-tree/description/
// Time Complexity: O(h) where h is the height of the tree
// Space Complexity: O(h) for recursive stack space, O(1) for iterative approach

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

// Recursive Approach
class Solution1 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        if(root.val>val){
            root.left = insertIntoBST(root.left,val);
        }else{
            root.right = insertIntoBST(root.right,val);
        }
        return root; 
    }
}

// Iterative Approach
class Solution2{
    public TreeNode insertIntoBST(TreeNode root, int val){
        if(root == null) return new TreeNode(val);
        TreeNode current = root;
        while(true){
            if(current.val>val){
                if(current.left!=null) current = current.left;
                else{
                    current.left = new TreeNode(val);
                    break;
                }
            }else{
                if(current.right!=null) current = current.right;
                else{
                    current.right = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
}
// https://leetcode.com/problems/binary-tree-preorder-traversal/description/

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
class Solution {
    List<Integer> res = new ArrayList<>();
    public void traversal(TreeNode root){
        if(root == null){
            return;
        }
        res.add(root.val);
        traversal(root.left);
        traversal(root.right);
    }
    public List<Integer> preorderTraversal(TreeNode root) {
        traversal(root);
        return res;
    }
}
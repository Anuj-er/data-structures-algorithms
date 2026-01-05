// https://leetcode.com/problems/sum-of-left-leaves/

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
    int sum = 0;
    public void sumDFS(TreeNode node){
        if(node == null) return;
        if(node.left!=null && node.left.left == null && node.left.right == null) sum += node.left.val;
        sumDFS(node.left);
        sumDFS(node.right);
    }
    public int sumOfLeftLeaves(TreeNode root) {
        sumDFS(root);
        return sum;
    }
}
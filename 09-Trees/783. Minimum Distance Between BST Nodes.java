// https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/


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
    int prev = -1;
    int minDiff = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
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
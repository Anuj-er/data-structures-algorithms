// https://leetcode.com/problems/binary-tree-maximum-path-sum/description/

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
    int maxi = Integer.MIN_VALUE;

    public int maxPathDown(TreeNode node){
        if(node==null) return 0;
        int left = Math.max(0,maxPathDown(node.left));
        int right = Math.max(0,maxPathDown(node.right));
        maxi = Math.max(maxi,left+right+node.val);
        return Math.max(left,right) + node.val;
    }

    public int maxPathSum(TreeNode root) {
        maxPathDown(root);
        return maxi;
    }
}
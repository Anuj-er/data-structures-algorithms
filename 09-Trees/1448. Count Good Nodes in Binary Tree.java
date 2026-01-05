// https://leetcode.com/problems/count-good-nodes-in-binary-tree/

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
    public int goodNodes(TreeNode root) {
        if(root==null) return 0;
        return helper(root,Integer.MIN_VALUE);
        
    }
    private int helper(TreeNode node,int curMax){
        if(node == null) return 0;
        int nodeAns = 0;
        if(node.val>=curMax){
            nodeAns = 1;
            curMax = node.val;
        }
        int left = helper(node.left,curMax);
        int right = helper(node.right,curMax);

        return left+right+nodeAns;
    }
}
// 98. Validate Binary Search Tree
// https://leetcode.com/problems/validate-binary-search-tree/
// Time Complexity: O(n) where n is the number of nodes in the tree
// Space Complexity: O(h) where h is the height of the tree due to recursive stack


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
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean helper(TreeNode node, long minVal, long maxVal) {
        if (node == null)
            return true;
        if (node.val >= maxVal || node.val <= minVal)
            return false;
        return helper(node.left, minVal, node.val) && helper(node.right, node.val, maxVal);
    }
}
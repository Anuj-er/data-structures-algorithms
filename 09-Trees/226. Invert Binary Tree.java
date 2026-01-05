// https://leetcode.com/problems/invert-binary-tree/description/

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

    void dfs(TreeNode node) {
        if (node == null)
            return;

        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;

        dfs(node.left);
        dfs(node.right);

    }

    public TreeNode invertTree(TreeNode root) {
        dfs(root);
        return root;
    }
}
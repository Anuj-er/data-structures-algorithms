// https://leetcode.com/problems/binary-search-tree-iterator/description/

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

    int count = 0;
    int ans = 0;

    public void traversal(TreeNode node,int k){
        if(node == null) return;

        traversal(node.left,k);

        count++;
        if(count == k){
            ans = node.val;
        }
        
        traversal(node.right,k);
    }

    public int kthSmallest(TreeNode root, int k) {
        traversal(root,k);
        return ans;
    }
}
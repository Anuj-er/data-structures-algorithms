// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/

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

    private Map<Integer, Integer> map = new HashMap<>();
    private int preorderIdx = 0;

    public TreeNode helper(int[] preorder, int left, int right) {
        if (left > right) return null;

        TreeNode root = new TreeNode(preorder[preorderIdx]);

        int mid = map.get(root.val);
        preorderIdx++;

        root.left = helper(preorder, left, mid - 1);
        root.right = helper(preorder, mid + 1, right);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return helper(preorder, 0, inorder.length - 1);
    }
}

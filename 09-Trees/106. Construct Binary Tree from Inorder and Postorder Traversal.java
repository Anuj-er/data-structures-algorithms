// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/

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
    private int postorderIdx;

    public TreeNode helper(int[] postorder, int left, int right) {
        if (left > right) return null;

        TreeNode root = new TreeNode(postorder[postorderIdx]);

        int mid = map.get(root.val);
        postorderIdx--;

        root.right = helper(postorder, mid + 1, right);
        root.left = helper(postorder, left, mid - 1);

        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postorderIdx = postorder.length-1;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(postorder, 0, inorder.length - 1);
    }
}
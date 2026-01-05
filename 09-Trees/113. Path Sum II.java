// https://leetcode.com/problems/path-sum-ii

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
    List<List<Integer>> result = new ArrayList<List<Integer>>();

    public void fill(TreeNode node, int sum, List<Integer> temp, int targetSum) {
        if (node == null)
            return;

        sum += node.val;
        temp.add(node.val);

        if (node.left == null && node.right == null) {
            if (sum == targetSum) {
                result.add(new ArrayList<>(temp));
            }
            temp.remove(temp.size()-1);
            return;
        } else {
            fill(node.left, sum, temp, targetSum);
            fill(node.right, sum, temp, targetSum);
        }

        temp.remove(temp.size()-1);

    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        int sum = 0;
        List<Integer> temp = new ArrayList<>();

        fill(root, sum, temp, targetSum);
        return result;
    }
}
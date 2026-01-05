// https://leetcode.com/problems/count-complete-tree-nodes/description/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

//  O(N) solution
class Solution1 {
    int count = 0;

    public void countN(TreeNode root) {
        if (root == null)
            return;

        count++;
        countN(root.left);
        countN(root.right);
    }

    public int countNodes(TreeNode root) {
        countN(root);
        return count;
    }
}

// O((logN)^2) solution
class Solution2 {
    public int getLeftHeight(TreeNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.left;
        }
        return count;
    }

    public int getRightHeight(TreeNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.right;
        }
        return count;
    }

    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;

        int left = getLeftHeight(root);
        int right = getRightHeight(root);

        if (left == right) {
            return (1 << left) - 1;
        }

        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
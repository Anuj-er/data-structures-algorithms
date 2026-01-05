// https://leetcode.com/problems/binary-tree-right-side-view/description/



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
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> myq = new LinkedList<TreeNode>();
        List<Double> ans = new ArrayList<>();
        if (root == null)
            return ans;
        myq.add(root);
        while (!myq.isEmpty()) {
            int size = myq.size();
            double level = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = myq.peek();
                myq.poll();
                if (node.left != null) myq.add(node.left);
                if (node.right != null) myq.add(node.right);
                level += node.val;
            }
            double average = level/size;
            ans.add(average);
            
        }
        return ans;
    }
}


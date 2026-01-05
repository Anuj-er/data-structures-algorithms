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
    List<Integer> ansList = new ArrayList<>();
    void dfs(TreeNode node,int sum){
        if(node==null) return;
        
        sum += node.val;

        if(node.left == null && node.right == null){
            ansList.add(sum);
        }

        dfs(node.left,sum);
        dfs(node.right,sum);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        int sum = 0;
        dfs(root,sum);
        for(int i=0;i<ansList.size();i++){
            if(ansList.get(i)==targetSum){
                return true;
            }
        }
        return false;
    }
}
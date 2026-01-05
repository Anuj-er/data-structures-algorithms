// https://leetcode.com/problems/path-sum-iii

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
    int result = 0;
    HashMap<Long,Integer> map = new HashMap<>();

    void traverse(TreeNode node,int targetSum, long sum){
        if(node == null) return;
        sum += node.val;

        if(map.containsKey(sum-targetSum)){
            result += map.get(sum-targetSum);
        }

        map.put(sum,map.getOrDefault(sum,0)+1);
        

        traverse(node.left,targetSum,sum);
        traverse(node.right,targetSum,sum);

        map.put(sum, map.get(sum) - 1);
    }

    public int pathSum(TreeNode root, int targetSum) {
        map.put((long)0,1);
        traverse(root,targetSum,0);
        return result;
    }
}
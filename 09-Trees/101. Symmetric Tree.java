// https://leetcode.com/problems/symmetric-tree/description/

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
    List<Integer> arr1 = new ArrayList<>();
    List<Integer> arr2 = new ArrayList<>();

    void traversal(TreeNode node){
        if(node == null){
            arr1.add(null);
            return; 
        }
        arr1.add(node.val);
        traversal(node.left);
        traversal(node.right);
    }

    void traversal2(TreeNode node){
        if(node == null){
            arr2.add(null);
            return; 
        }
        arr2.add(node.val);
        traversal2(node.right);
        traversal2(node.left);
    }

    public boolean isSymmetric(TreeNode root) {
        traversal(root);
        traversal2(root);
        if(arr1.equals(arr2)){
            return true;
        }
        return false;
    }
}
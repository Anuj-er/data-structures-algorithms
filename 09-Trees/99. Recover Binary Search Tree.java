// https://leetcode.com/problems/recover-binary-search-tree/description/

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

    private TreeNode first,prev,middle,last;
    
    public void inOrder(TreeNode node){
        if(node == null) return;

        inOrder(node.left);

        if(prev!=null&& prev.val>node.val){
            if(first == null){
                first = prev;
                middle = node;
            }else{
                last = node;
            }
        }

        prev = node;

        inOrder(node.right);

    }

    public void recoverTree(TreeNode root) {
        first = middle = last = null;
        prev = new TreeNode(Integer.MIN_VALUE);
        inOrder(root);

        if(first!=null && last!=null){
            int t = first.val;
            first.val = last.val;
            last.val = t;
        }else if(first!=null && middle!=null){
            int t = first.val;
            first.val = middle.val;
            middle.val = t;
            
        }
    }
}
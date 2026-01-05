// https://leetcode.com/problems/binary-tree-level-order-traversal/description/

import java.util.*;

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

// If i want to find the height of the tree then i can use level order traversal and count the levels

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> myq = new LinkedList<TreeNode>();
        List<List<Integer>> ans = new ArrayList<>();
        if(root==null) return ans;
        myq.add(root);
        while(!myq.isEmpty()){
            int size = myq.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode node = myq.peek();
                myq.poll();
                if(node.left!=null)  myq.add(node.left);
                if(node.right!=null)  myq.add(node.right);                
                level.add(node.val);
            }
            ans.add(level);
        }
    return ans;
    }
}
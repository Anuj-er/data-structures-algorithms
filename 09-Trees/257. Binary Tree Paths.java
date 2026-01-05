// https://leetcode.com/problems/binary-tree-paths/description/

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

    List<String> ans = new ArrayList<>();

    public void traverse(TreeNode node,StringBuilder temp){
        if(node == null) return;

        int len = temp.length();

        if(len!=0){
            temp.append("->");
        }

        temp.append(node.val);
        if(node.left==null&&node.right==null){
            ans.add(temp.toString());
        }else{
            traverse(node.left,temp);
            traverse(node.right,temp);
        }

        temp.setLength(len);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        if(root!=null) traverse(root,new StringBuilder());
        return ans;
    }
}
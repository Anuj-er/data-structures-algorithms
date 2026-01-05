// https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/


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
// class Solution {
//     public List<Integer> rightSideView(TreeNode root) {
//         Queue<TreeNode> q = new LinkedList<>();
//         List<Integer> ans = new ArrayList<>();
//         if(root==null) return ans;
//         q.add(root);
//         while(!q.isEmpty()){
//             int size = q.size();
//             for(int i=0;i<size;i++){
//                 TreeNode node = q.poll();
//                 if(node.left!=null) q.add(node.left);
//                 if(node.right!=null) q.add(node.right);
//                 if(i==size-1){
//                     ans.add(node.val);
//                 }
//             }
//         }
//         return ans;
//     }
// }

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root,0,ans);
        return ans;
    }
    void dfs(TreeNode node,int depth,List<Integer> ans){
        if(node==null) return;
        if(depth==ans.size()){
            ans.add(node.val);
        }
        dfs(node.right,depth+1,ans);
        dfs(node.left,depth+1,ans);
    }
}

// https://leetcode.com/problems/find-mode-in-binary-search-tree/

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
    Map<Integer,Integer> mp;
    public void dfs(TreeNode node,Map<Integer,Integer> mp){
        if(node == null) return;
        if(!mp.containsKey(node.val)) mp.put(node.val,1);
        else mp.put(node.val,mp.get(node.val)+1);
        dfs(node.left,mp);
        dfs(node.right,mp);
    }
    public int[] findMode(TreeNode root) {
        mp = new HashMap<>();
        dfs(root,mp);
        int maxFreq = 0;
        for (int freq : mp.values()) {
            maxFreq = Math.max(maxFreq, freq);
        }
        List<Integer> result = new ArrayList<>();
        for(Integer key : mp.keySet()){
            if(mp.get(key)==maxFreq){
                result.add(key);
            }
        }
        int[] ans = new int[result.size()];
        for(int i=0;i<result.size();i++) ans[i] = result.get(i);

        return ans;
    }
}
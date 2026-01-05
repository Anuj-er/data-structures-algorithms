// https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/

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

class NodeInfo {
    TreeNode node;
    int col;

    NodeInfo(TreeNode node,int col){
        this.node = node;
        this.col = col;
    }
}

class Solution {

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (root == null)
            return ans;
        Queue<NodeInfo> myq = new LinkedList<NodeInfo>();
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        myq.offer(new NodeInfo(root, 0));
        while (!myq.isEmpty()) {

            int size = myq.size();

            Map<Integer, List<Integer>> temp = new HashMap<>();

            for (int i = 0; i < size; i++) {
                NodeInfo current = myq.poll();
                TreeNode node = current.node;
                int col = current.col;

                if (!temp.containsKey(col)) {
                    temp.put(col, new ArrayList<>());
                }
                temp.get(col).add(node.val);

                if (node.left != null)
                    myq.offer(new NodeInfo(node.left, col - 1));
                if (node.right != null)
                    myq.offer(new NodeInfo(node.right, col + 1));
            }
            for(int col: temp.keySet()){
                List<Integer> list = temp.get(col);
                Collections.sort(list);
                if(!map.containsKey(col)){
                    map.put(col,new ArrayList<>());
                }
                map.get(col).addAll(list);
            }
        }
        for (List<Integer> colList : map.values()) {
            ans.add(colList);
        }
        return ans;
    }
}
// https://leetcode.com/problems/same-tree/description/

class Solution {
    List<Integer> tree1;
    List<Integer> tree2;

    public boolean isSameTree(TreeNode p, TreeNode q) {
        tree1 = new ArrayList<>();
        tree2 = new ArrayList<>();

        traversal(p);
        traversal2(q);

        if(tree1.equals(tree2)){
            return true;
        }else{
            return false;
        }
    
    }

    public void traversal(TreeNode root){
        if(root == null){
            tree1.add(null);
            return;
        }
        tree1.add(root.val);
        traversal(root.left);
        traversal(root.right);
    }

    public void traversal2(TreeNode root){
        if(root == null){
            tree2.add(null);
            return;
        }
        tree2.add(root.val);
        traversal2(root.left);
        traversal2(root.right);
    }

}
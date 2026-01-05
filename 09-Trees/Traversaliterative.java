import java.util.*;

public class Traversaliterative {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> preOrderIteration(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null)
            return ans;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return ans;
    }

    public List<Integer> postOrderIteration(TreeNode root) {
        return null;
    }

    public List<Integer> inOrderIteration(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        while(true){
            if(root!=null){
                stack.push(root);
                root = root.left;
            }
            else{
                if(stack.isEmpty()) break;
                root = stack.pop();
                ans.add(root.val);
                root = root.right;
            }
        }
        return ans;
    }

    public List<Integer> levelOrderIteration(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans.add(node.val);
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        
        return ans;
    }

    public static void main(String[] args) {
        Traversaliterative tree = new Traversaliterative();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // List<Integer> result = tree.preOrderIteration(root); // 1 2 4 5 3
        // List<Integer> result = tree.inOrderIteration(root); // 4 2 5 1 3
        List<Integer> result = tree.postOrderIteration(root); // 4 5 2 1 3
        System.out.println(result); 
    }
}

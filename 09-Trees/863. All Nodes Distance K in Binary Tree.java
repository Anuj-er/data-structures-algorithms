/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    // Here we have created a Map for tracking the elements parents;
    private void markParents(TreeNode root, Map<TreeNode,TreeNode> parent_track, TreeNode target){
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode current = q.poll();

            // If left and right child is not null then only we will put it in the map
            if(current.left!=null){
                parent_track.put(current.left,current);
                q.offer(current.left);
            }

            // If left and right child is not null then only we will put it in the map
            if(current.right!=null){
                parent_track.put(current.right,current);
                q.offer(current.right);
            }
        }
    }


    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode,TreeNode> parent_track = new HashMap<>();
        markParents(root,parent_track,target);

        // Now we will do the bfs traversal from the target node and go upto k level

        // This map is used to check whether the node is visited or not
        Map<TreeNode,Boolean> visited = new HashMap<>();

        // Second we will do the bfs traversal
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(target);
        visited.put(target,true);
        int curr_level = 0;

        // BFS
        while(!q.isEmpty()){

            int size = q.size();
            if(curr_level == k) break;
            curr_level++;

            for(int i=0;i<size;i++){
                TreeNode current = q.poll();

                // Now we will check for the left child, right child and parent of the current node
                if(current.left!=null&&visited.get(current.left)==null){
                    q.offer(current.left);
                    visited.put(current.left,true);
                }
                if(current.right!=null&&visited.get(current.right)==null){
                    q.offer(current.right);
                    visited.put(current.right,true);
                }
                if(parent_track.get(current)!=null && visited.get(parent_track.get(current))==null){
                    q.offer(parent_track.get(current));
                    visited.put(parent_track.get(current),true);
                }
            }
        }

        // Now we will have the answer in the queue
        List<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            ans.add(temp.val);
        }

        return ans;

    }
}
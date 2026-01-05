// https://leetcode.com/problems/number-of-provinces/description/

class Solution {

    // DFS
    private void dfs(int node, int[][] isConnected , boolean[] vis,int size){
        vis[node] = true;
        for(int j=0;j<size;j++){
            if(isConnected[node][j] == 1 && !vis[j]){
                dfs(j,isConnected,vis,size);
            }
        }
    }

    // O(n^2) time | O(n) space
    // Approach: Count the number of connected components in the graph using DFS.
    // isConnected is the adjacency matrix representation of the graph.
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int count = 0;
        boolean[] vis = new boolean[n];

        for(int i=0;i<n;i++){
            if(!vis[i]){
                count++;
                dfs(i,isConnected,vis,n);
            }
        }
        return count;
    }
}
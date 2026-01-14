// https://www.geeksforgeeks.org/detect-cycle-undirected-graph/

class Solution {
    
    private boolean dfs(int curr,List<List<Integer>> graph,boolean[] vis,int parent){
        vis[curr] = true;
        for(int next : graph.get(curr)){
            if(!vis[next]) {
                if(dfs(next,graph,vis,curr)) return true;
            }
            else if(next != parent){
                return true;
            }
        }
        return false;
    }
    
    public boolean isCycle(int V, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        boolean[] vis = new boolean[V];
        for(int i = 0;i<V;i++) graph.add(new ArrayList<>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        for(int i = 0; i<V;i++){
            if(!vis[i]) if(dfs(i,graph,vis,-1)) return true;
        }
        return false;
    }
}
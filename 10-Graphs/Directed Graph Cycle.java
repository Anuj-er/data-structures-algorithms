// https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/

class Solution {
    private boolean dfs(int curr,List<List<Integer>> graph,boolean[] vis,boolean[] path){
        vis[curr] = true;
        path[curr] = true;
        for(int next : graph.get(curr)){
            if(!vis[next]) {
                if(dfs(next,graph,vis,path)) return true;
            }
            else if(path[next]){
                return true;
            }
        }
        path[curr] = false;
        return false;
    }
    
    public boolean isCyclic(int V, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        boolean[] vis = new boolean[V];
        boolean[] path = new boolean[V];
        for(int i = 0;i<V;i++) graph.add(new ArrayList<>());
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
        }
        for(int i = 0; i<V;i++){
            if(!vis[i]) if(dfs(i,graph,vis,path)) return true;
        }
        return false;
    }
}
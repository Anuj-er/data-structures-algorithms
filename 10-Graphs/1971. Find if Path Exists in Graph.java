// https://leetcode.com/problems/find-if-path-exists-in-graph/description/

class Solution {

    boolean[] vis;
    List<List<Integer>> graph;

    private boolean dfs(int curr, int destination){
        if(vis[destination]) return true; 
        vis[curr] = true;

        if(curr == destination){
            return true;
        }

        for(int nex : graph.get(curr)){
            if(!vis[nex]){
                if(dfs(nex, destination))
                    return true;
            }
        }
        return false;
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        graph = new ArrayList<>();
        vis = new boolean[n];

        for(int i = 0; i < n; i++) 
            graph.add(new ArrayList<>());

        for(int[] e : edges){
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        return dfs(source, destination);
    }
}
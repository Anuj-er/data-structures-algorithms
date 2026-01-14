class Solution {
    
    private void dfs(int curr,List<List<Integer>> g, boolean[] vis,Stack<Integer> st){
        vis[curr] = true;
        for(int next : g.get(curr)){
            if(!vis[next]) dfs(next,g,vis,st);
        }
        st.push(curr);
    }
    
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();
        boolean[] vis = new boolean[V];
        List<List<Integer>> g = new ArrayList<>();
        for(int i = 0;i<V;i++) g.add(new ArrayList<>());
        for(int[] edge: edges){
            g.get(edge[0]).add(edge[1]);
        }
        for(int i=0;i<V;i++){
            if(!vis[i]) dfs(i,g,vis,st);
        }
        while(!st.isEmpty()){
            ans.add(st.pop());
        }
        return ans;
    }
}

class Solution2 {
    
    private void bfs(int V,List<List<Integer>> g,int[] indeg,ArrayList<Integer> ans){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        // Adding First Element in the Queue With Indegree Zero;
        for(int i = 0;i<V;i++) if(indeg[i] == 0) q.add(i);
        
        while(!q.isEmpty()){
            int curr = q.poll();
            ans.add(curr);
            for(int next : g.get(curr)){
                indeg[next]--;
                if(indeg[next] == 0){
                    q.add(next);
                }
            }
        }
    }
    
    public ArrayList<Integer> topoSort(int V, int[][] edges) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        // Creating Adjancy List
        List<List<Integer>> g = new ArrayList<>();
        for(int i = 0;i<V;i++) g.add(new ArrayList<>());
        for(int[] edge: edges){
            g.get(edge[0]).add(edge[1]);
        }
        
        // indegree Array;
        int[] indeg = new int[V];
        for(int i=0;i<V;i++){
            for(int e: g.get(i)){
                indeg[e]++;
            }
        }
        bfs(V,g,indeg,ans);
        return ans;
    }
}
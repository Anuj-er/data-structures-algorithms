// https://www.geeksforgeeks.org/problems/minimum-spanning-tree/

class Solution {

    private int ans = 0;
    
    private int getAns(List<List<int[]>> adj,PriorityQueue<int[]> pq,boolean[] vis){
        pq.add(new int[]{0,0});
        
        while(!pq.isEmpty()){
            int[] p = pq.poll();
            int node = p[0];
            int wt = p[1];
            
            if(vis[node]) continue;
            
            vis[node] = true;
            ans += wt;
            
            for(int[] next : adj.get(node)){
                int u = next[0];
                int w = next[1];
                if(!vis[u]) pq.add(new int[]{u,w});
            }
        }
        return ans;
    }
    
    public int spanningTree(int V, int[][] edges) {
        // Created Adjancy List;
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0;i<V;i++) adj.add(new ArrayList<>());
        for(int[] e: edges){
            int u = e[0];
            int v = e[1];
            int w = e[2];
            adj.get(u).add(new int[]{v,w});
            adj.get(v).add(new int[]{u,w});
        }
        
        boolean[] vis = new boolean[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1],b[1]));
        
        return getAns(adj,pq,vis);
    }
}


// class Solution {
//     public int spanningTree(int V, int[][] edges) {
//         // Created Adjancy List;
//         List<List<int[]>> adj = new ArrayList<>();
//         for(int i = 0;i<V;i++) adj.add(new ArrayList<>());
//         for(int[] e: edges){
//             int u = e[0];
//             int v = e[1];
//             int w = e[2];
//             adj.get(u).add(new int[]{v,w});
//             adj.get(v).add(new int[]{u,w});
//         }
        
//         boolean[] vis = new boolean[V];
        
//         PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1],b[1]));
        
//         pq.add(new int[]{0,0});
        
//         int ans = 0;
        
//         while(!pq.isEmpty()){
//             int[] p = pq.poll();
//             int node = p[0];
//             int wt = p[1];
            
//             if(vis[node]) continue;
            
//             vis[node] = true;
//             ans += wt;
            
//             for(int[] next : adj.get(node)){
//                 int nei = next[0];
//                 int w = next[1];
//                 if(!vis[nei]) pq.add(new int[]{nei,w});
//             }
//         }
//         return ans;
//     }
// }

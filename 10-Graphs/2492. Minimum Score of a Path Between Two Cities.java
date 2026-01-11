// https://leetcode.cn/problems/minimum-score-of-a-path-between-two-cities/

class Solution {

    int min = Integer.MAX_VALUE;
    boolean[] vis;


    private void bfs(int start,List<List<int[]>> graph){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        vis[start] = true;
        q.add(start);
        while(!q.isEmpty()){
            int curr = q.poll();
            for(int[] edge: graph.get(curr)){
                int next = edge[0];
                int weight = edge[1];
                min = Math.min(min,weight);
                if(!vis[next]){
                    vis[next] = true;
                    q.add(next);
                }
            }
        }
    }

    private void dfs(int curr, List<List<int[]>> graph){
        vis[curr] = true;
        for(int[] edge: graph.get(curr)){
            int next = edge[0];
            int weight = edge[1];
            min = Math.min(min,weight);
            if(!vis[next]) dfs(next,graph);
        }
    }

    public int minScore(int n, int[][] roads) {
        vis = new boolean[n];
        List<List<int[]>> graph = new ArrayList<>();
        for(int i = 0;i<n;i++) graph.add(new ArrayList<>());
        for(int[] r : roads){
            int u = r[0] - 1;
            int v = r[1] - 1;
            int w = r[2];
            graph.get(u).add(new int[]{v,w});
            graph.get(v).add(new int[]{u,w});
        }
        dfs(0,graph);
        // bfs(0,graph);
        return min;
    }
}

// class Solution {
//     public int minScore(int n, int[][] roads) {
//         int min = Integer.MAX_VALUE;
//         for(int[] road : roads){
//             min = Math.min(min,road[2]);
//         }
//         return min;
//     }
// }
1class Solution {
2
3    int min = Integer.MAX_VALUE;
4    boolean[] vis;
5
6
7    private void bfs(int start,List<List<int[]>> graph){
8        ArrayDeque<Integer> q = new ArrayDeque<>();
9        vis[start] = true;
10        q.add(start);
11        while(!q.isEmpty()){
12            int curr = q.poll();
13            for(int[] edge: graph.get(curr)){
14                int next = edge[0];
15                int weight = edge[1];
16                min = Math.min(min,weight);
17                if(!vis[next]){
18                    vis[next] = true;
19                    q.add(next);
20                }
21            }
22        }
23    }
24
25    private void dfs(int curr, List<List<int[]>> graph){
26        vis[curr] = true;
27        for(int[] edge: graph.get(curr)){
28            int next = edge[0];
29            int weight = edge[1];
30            min = Math.min(min,weight);
31            if(!vis[next]) dfs(next,graph);
32        }
33    }
34
35    public int minScore(int n, int[][] roads) {
36        vis = new boolean[n];
37        List<List<int[]>> graph = new ArrayList<>();
38        for(int i = 0;i<n;i++) graph.add(new ArrayList<>());
39        for(int[] r : roads){
40            int u = r[0] - 1;
41            int v = r[1] - 1;
42            int w = r[2];
43            graph.get(u).add(new int[]{v,w});
44            graph.get(v).add(new int[]{u,w});
45        }
46        dfs(0,graph);
47        // bfs(0,graph);
48        return min;
49    }
50}
51
52// class Solution {
53//     public int minScore(int n, int[][] roads) {
54//         int min = Integer.MAX_VALUE;
55//         for(int[] road : roads){
56//             min = Math.min(min,road[2]);
57//         }
58//         return min;
59//     }
60// }
1class Solution {
2    public int countCompleteComponents(int n, int[][] edges) {
3        DisjointSetPotd dsj=new DisjointSetPotd(n);
4        for(int[]row:edges){
5            dsj.unionByRank(row[0],row[1]);
6        }
7        int ans=0;
8        for(int i=0;i<n;i++){
9            dsj.findParent(i);
10        }
11        Map<Integer,Integer>vertex=new HashMap<>();
12        Map<Integer,Integer>edgeCount=new HashMap<>();
13        for(int i=0;i<n;i++){
14            int root= dsj.parent[i];
15            vertex.put(root,vertex.getOrDefault(root,0)+1);
16        }
17        for(int[]row:edges){
18            int root=dsj.findParent(row[0]);
19            edgeCount.put(root,edgeCount.getOrDefault(root,0)+1);
20        }
21        for(int root:vertex.keySet()){
22            int v=vertex.get(root);
23            int e=edgeCount.getOrDefault(root,0);
24            if(e==v*(v-1)/2)ans++;
25        }
26        return ans;
27    }
28    public static class DisjointSetPotd{
29        int[]rank;
30        int[]parent;
31        DisjointSetPotd(int n){
32            rank=new int[n];
33            parent=new int[n];
34            for(int i=0;i<n;i++){
35                rank[i]=0;
36                parent[i]=i;
37            }
38        }
39        public int findParent(int node){
40            if(node==parent[node])return node;
41            int ultimateParent=findParent(parent[node]);
42            parent[node]=ultimateParent;
43            return parent[node];
44        }
45        public void unionByRank(int u,int v){
46            int pu=findParent(u);
47            int pv=findParent(v);
48            if(pu==pv)return;
49            if(rank[pv]<rank[pu]){
50                parent[pv]=pu;
51            }else if(rank[pv]>rank[pu]){
52                parent[pu]=pv;
53            }else{
54                parent[pv]=pu;
55                int rankU=rank[pu];
56                rank[pu]=rankU+1;
57            }
58        }
59    }
60}
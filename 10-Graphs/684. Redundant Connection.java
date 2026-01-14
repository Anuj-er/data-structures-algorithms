// https://leetcode.com/problems/redundant-connection/description/

class Solution {

    int[] parent;
    int[] size;
    public int findParent(int node){
        if(parent[node] == node) return node;
        return parent[node] = findParent(parent[node]);
    }
    public boolean unionBySize(int a,int b){
        int pa = findParent(a);
        int pb = findParent(b);
        if(pa == pb) return false;
        if(size[pa] < size[pb]){
            parent[pa] = pb;
            size[pb]+=size[pa];
        }else if(size[pa]>size[pb]){
            parent[pb] = pa;
            size[pa]+=size[pb];
        }else{
            parent[pa] = pb;
            size[pb]+=size[pa];
        }
        return true;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int nodes = edges.length;
        parent = new int[nodes];
        size = new int[nodes];

        for(int i = 0;i<nodes;i++){
            parent[i] = i;
            size[i] = 1;
        }
        
        for(int[] e : edges){
            if(!unionBySize(e[0]-1,e[1]-1)) return e;
        }
        return new int[]{};
    }
}

// class Solution {
//     public int[] findRedundantConnection(int[][] edges) {
//         int totalNodes = edges.length;
//         DisjointSet dsu = new DisjointSet(totalNodes);
//         for(int[] e : edges){
//             if(!dsu.unionBySize(e[0]-1,e[1]-1)) return e;
//         }
//         return new int[]{};
//     }
// }

// // Union by size; 
// public class DisjointSet{
//     int parent[];
//     int size[];
//     DisjointSet(int totalNodes){
//         this.parent = new int[totalNodes];
//         this.size = new int[totalNodes];
//         for(int i = 0;i<totalNodes;i++){
//             this.parent[i] = i;
//             this.size[i] = 1;
//         }
//     }
//     public int findParent(int node){
//         if(parent[node] == node) return node;
//         return parent[node] = findParent(parent[node]);
//     }
//     public boolean unionBySize(int a,int b){
//         int pa = findParent(a);
//         int pb = findParent(b);
//         if(pa == pb) return false;
//         if(size[pa] < size[pb]){
//             parent[pa] = pb;
//             size[pb]+=size[pa];
//         }else if(size[pa]>size[pb]){
//             parent[pb] = pa;
//             size[pa]+=size[pb];
//         }else{
//             parent[pa] = pb;
//             size[pb]+=size[pa];
//         }
//         return true;
//     }
// }

// Union by Rank
// public class DisjointSet{
//     int parent[];
//     int rank[];
//     DisjointSet(int totalNodes){
//         this.parent = new int[totalNodes];
//         this.rank = new int[totalNodes];
//         for(int i = 0;i<totalNodes;i++){
//             this.parent[i] = i;
//             this.rank[i] = 0;
//         }
//     }
//     public int findParent(int node){
//         if(parent[node] == node) return node;
//         return parent[node] = findParent(parent[node]);
//     }
//     public boolean unionByRank(int a,int b){
//         int pa = findParent(a);
//         int pb = findParent(b);
//         if(parent[a] == parent[b]) return false;
//         if(rank[pa] == rank[pb]){
//             parent[pb] = pa;
//             rank[pa]++;
//         }else if(rank[pa]>rank[pb]){
//             parent[pb] = pa;
//         }else{
//             parent[pa] = pb;
//         }
//         return true;
//     }
// }
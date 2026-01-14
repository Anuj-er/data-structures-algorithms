// https://leetcode.com/problems/number-of-provinces/description/

class Solution1 {
    public int findCircleNum(int[][] isConnected) {
        DisjointSet dsu = new DisjointSet(isConnected.length);
        for(int i = 0;i<isConnected.length;i++){
            for(int j = i+1;j<isConnected[0].length;j++){
                if(isConnected[i][j] == 1){
                    dsu.unionBySize(i,j);
                }
            }
        }
        return dsu.components;
    }
}

public class DisjointSet{
    int parent[];
    int size[];
    int components;
    DisjointSet(int totalNodes){
        this.parent = new int[totalNodes];
        this.size = new int[totalNodes];
        this.components = totalNodes;
        for(int i = 0;i<totalNodes;i++){
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }
    public int findParent(int node){
        if(parent[node] == node) return node;
        return parent[node] = findParent(parent[node]);
    }
    public boolean unionBySize(int a,int b){
        int pa = findParent(a);
        int pb = findParent(b);
        if(pa==pb) return false;
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
        components--;
        return true;
    }
}


class Solution2 {

    private void bfsArrayDeque(int start,int[][] isConnected,boolean[] vis,int size,ArrayDeque<Integer> q){
        vis[start] = true;
        q.offer(start);
        while(!q.isEmpty()){
            int node = q.poll();
            for(int j = 0;j<size;j++){
                if(isConnected[node][j] != 0 && !vis[j]) bfsArrayDeque(j,isConnected,vis,size,q);
            }
        }
    }

    private void bfsLinkedList(int start,int[][] isConnected,boolean[] vis,int size,Queue<Integer> q){
        vis[start] = true;
        q.add(start);
        while(!q.isEmpty()){
            int node = q.poll();
            for(int j = 0;j<size;j++){
                if(isConnected[node][j] != 0 && !vis[j]) bfsLinkedList(j,isConnected,vis,size,q);
            }
        }
    }

    private void dfs(int node, int[][] isConnected, boolean[] vis, int size) {
        vis[node] = true;
        for (int j = 0; j < size; j++) {
            if (isConnected[node][j] != 0 && !vis[j]) {
                dfs(j, isConnected, vis, size);
            }
        }
    }

    public int findCircleNum(int[][] isConnected) {
        int count = 0;
        int size = isConnected.length;

        boolean[] vis = new boolean[size];

        // Queue<Integer> q = new LinkedList<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i<size ;i++){
            if(!vis[i]){
                count++;
                // dfs(i, isConnected, vis, size);
                bfsArrayDeque(i,isConnected,vis,size,q);
                // bfsLinkedList(i,isConnected,vis,size,q);
            }
        }

        return count;
    }

}
// https://leetcode.com/problems/number-of-provinces/description/

class Solution {

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
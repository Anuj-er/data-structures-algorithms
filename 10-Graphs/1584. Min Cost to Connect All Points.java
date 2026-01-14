// https://leetcode.com/problems/min-cost-to-connect-all-points/description/

class Solution {
    public int minCostConnectPoints(int[][] points) {
       int len = points.length;
       boolean[] vis = new boolean[len];
       int[] minDist = new int[len];
       Arrays.fill(minDist,Integer.MAX_VALUE);
       minDist[0] = 0;
       int answer = 0;
       for(int i=0;i<len;i++){
        int u = -1;
        int currMin = Integer.MAX_VALUE;
        for(int j = 0;j<len;j++){
            if(!vis[j] && minDist[j] < currMin){
                currMin = minDist[j];
                u = j;
            }
        }
        vis[u] = true;
        answer += currMin;
        for(int v = 0 ;v < len;v++){
            if(!vis[v]) {
                int dist = Math.abs(points[u][0]-points[v][0]) + 
                Math.abs(points[u][1]-points[v][1]);
                minDist[v] = Math.min(minDist[v],dist);
            }
        }
       }
       return answer;
    }
}
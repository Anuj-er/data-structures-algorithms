// https://leetcode.com/problems/rotting-oranges/description/


// Pair Class to store the row, column, and time
class Pair {
    int row;
    int col;
    int tm;

    Pair(int row, int col, int tm) {
        this.row = row;
        this.col = col;
        this.tm = tm;
    }
}

class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<Pair> q = new LinkedList<>();
        int[][] vis = new int[rows][cols];
        int cntFresh = 0;

        // Step 1: Initialize the queue with all rotten oranges and count fresh oranges
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 2) {
                    q.add(new Pair(row, col, 0));
                    vis[row][col] = 2;
                } else {
                    vis[row][col] = 0;
                }
                if (grid[row][col] == 1)
                    cntFresh++;
            }
        }


        // Step 2: BFS to rot adjacent fresh oranges
        int tm = 0;

        // Direction vectors for right, up, left, down
        int[] drow = {0,-1,0,1};
        int[] dcol = {1,0,-1,0};
        int cnt = 0;

        // BFS traversal
        while(!q.isEmpty()){
            int r = q.peek().row;
            int c = q.peek().col;
            int t = q.peek().tm;
            q.remove();

            // Update the maximum time
            tm = Math.max(tm,t);

            for(int i=0;i<4;i++){
                int nrow = r + drow[i];
                int ncol = c + dcol[i]; 

                if(nrow>=0 && ncol>=0 && nrow < rows && ncol < cols){
                    if(vis[nrow][ncol] == 0 && grid[nrow][ncol]==1){
                        // Rot the fresh orange
                        q.add(new Pair(nrow,ncol,t+1));
                        // Mark as visited (rotten)
                        vis[nrow][ncol] = 1;
                        // Increase the count of rotten oranges
                        cnt++;
                    }
                }
            }
        }
        // If all fresh oranges are not rotten, return -1
        if(cnt!=cntFresh) return -1;
        return tm;
    }
}
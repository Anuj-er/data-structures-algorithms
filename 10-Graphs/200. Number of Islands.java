// https://leetcode.com/problems/number-of-islands/description/

class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {

    // DFS Approach
    private void dfs(int row, int col, char[][] grid, int[][] vis) {
        int n = grid.length, m = grid[0].length;

        vis[row][col] = 1;

        // 4 directions
        int[] drow = { -1, 0, 1, 0 };
        int[] dcol = { 0, 1, 0, -1 };

        for (int i = 0; i < 4; i++) {
            int nrow = row + drow[i];
            int ncol = col + dcol[i];
            
            // check for valid coordinates
            if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < m) {
                // unvisited land
                if (vis[nrow][ncol] == 0 && grid[nrow][ncol] == '1') {
                    dfs(nrow, ncol, grid, vis);
                }
            }
        }
    }

    // BFS Approach
    private void bfs(int row, int col, char[][] grid, int[][] vis) {
        vis[row][col] = 1;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(row, col));
        int n = grid.length;
        int m = grid[0].length;

        while (!q.isEmpty()) {
            int rowl = q.peek().first;
            int coll = q.peek().second;
            q.remove();

            int[] drow = { -1, 0, 1, 0 };
            int[] dcol = { 0, -1, 0, 1 };

            for (int i = 0; i < 4; i++) {
                int nrow = rowl + drow[i];
                int ncol = coll + dcol[i];
                if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < m) {
                    if (vis[nrow][ncol] == 0 && grid[nrow][ncol] == '1') {
                        vis[nrow][ncol] = 1;
                        q.add(new Pair(nrow, ncol));
                    }
                }
            }
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] vis = new int[n][m];

        int count = 0;

        // traversing the grid
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                // unvisited land
                if (vis[row][col] == 0 && grid[row][col] == '1') {
                    count++;
                    dfs(row, col, grid, vis);
                }
            }
        }
        return count;
    }
}
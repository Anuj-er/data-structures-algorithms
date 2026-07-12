1class Solution {
2    public int minPathSum(int[][] grid) {
3        int m = grid.length;
4        int n = grid[0].length;
5        
6        for (int i = 1; i < m; i++) {
7            grid[i][0] += grid[i-1][0];
8        }
9        
10        for (int j = 1; j < n; j++) {
11            grid[0][j] += grid[0][j-1];
12        }
13        for (int i = 1; i < m; i++) {
14            for (int j = 1; j < n; j++) {
15                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
16            }
17        }
18        
19        return grid[m-1][n-1];
20    }
21}
22
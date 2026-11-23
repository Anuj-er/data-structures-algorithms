1class Solution {
2    public int countServers(int[][] grid) {
3        int m = grid.length, n = grid[0].length;
4        int[] rows = new int[m], cols = new int[n];  // servers on each row and column
5        for (int i = 0; i < m; i++)
6            for (int j = 0; j < n; j++) {
7                rows[i] += grid[i][j];
8                cols[j] += grid[i][j];
9            }
10
11        int ans = 0;
12        for (int i = 0; i < m; i++)
13            for (int j = 0; j < n; j++)
14                if (grid[i][j] == 1 && rows[i] + cols[j] > 2)  // it's counted in both, so more than 2 means company
15                    ans++;
16        return ans;
17    }
18}
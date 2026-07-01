1class Solution {
2    public int maximumSafenessFactor(List<List<Integer>> grid) {
3
4        int n = grid.size();
5
6        int[][] dist = new int[n][n];
7        for (int[] row : dist)
8            Arrays.fill(row, -1);
9
10        Queue<int[]> queue = new LinkedList<>();
11
12        // Start BFS from every thief
13        for (int i = 0; i < n; i++) {
14            for (int j = 0; j < n; j++) {
15                if (grid.get(i).get(j) == 1) {
16                    dist[i][j] = 0;
17                    queue.offer(new int[] { i, j });
18                }
19            }
20        }
21
22        int[] dx = { -1, 0, 1, 0 };
23        int[] dy = { 0, 1, 0, -1 };
24
25        // Multi-source BFS
26        while (!queue.isEmpty()) {
27            int[] cur = queue.poll();
28
29            for (int k = 0; k < 4; k++) {
30                int nx = cur[0] + dx[k];
31                int ny = cur[1] + dy[k];
32
33                if (nx < 0 || ny < 0 || nx >= n || ny >= n || dist[nx][ny] != -1)
34                    continue;
35
36                dist[nx][ny] = dist[cur[0]][cur[1]] + 1;
37                queue.offer(new int[] { nx, ny });
38            }
39        }
40
41        int left = 0;
42        int right = 2 * n;
43        int ans = 0;
44
45        while (left <= right) {
46
47            int mid = left + (right - left) / 2;
48
49            if (canReach(dist, mid, n)) {
50                ans = mid;
51                left = mid + 1;
52            } else {
53                right = mid - 1;
54            }
55        }
56
57        return ans;
58    }
59
60    private boolean canReach(int[][] dist, int limit, int n) {
61
62        if (dist[0][0] < limit || dist[n - 1][n - 1] < limit)
63            return false;
64
65        Queue<int[]> queue = new LinkedList<>();
66        boolean[][] vis = new boolean[n][n];
67
68        queue.offer(new int[] { 0, 0 });
69        vis[0][0] = true;
70
71        int[] dx = { -1, 0, 1, 0 };
72        int[] dy = { 0, 1, 0, -1 };
73
74        while (!queue.isEmpty()) {
75
76            int[] cur = queue.poll();
77
78            if (cur[0] == n - 1 && cur[1] == n - 1)
79                return true;
80
81            for (int k = 0; k < 4; k++) {
82
83                int nx = cur[0] + dx[k];
84                int ny = cur[1] + dy[k];
85
86                if (nx < 0 || ny < 0 || nx >= n || ny >= n)
87                    continue;
88
89                if (vis[nx][ny] || dist[nx][ny] < limit)
90                    continue;
91
92                vis[nx][ny] = true;
93                queue.offer(new int[] { nx, ny });
94            }
95        }
96
97        return false;
98    }
99}
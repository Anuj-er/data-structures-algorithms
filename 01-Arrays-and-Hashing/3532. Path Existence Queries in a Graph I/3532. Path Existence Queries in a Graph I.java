1class Solution {
2    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
3        int[] component = new int[n];
4        int cmpid = 0;
5
6        // Assign component IDs
7        for (int i = 1; i < n; i++) {
8            if (nums[i] - nums[i - 1] <= maxDiff) {
9                component[i] = cmpid;
10            } else {
11                cmpid++;
12                component[i] = cmpid;
13            }
14        }
15
16        // Answer queries
17        boolean[] ans = new boolean[queries.length];
18        for (int i = 0; i < queries.length; i++) {
19            int u = queries[i][0];
20            int v = queries[i][1];
21            ans[i] = component[u] == component[v];
22        }
23
24        return ans;
25    }
26}
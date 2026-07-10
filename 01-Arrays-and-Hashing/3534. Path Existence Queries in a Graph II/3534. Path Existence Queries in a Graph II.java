1class Solution {
2    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
3        int[][] newNums = new int[n][2];
4        for (int i = 0; i < n; i++) {
5            newNums[i] = new int[] { nums[i], i };
6        }
7
8        Arrays.sort(newNums, (a, b) -> a[0] - b[0]);
9
10        int[] getI = new int[n];
11        for (int i = 0; i < n; i++) {
12            getI[newNums[i][1]] = i;
13        }
14
15        int[][] st = new int[n][18];
16        int r = 0;
17
18        for (int i = 0; i < n; i++) {
19            if (r < i) r = i;
20
21            while (r + 1 < n &&
22                    newNums[r + 1][0] - newNums[r][0] <= maxDiff &&
23                    newNums[r + 1][0] - newNums[i][0] <= maxDiff) {
24                r++;
25            }
26
27            st[i][0] = r;
28        }
29
30        for (int j = 1; j < 18; j++) {
31            for (int i = 0; i < n; i++) {
32                st[i][j] = st[st[i][j - 1]][j - 1];
33            }
34        }
35
36        int[] ans = new int[queries.length];
37
38        for (int i = 0; i < queries.length; i++) {
39            int a = getI[queries[i][0]];
40            int b = getI[queries[i][1]];
41
42            if (a > b) {
43                int t = a;
44                a = b;
45                b = t;
46            }
47
48            if (a == b) {
49                ans[i] = 0;
50                continue;
51            }
52
53            int curr = a;
54            int steps = 0;
55
56            for (int j = 17; j >= 0; j--) {
57                if (st[curr][j] < b) {
58                    curr = st[curr][j];
59                    steps += 1 << j;
60                }
61            }
62
63            ans[i] = (st[curr][0] >= b) ? steps + 1 : -1;
64        }
65
66        return ans;
67    }
68}
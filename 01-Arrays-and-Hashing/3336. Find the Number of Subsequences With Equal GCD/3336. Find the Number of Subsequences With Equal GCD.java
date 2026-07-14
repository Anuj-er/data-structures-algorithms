1class Solution {
2    private static final int MOD = 1_000_000_007;
3    private int n;
4    private int[][][] dp;
5
6    private int solve(int idx, int g1, int g2, int[] nums) {
7        if (idx == n) {
8            return (g1 != 0 && g1 == g2) ? 1 : 0;
9        }
10
11        if (dp[idx][g1][g2] != -1)
12            return dp[idx][g1][g2];
13
14        long ans = 0;
15        ans = solve(idx + 1, g1, g2, nums);
16
17        int ng1 = (g1 == 0) ? nums[idx] : gcd(g1, nums[idx]);
18        ans = (ans + solve(idx + 1, ng1, g2, nums)) % MOD;
19
20        int ng2 = (g2 == 0) ? nums[idx] : gcd(g2, nums[idx]);
21        ans = (ans + solve(idx + 1, g1, ng2, nums)) % MOD;
22
23        return dp[idx][g1][g2] = (int) ans;
24    }
25
26    public int subsequencePairCount(int[] nums) {
27        n = nums.length;
28        dp = new int[n + 1][201][201];
29
30        for (int i = 0; i <= n; i++) {
31            for (int j = 0; j <= 200; j++) {
32                Arrays.fill(dp[i][j], -1);
33            }
34        }
35
36        return solve(0, 0, 0, nums);
37    }
38
39    private int gcd(int a, int b) {
40        while (b != 0) {
41            int t = a % b;
42            a = b;
43            b = t;
44        }
45        return a;
46    }
47}
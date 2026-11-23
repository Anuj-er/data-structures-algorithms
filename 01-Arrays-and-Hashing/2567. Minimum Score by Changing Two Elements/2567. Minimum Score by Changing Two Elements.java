1class Solution {
2    public int minimizeSum(int[] nums) {
3        int[] small = new int[3], neg = new int[3];  // the 3 smallest values, and the 3 smallest negated ones: minus the 3 largest
4        Arrays.fill(small, Integer.MAX_VALUE);
5        Arrays.fill(neg, Integer.MAX_VALUE);
6        for (int x : nums) {
7            keep(small, x);
8            keep(neg, -x);
9        }
10        return Math.min(-neg[0] - small[2], Math.min(-neg[1] - small[1], -neg[2] - small[0]));
11    }
12
13    private void keep(int[] three, int x) {  // three: the 3 smallest values so far, ascending
14        if (x >= three[2])
15            return;
16        int i = 2;
17        for (; i > 0 && three[i - 1] > x; i--)
18            three[i] = three[i - 1];
19        three[i] = x;
20    }
21}
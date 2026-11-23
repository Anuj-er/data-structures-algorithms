1class Solution {
2    public int maxSelectedElements(int[] nums) {
3        Arrays.sort(nums);
4        // New Solution
5        int best = 1, keep = 1, bump = 1, last = -10;
6        for (int x : nums) {
7            switch (x - last) {
8                case 0 -> bump = keep + 1;  // x repeat: bump it on top of keep
9                case 1 -> { keep++; bump++; }  // x extends keep, x + 1 extends bump
10                case 2 -> { keep = bump + 1; bump = 1; }  // x extends bump, x + 1 starts over
11                default -> keep = bump = 1;
12            }
13            best = Math.max(best, Math.max(keep, bump));
14            last = x;
15        }
16        return best;
17    }
18}
1class Solution {
2    public int maxSelectedElements(int[] nums) {
3        Arrays.sort(nums);
4        int best = 1, keep = 1, bump = 1, last = -10;
5        for (int x : nums) {
6            switch (x - last) {
7                case 0 -> bump = keep + 1;  // x repeat: bump it on top of keep
8                case 1 -> { keep++; bump++; }  // x extends keep, x + 1 extends bump
9                case 2 -> { keep = bump + 1; bump = 1; }  // x extends bump, x + 1 starts over
10                default -> keep = bump = 1;
11            }
12            best = Math.max(best, Math.max(keep, bump));
13            last = x;
14        }
15        return best;
16    }
17}
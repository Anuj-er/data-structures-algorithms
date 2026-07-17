1class Solution {
2    public long gcdSum(int[] nums) {
3        int n = nums.length;
4        int mx = 0;
5
6        for (int i = 0; i < n; i++) {
7            mx = Math.max(mx, nums[i]);
8            nums[i] = gcd(mx, nums[i]);
9        }
10
11        Arrays.sort(nums);
12
13        int left = 0, right = n - 1;
14        long ans = 0;
15
16        while (left < right) {
17            ans += gcd(nums[left], nums[right]);
18            left++;
19            right--;
20        }
21
22        return ans;
23    }
24
25    private int gcd(int a, int b) {
26        while (b != 0) {
27            int t = a % b;
28            a = b;
29            b = t;
30        }
31        return a;
32    }
33}
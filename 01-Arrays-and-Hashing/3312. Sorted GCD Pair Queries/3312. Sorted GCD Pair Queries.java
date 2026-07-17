class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int MAX = 0;
        for (int num : nums) {
            MAX = Math.max(MAX, num);
        }
        int[] freq = new int[MAX + 1]; // freq of elements in arr
        // used for calculating cnt[d]
        // no of elements divisible by d 
        // that is equal to cnt[multiples of d]
        for (int num : nums) {
            freq[num]++;
        }
        long[] cnt = new long[MAX + 1];
        for (int d = 1; d <= MAX; d++) {
            for (int multiple = d; multiple <= MAX; multiple += d) {
                cnt[d] += freq[multiple];
            }
        }
        long[] exact = new long[MAX + 1];
        for (int d = MAX; d > 0; d--) {
            exact[d] = cnt[d] * (cnt[d] - 1) / 2;
            for (int multiple = 2 * d; multiple <= MAX; multiple += d) {
                exact[d] -= exact[multiple];
            }
        }
        for (int i = 1; i <= MAX; i++) {
            exact[i] += exact[i - 1];
        }
        int m = queries.length;
        int[] answer = new int[m];
        for (int q = 0; q < m; q++) {
            long qu = queries[q] + 1;
            int l = 1;
            int r = MAX;
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (exact[mid] < qu) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            answer[q] = l;
        }
        return answer;
    }

}

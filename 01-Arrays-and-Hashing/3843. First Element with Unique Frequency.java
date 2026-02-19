// https://leetcode.com/problems/first-element-with-unique-frequency/

class Solution {
    public int firstUniqueFreq(int[] nums) {
        int n = nums.length;

        int[] freq = new int[100001];
        for (int x : nums)
            freq[x]++;

        int[] freqCount = new int[n + 1];
        for (int x : freq) {
            if (x > 0)
                freqCount[x]++;
        }

        for (int x : nums) {
            if (freqCount[freq[x]] == 1)
                return x;
        }

        return -1;
    }
}
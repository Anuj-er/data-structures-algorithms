// https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> maxDeque = new ArrayDeque<>(); // tracks max values in window (decreasing order)
        Deque<Integer> minDeque = new ArrayDeque<>(); // tracks min values in window (increasing order)
        int left = 0;
        int res = 0;

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];

            // maintain maxDeque in decreasing order
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < num) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(num);

            // maintain minDeque in increasing order
            while (!minDeque.isEmpty() && minDeque.peekLast() > num) {
                minDeque.pollLast();
            }
            minDeque.offerLast(num);

            // shrink window if max - min exceeds limit
            while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                if (maxDeque.peekFirst() == nums[left]) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == nums[left]) {
                    minDeque.pollFirst();
                }
                left++; // move window from left
            }

            // update longest valid window length
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
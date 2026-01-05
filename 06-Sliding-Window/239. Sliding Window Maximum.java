// https://leetcode.com/problems/sliding-window-maximum/
import java.util.*;

// Brute Force Approach
// class Solution {
//     public int[] maxSlidingWindow(int[] nums, int k) {
//         int size = nums.length;
//         int[] res = new int[size-k+1];
//         for(int i=0;i<size-k+1;i++){
//             int max = -100000000;
//             for(int j=i;j<i+k;j++){
//                 if(nums[j]>max){
//                     max=nums[j];
//                 }
//             }
//             res[i] = max;
//         }
//         return res;
//     }
// }


// Optimized Approach using Deque
// This approach uses a deque to keep track of the indices of the maximum elements in the current
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // If the array is empty, there are no windows, so return an empty array.
        if (n == 0) {
            return new int[0];
        }
        // This will store the maximum value for each sliding window.
        int[] res = new int[n - k + 1];
        int ri = 0;
        // The deque will store indices of elements in a way that the element at the front is the maximum.
        // It maintains a decreasing order of values from front to back.
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // 1. Remove indices that are out of the current window.
            // The index at the front of the deque is the oldest.
            // If it's no longer in the window (i - k), remove it.
            if (!q.isEmpty() && q.peek() == i - k) {
                q.poll();
            }

            // 2. Maintain the decreasing order in the deque.
            // We remove elements from the back of the deque that are smaller than the current element (nums[i]).
            // This is because the new, larger element makes the smaller ones irrelevant for future maximums.
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.pollLast();
            }

            // 3. Add the current element's index to the back of the deque.
            q.offer(i);

            // 4. Record the maximum for the fully formed window.
            // The first time a window is complete is when i reaches k-1.
            // After this, for every 'i', we have a new complete window.
            // The maximum element is always at the front of the deque.
            if (i >= k - 1) {
                res[ri++] = nums[q.peek()];
            }
        }
        return res;
    }
}
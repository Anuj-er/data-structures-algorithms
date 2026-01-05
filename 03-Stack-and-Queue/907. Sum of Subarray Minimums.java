// https://leetcode.com/problems/sum-of-subarray-minimums/

import java.util.Stack;

class Solution {

    /**
     * Calculates the span of each element to its left.
     * The left span for arr[i] is the number of elements to its left (including itself)
     * that are greater than or equal to arr[i].
     */
    public int[] getLeftSpan(int[] arr) {
        Stack<int[]> pse = new Stack<>();
        int size = arr.length;
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            // Pop elements that are greater than or equal to the current element.
            while (!pse.isEmpty() && arr[i] <= pse.peek()[0]) {
                pse.pop();
            }
            if (pse.isEmpty()) {
                // If no smaller element is found, the span is the entire range.
                res[i] = i + 1;
            } else {
                res[i] = i - pse.peek()[1];
            }
            pse.push(new int[] { arr[i], i });
        }
        return res;
    }

    /**
     * Calculates the span of each element to its right.
     * The right span for arr[i] is the number of elements to its right (including itself)
     * that are strictly greater than arr[i].
     */
    public int[] getRightSpan(int[] arr) {
        Stack<int[]> nse = new Stack<>();
        int size = arr.length;
        int[] res = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            // Corrected: Use strict inequality to handle duplicates correctly.
            // This ensures each minimum is counted only once.
            while (!nse.isEmpty() && nse.peek()[0] >= arr[i]) {
                nse.pop();
            }
            if (nse.isEmpty()) {
                res[i] = size - i;
            } else {
                res[i] = nse.peek()[1] - i;
            }
            nse.push(new int[] { arr[i], i });
        }
        return res;
    }

    /**
     * Calculates the sum of minimums of all subarrays.
     * The contribution of each element is (element value) * (left span) * (right span).
     * This is calculated with modulo arithmetic to prevent integer overflow.
     */
    public int sumSubarrayMins(int[] arr) {
        long total = 0;
        int left[] = getLeftSpan(arr);
        int right[] = getRightSpan(arr);
        int mod = 1000000007;
        int size = arr.length;

        for (int i = 0; i < size; i++) {
            long contribution = (long) arr[i] * left[i] * right[i];
            // Corrected: Apply modulo after adding to total to avoid potential overflow.
            total = (total + contribution) % mod;
        }
        return (int) total;
    }
}
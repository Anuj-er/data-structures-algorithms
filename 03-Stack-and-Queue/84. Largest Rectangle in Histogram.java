// https://leetcode.com/problems/largest-rectangle-in-histogram/

import java.util.Stack;

class Solution {

    /**
     * Finds the number of consecutive bars to the left (including itself) that are taller than or equal to the current bar.
     * This is the "left span" or "left boundary".
     * It uses a monotonic increasing stack to efficiently find the previous smaller element's index.
     */
    public int[] getLeft(int[] arr) {
        // Stack to store pairs of {height, index}
        Stack<int[]> pse = new Stack<>();
        int n = arr.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            // Pop bars from the stack that are taller than or equal to the current bar.
            // This maintains a stack of increasing heights.
            while (!pse.isEmpty() && arr[i] <= pse.peek()[0]) {
                pse.pop();
            }

            if (pse.isEmpty()) {
                // If stack is empty, there are no smaller bars to the left.
                // The span is the entire left side up to the current bar's index.
                res[i] = i + 1;
            } else {
                // The span is the distance from the current index to the index of the previous smaller bar.
                res[i] = i - pse.peek()[1];
            }
            // Push the current bar's height and index onto the stack.
            pse.push(new int[]{arr[i], i});
        }
        return res;
    }

    /**
     * Finds the number of consecutive bars to the right (including itself) that are taller than the current bar.
     * This is the "right span" or "right boundary".
     * It uses a monotonic increasing stack, but iterates from right to left.
     */
    public int[] getRight(int[] arr) {
        // Stack to store pairs of {height, index}
        Stack<int[]> nse = new Stack<>();
        int n = arr.length;
        int[] res = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            // Pop bars from the stack that are taller than the current bar.
            // This finds the next smaller element to the right.
            while (!nse.isEmpty() && nse.peek()[0] > arr[i]) {
                nse.pop();
            }

            if (nse.isEmpty()) {
                // If stack is empty, there are no smaller bars to the right.
                // The span is the entire right side from the current bar's index.
                res[i] = n - i;
            } else {
                // The span is the distance from the current index to the index of the next smaller bar.
                res[i] = nse.peek()[1] - i;
            }
            // Push the current bar's height and index onto the stack.
            nse.push(new int[]{arr[i], i});
        }
        return res;
    }

    /**
     * Calculates the largest rectangular area in a histogram.
     * It finds the largest possible rectangle for each bar by extending to its left and right boundaries,
     * where the boundaries are the first bars shorter than the current one.
     * The area for each bar is its height multiplied by its total span (left + right - 1).
     */
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        int n = heights.length;
        int left[] = getLeft(heights);
        int right[] = getRight(heights);

        for (int i = 0; i < n; i++) {
            // The width of the rectangle with heights[i] as the minimum height is `left[i] + right[i] - 1`.
            // left[i] counts the current bar and bars to its left.
            // right[i] counts the current bar and bars to its right.
            // We subtract 1 to avoid double-counting the current bar.
            int area = heights[i] * (left[i] + right[i] - 1);

            if (area > max) {
                max = area;
            }
        }
        return max;
    }
}
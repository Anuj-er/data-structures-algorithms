// https://www.geeksforgeeks.org/problems/sort-an-array-of-0s-1s-and-2s4231/

class Solution {
    // Function to sort an array of 0s, 1s, and 2s
    public void sort012(int[] arr) {
        // Initialize counters for 0s, 1s, and 2s
        int zeros = 0;
        int ones = 0;

        // Count the number of 0s, 1s, and 2s in the array
        for (int num : arr) {
            if (num == 0) zeros++;  // Increment zeros counter
            if (num == 1) ones++;   // Increment ones counter
        }

        // Fill the array with the correct number of 0s
        for (int i = 0; i < zeros; i++) {
            arr[i] = 0;  // Set the first part of the array to 0s
        }

        // Fill the next section with 1s
        for (int i = zeros; i < zeros + ones; i++) {
            arr[i] = 1;  // Set the next part of the array to 1s
        }

        // Fill the remaining part with 2s
        for (int i = zeros + ones; i < arr.length; i++) {
            arr[i] = 2;  // Set the remaining part of the array to 2s
        }
    }
}

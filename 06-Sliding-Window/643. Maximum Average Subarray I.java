// https://leetcode.com/problems/maximum-average-subarray-i/

class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int current_sum = 0;
        // Calculate the sum of the first k elements
        // This is the first window of size k 
        for(int i=0;i<k;i++){
            current_sum += nums[i];
        }

        int max_sum = current_sum;
        // Now we will slide the window of size k
        for(int i=k;i<nums.length;i++){
            current_sum = current_sum + nums[i] - nums[i-k];
            if(max_sum<current_sum){
                max_sum = current_sum;
            }
        }
        return (double)max_sum/k;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)
// The above code uses a sliding window approach to find the maximum average of a subarray of size k.
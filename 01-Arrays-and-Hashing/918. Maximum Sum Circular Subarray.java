// https://leetcode.com/problems/maximum-sum-circular-subarray/description/

class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        int currMax = 0, maxSum = Integer.MIN_VALUE;
        int currMin = 0, minSum = Integer.MAX_VALUE;
        
        for(int num: nums){
            currMax = Math.max(num,currMax+num);
            maxSum = Math.max(currMax,maxSum);

            currMin = Math.min(num,currMin+num);
            minSum = Math.min(minSum,currMin);

            total += num;
        }
        if(maxSum < 0) return maxSum;
        return Math.max(maxSum,total-minSum);
    }
}
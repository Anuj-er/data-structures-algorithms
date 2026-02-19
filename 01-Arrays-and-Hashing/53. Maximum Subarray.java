// https://leetcode.com/problems/maximum-subarray/description/

class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = -100000;
        int currentSum = 0;
        for(int i=0;i<nums.length;i++){
            currentSum += nums[i];
            if(currentSum>maxSum){
                maxSum = currentSum;
            }
            if(currentSum<0){
                currentSum = 0;
            }
        }
        return maxSum;
    }
}
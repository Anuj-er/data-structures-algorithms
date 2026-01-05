// https://leetcode.com/problems/minimum-size-subarray-sum/

class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int res = 99999999;
        int sum = 0;
        for(int right=0;right<nums.length;right++){
            sum += nums[right];
            while(sum >= target){
                res = Math.min(res,right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        if(res == 99999999){
            return 0;
        }else{
            return res;
        }
    }
}
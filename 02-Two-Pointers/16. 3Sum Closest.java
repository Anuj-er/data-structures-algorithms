// https://leetcode.com/problems/3sum-closest/

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int closestSum = Integer.MAX_VALUE;
        for(int k=0;k<len-2;k++){
            int i = k + 1;
            int j = len - 1;

            while(i<j){
                int sum = nums[k] + nums[i] + nums[j];
                if(Math.abs(target-sum)<Math.abs(target-closestSum)) 
                    closestSum = sum;

                if(sum<target) i++;
                else j--;
            }
        }
        return closestSum;
    }
}
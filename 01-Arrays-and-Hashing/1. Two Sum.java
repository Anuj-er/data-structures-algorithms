// https://leetcode.com/problems/two-sum/

import java.util.HashMap;
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            Integer complementIndex = map.get(nums[i]);
            if(complementIndex !=null){
                return new int[]{complementIndex,i};
            }
            map.put(target-nums[i],i);
        }
        return nums;
    }
}
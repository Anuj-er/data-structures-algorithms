// https://leetcode.com/problems/contains-duplicate/

import java.util.HashMap;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer,Integer> numsMap= new HashMap<>();
        for(int num:nums){
            if(numsMap.containsKey(num)){
                return true;
            }else{
                numsMap.put(num,1);
            }
        }
        return false;
    }
}
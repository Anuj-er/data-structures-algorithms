// https://leetcode.com/problems/majority-element/

import java.util.HashMap;
import java.util.Map;

// Using HashMap with O(n) time complexity and O(n) space complexity
class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num: nums){
            if(map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else{
                map.put(num,1);
            }
        }
        int high = 0;
        int key = 0;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(entry.getValue()>high){
                high = entry.getValue();
                key = entry.getKey();
            }
        }
        return key;
        
    }
}

// Using Boyer-Moore Voting Algorithm with O(1) space complexity
class Solution2 {
    public int majorityElement(int[] nums) {
        int count = 1;
        int res = nums[0];

        for(int i = 1;i<nums.length;i++){
                if(nums[i]==res){
                    count++;
                }else{
                    if(count==0){
                        res = nums[i];
                        count = 1;
                    }else{
                        count--;
                    }
                }
        }
        return res;
    }
}
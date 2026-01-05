// https://leetcode.com/problems/majority-element-ii/

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

// Using HashMap with O(n) time complexity and O(n) space complexity
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num: nums){
            if(map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else{
                map.put(num,1);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        int threshHold = nums.length/3;

        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
            if(entry.getValue()>threshHold){
                res.add(entry.getKey());
            }
        }
        return res;
    }
}

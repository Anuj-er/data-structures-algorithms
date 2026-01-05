// https://leetcode.com/problems/longest-consecutive-sequence/

import java.util.HashSet;
import java.util.Set;
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> number = new HashSet<>();
        for(int num:nums){
            number.add(num);
        }
        int longestStreak = 0;
        for(int num: number){ 
            if(!number.contains(num-1)){
                int currentNum = num;
                int currentStreak = 1;
                while(number.contains(currentNum+1)){
                    currentStreak++;
                    currentNum++;
                }
                if(currentStreak>longestStreak){
                    longestStreak = currentStreak;
                }
            }     
        }
        return longestStreak;
    }
}
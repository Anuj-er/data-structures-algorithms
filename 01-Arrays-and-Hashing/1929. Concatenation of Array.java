// https://leetcode.com/problems/concatenation-of-array/description/

class Solution {
    public int[] getConcatenation(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len*2];
        int i = 0;
        for(int num:nums){
            ans[i++]=num;
        }
        for(int num:nums){
            ans[i++]=num;
        }
        return ans;
    }
}
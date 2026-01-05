// https://leetcode.com/problems/remove-duplicates-from-sorted-array/

class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;

        int k = 1;
        for(int i = 1; i<nums.length; i++){
            if(nums[i]!=nums[i-1]){
                nums[k++] = nums[i];
            }
        }
        return k;
    }
}
// Time Complexity: O(n)
// Space Complexity: O(1)
// Approach 
// 1. Two Pointers
// 2. Initialize a variable k to 1, which will keep track of the number of unique elements.

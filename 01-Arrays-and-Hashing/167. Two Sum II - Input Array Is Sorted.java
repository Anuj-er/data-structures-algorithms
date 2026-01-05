// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while(left<right){
            int sum = numbers[left] + numbers[right];
            // if sum is greater than rem than right --
            // else left ++ ;
            if(sum>target){
                right--;
            }else if(sum<target){
                left++;
            }else{
                return new int[]{left+1,right+1}; // 1 based indexing
            }

        }
        return new int[]{-1,-1};
    }
}
// Time Complexity: O(n)
// Space Complexity: O(1)
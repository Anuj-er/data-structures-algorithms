// https://leetcode.com/problems/squares-of-a-sorted-array/

class Solution {
    public int[] sortedSquares(int[] nums) {
        int l = 0;
        int r = nums.length-1;
        int[] res = new int[r+1];
        int pos = r;
        while(l<=r){
            if(Math.abs(nums[l])>Math.abs(nums[r])){
                res[pos] = nums[l] * nums[l];
                l++; 
            }else{
                res[pos] = nums[r] * nums[r];
                r--;
            }
            pos--;
        }
        return res;
    }
}

// class Solution {
//     public int[] sortedSquares(int[] nums) {
//         int l = 0;
//         int r = nums.length-1;
//         while(l<=r){
//             if(l!=r){
//                 nums[l] *= nums[l];
//                 nums[r] *= nums[r];
//             }else{
//                 nums[r] *= nums[r];
//             }
//             l++;
//             r--;
//         }
//         Arrays.sort(nums);
//         return nums;
//     }
// }
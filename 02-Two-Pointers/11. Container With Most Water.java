// https://leetcode.com/problems/container-with-most-water/

class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int i = 0 , j = height.length - 1;
        while(i<j){
            int left_height = height[i];
            int right_height = height[j];
            int min_h = Math.min(left_height,right_height);
            max = Math.max(max,min_h*(j-i));
            if(left_height<right_height){
                i++;
            }else{
                j--;
            }
        }
        return max;
    }
}
// Time Complexity: O(n)
// Space Complexity: O(1)
// https://leetcode.com/problems/house-robber-ii/description/

class Solution {
    public int rob1(int[] nums) {
        int size = nums.length;
        if(size == 0) return 0;
        if(size == 1) return nums[0];
        int prev2 = 0;
        int prev1 = nums[0];

        for(int i = 2;i<=size;i++){
            int pick = nums[i-1] + prev2;
            int noPick = prev1;
            int ans = Math.max(pick,noPick);
            prev2 = prev1;
            prev1 = ans;
        }
        return prev1;
    }
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        if(n == 1) return nums[0];
        int[] num1 = new int[n-1];
        int[] num2 = new int[n-1];
        int j = 0, k = 0;
        for(int i = 0;i<n;i++){
            if(i != 0){
                num1[j++] = nums[i];
            }
            if(i != n-1){
                num2[k++] = nums[i];
            }
        }
        return Math.max(rob1(num1),rob1(num2));
    }
}
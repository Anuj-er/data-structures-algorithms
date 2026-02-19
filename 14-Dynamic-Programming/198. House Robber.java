// https://leetcode.com/problems/house-robber/description/

class Solution {
    public int rob(int[] nums) {
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
}

// Bottom-Up Approach
// class Solution {
//     public int rob(int[] nums) {
//         int size = nums.length;
//         if(size == 0) return 0;
//         if(size == 1) return nums[0];
//         int[] dp = new int[size+1];
//         dp[0] = 0;
//         dp[1] = nums[0];
//         for(int i = 2;i<=size;i++){
//             int pick = nums[i-1] + dp[i-2];
//             int noPick = dp[i-1];
//             dp[i] = Math.max(pick,noPick);
//         }
//         return dp[size];
//     }
// }

// Top-Down Approach
// class Solution {
//     public int rob(int[] nums) {
//         int size = nums.length;
//         if(size == 0) return 0;
//         int[] dp = new int[size];
//         Arrays.fill(dp,-1);
//         recur(size-1,nums,dp);
//         return dp[size-1];
//     }
//     public int recur(int n,int[] nums,int[] dp){
//         if(n<0){
//             return 0;
//         }
//         if(dp[n] != -1) return dp[n];
//         int pick = nums[n] + recur(n-2,nums,dp);
//         int noPick = 0 + recur(n-1,nums,dp);
//         dp[n] = Math.max(pick,noPick);
//         return dp[n];
//     }
// }


// Gadha Logic
// class Solution {
//     public int rob(int[] nums) {
//         int ans1 = 0;
//         int ans2 = 0;
//         int size = nums.length;
//         int i = 0;
//         while(i<size){
//             ans1 = ans1 + nums[i];
//             i = i+2;
//         }
//         i = 1;
//         while(i<size){
//             ans2 = ans2 + nums[i];
//             i = i+2;
//         }
//         return Math.max(ans1,ans2);
//     }
// }
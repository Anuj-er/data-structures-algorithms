// https://leetcode.com/problems/climbing-stairs/description/

// Bottom-Up optimized
class Solution {
    public int climbStairs(int n) {
        if(n<=2) return n;
        int prev2 = 1;
        int prev1 = 2;
        int ans = 0;
        for(int state = 2;state<n;state++){
            ans = prev1 + prev2;
            prev2 = prev1;
            prev1 = ans;
        }
        return ans;
    }
} 

// // Bottom-Up Approach DP t.c = O(n) s.c O(n)
// class Solution {
//     public int climbStairs(int n) {
//         if(n<=2) return n;
//         int[] dp = new int[n];
//         dp[0] = 1;
//         dp[1] = 2;
//         for(int state = 2;state<n;state++){
//             dp[state] = dp[state-1] + dp[state-2];
//         }
//         return dp[n-1];
//     }
// } 

// Top-Down Approach
// class Solution {
//     public int climbStairs(int n) {
//         int[] dp = new int[n+1];
//         Arrays.fill(dp,-1);
//         return recur(n,dp);
//     }
//     public int recur(int n,int[] dp){
//         if(n<=2){
//             dp[n] = n;
//             return n;
//         }
//         if(dp[n]!=-1) return dp[n];
//         dp[n] = dp[n-1] + dp[n-2];
//         return dp[n];
//     }
// } 
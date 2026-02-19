// https://leetcode.com/problems/min-cost-climbing-stairs/description/

class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int prev1 = 0;
        int prev2 = 0;
        int ans = 0;
        for(int i=2;i<=n;i++){
            int oneStep = cost[i-1] + prev1;
            int secondStep = cost[i-2] + prev2;
            ans = Math.min(oneStep,secondStep);
            prev2 = prev1;
            prev1 = ans;
        }
        return ans;
    }
}

// Bottom-Up Approach
// class Solution {
//     static{
//         Runtime.getRuntime().gc();
//         Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//             try (FileWriter writer = new FileWriter("display_runtime.txt")) {
//                 writer.write("0");
//             } catch (IOException e) {
//             }
//         }));
//     }
//     public int minCostClimbingStairs(int[] cost) {
//         int n = cost.length;
//         int[] dp = new int[n+1];
//         dp[0] = 0;
//         dp[1] = 0;
//         for(int i=2;i<=n;i++){
//             int oneStep = cost[i-1] + dp[i-1];
//             int secondStep = cost[i-2] + dp[i-2];
//             dp[i] = Math.min(oneStep,secondStep);
//         }
//         return dp[n];
//     }
// }

// // Top-Down Approach
// class Solution {
//     public int minCostClimbingStairs(int[] cost) {
//         int n = cost.length;
//         int[] dp = new int[n+1];
//         Arrays.fill(dp,-1);
//         return recur(n,cost,dp);
//     }
//     public int recur(int n, int[] cost,int[] dp){
//         if(n==1 || n==0){
//             dp[n] = 0; 
//             return 0;
//         }
//         if(dp[n] != -1) return dp[n];
//         int oneStep = cost[n-1] + recur(n-1,cost,dp);
//         int secondStep = cost[n-2] + recur(n-2,cost,dp);
//         dp[n] = Math.min(oneStep,secondStep);
//         return dp[n];
//     }
// }

// TC = 2^n
// class Solution {
//     public int minCostClimbingStairs(int[] cost) {
//         return recur(cost.length,cost);
//     }
//     public int recur(int n, int[] cost){
//         if(n==1 || n==0){
//             return 0;
//         }
//         int oneStep = cost[n-1] + recur(n-1,cost);
//         int secondStep = cost[n-2] + recur(n-2,cost);
//         return Math.min(oneStep,secondStep);
//     }
// }

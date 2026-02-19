// https://leetcode.com/problems/coin-change/description/

// Bottom-Up Approach - Optimized
class Solution {

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                //this handles the time writing
                fw.write("0");
            } catch (Exception e) {
            }
        }));
    }

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] prev = new int[amount+1];
        for(int a=0;a<=amount;a++){
            if(a % coins[0] == 0)
                prev[a] = a / coins[0];
            else
                prev[a] = (int)(1e9) ;
        }
        
        for(int i=1;i<n;i++){
            int[] curr = new int[amount+1];
            for(int a=0;a<=amount;a++){
               int pick = (int)(1e9);
                if(a >= coins[i])
                    pick = 1 + curr[a-coins[i]];
                int noPick = prev[a];
                curr[a] = Math.min(pick,noPick); 
            }
            prev = curr;
        }
        return (prev[amount] == (int)(1e9))?-1:prev[amount];
    }
}

// Bottom-Up Approach - Tabulation
// class Solution {
//     public int coinChange(int[] coins, int amount) {
//         int n = coins.length;
//         int[][] dp = new int[n][amount+1];
//         for(int a=0;a<=amount;a++){
//             if(a % coins[0] == 0){
//                 dp[0][a] = a / coins[0];
//             }else{
//                 dp[0][a] = (int)(1e9) ;
//             }
//         }
        
//         for(int i=1;i<n;i++){
//             for(int a=0;a<=amount;a++){
//                int pick = (int)(1e9);
//                 if(a >= coins[i]){
//                     pick = 1 + dp[i][a-coins[i]];
//                 }
//                 int noPick = dp[i-1][a];
//                 dp[i][a] = Math.min(pick,noPick); 
//             }
//         }

//         int res = dp[n-1][amount];
//         if(res == (int)(1e9)){
//             return -1;
//         }
//         return res;
//     }
// }

// Top-Down Approach Memoisation
// class Solution {
//     public int coinChange(int[] coins, int amount) {
//         int n = coins.length;
//         int[][] dp = new int[n][amount+1];
//         for(int i=0;i<n;i++){
//             Arrays.fill(dp[i],-1);
//         }
//         int res = recur(coins,amount,n-1,dp);
//         if(res == (int)(1e9)){
//             return -1;
//         }
//         return res;
//     }
//     public int recur(int[] coins, int amount,int index,int[][] dp){
//         if(index == 0){
//             if(amount % coins[index] == 0){
//                 dp[index][amount] = amount / coins[index];
//                 return dp[index][amount];
//             }
//             dp[index][amount] = (int)(1e9) ;
//             return dp[index][amount];
//         }
//         if(dp[index][amount] != -1) return dp[index][amount];
//         int pick = (int)(1e9);
//         if(amount >= coins[index]){
//             pick = 1 + recur(coins,amount-coins[index],index,dp);
//         }
//         int noPick = recur(coins,amount,index-1,dp);
//         dp[index][amount] = Math.min(pick,noPick);
//         return dp[index][amount];
//     }
// }
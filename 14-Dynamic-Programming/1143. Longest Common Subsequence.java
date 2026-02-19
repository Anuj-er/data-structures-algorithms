// https://leetcode.com/problems/longest-common-subsequence/description/

class Solution {
    public int longestCommonSubsequence(String t1, String t2) {
        int n = t1.length();
        int m = t2.length();
        int[][] dp = new int[n+1][m+1];
        dp[0][0] = 0;

        for(int i = 1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(t1.charAt(i-1) == t2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];//recur(i-1,j-1,t1,t2,dp);
                }else{
                    int case1 = dp[i-1][j]; //recur(i-1,j,t1,t2,dp);
                    int case2 = dp[i][j-1]; //recur(i,j-1,t1,t2,dp);
                    dp[i][j] = Math.max(case1,case2);
                }
            }
        }
        printLCS(dp,t1,t2);
        return dp[n][m];
    }
    public void print(int[][] dp){
        int n = dp.length;
        int m = dp[0].length;
        for(int i = 0;i<n;i++){
            for(int j=0;j<m;j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void printLCS(int[][] dp, String t1, String t2){
        int i = t1.length();
        int j = t2.length();
        StringBuilder sb = new StringBuilder();

        while(i > 0 && j > 0){
            if(t1.charAt(i-1) == t2.charAt(j-1)){
                sb.append(t1.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i-1][j] > dp[i][j-1]){
                i--;
            } else {
                j--;
            }
        }

        System.out.println(sb.reverse().toString());
    }
}

// // Bottom Up Space optimized
// class Solution {
//     static{
//         Runtime.getRuntime().addShutdownHook(new Thread(()->{
//             try(FileWriter fw = new FileWriter("display_runtime.txt")){
//                 fw.write("0");
//             }
//             catch(IOException e){
//               e.printStackTrace();
//             }
//         }));
//     }

//     public int longestCommonSubsequence(String t1, String t2) {
//         int n = t1.length();
//         int m = t2.length();
//         int[] prev = new int[m+1];
//         prev[0] = 0;

//         for(int i = 1;i<=n;i++){
//             int[] curr = new int[m+1];
//             for(int j=1;j<=m;j++){
//                 if(t1.charAt(i-1) == t2.charAt(j-1)){
//                     curr[j] = 1 + prev[j-1];//recur(i-1,j-1,t1,t2,dp);
//                 }else{
//                     int case1 = prev[j]; //recur(i-1,j,t1,t2,dp);
//                     int case2 = curr[j-1]; //recur(i,j-1,t1,t2,dp);
//                     curr[j] = Math.max(case1,case2);
//                 }
//             }
//             prev = curr;
//         }
//         return prev[m];
//     }
// }

// Bottom Up
// class Solution {
//     public int longestCommonSubsequence(String t1, String t2) {
//         int n = t1.length();
//         int m = t2.length();
//         int[][] dp = new int[n+1][m+1];
//         dp[0][0] = 0;

//         for(int i = 1;i<=n;i++){
//             for(int j=1;j<=m;j++){
//                 if(t1.charAt(i-1) == t2.charAt(j-1)){
//                     dp[i][j] = 1 + dp[i-1][j-1];//recur(i-1,j-1,t1,t2,dp);
//                 }else{
//                     int case1 = dp[i-1][j]; //recur(i-1,j,t1,t2,dp);
//                     int case2 = dp[i][j-1]; //recur(i,j-1,t1,t2,dp);
//                     dp[i][j] = Math.max(case1,case2);
//                 }
//             }
//         }
//         return dp[n][m];
//     }
// }

// Top-Down With increment
// class Solution {
//     public int longestCommonSubsequence(String text1, String text2) {
//         int n = text1.length();
//         int m = text2.length();
//         int[][] dp = new int[n+1][m+1];
//         for(int i = 0;i<=n;i++){
//             Arrays.fill(dp[i],-1);
//         }
//         return recur(n,m,text1,text2,dp);
//     }
//     public int recur(int i, int j,String t1, String t2,int[][] dp){
//         if(i==0 || j==0){
//             dp[i][j] = 0;
//             return 0;
//         }
//         if(dp[i][j] != -1) return dp[i][j];
//         if(t1.charAt(i-1) == t2.charAt(j-1)){
//             dp[i][j] = 1 + recur(i-1,j-1,t1,t2,dp);
//             return dp[i][j];
//         }else{
//             int case1 = recur(i-1,j,t1,t2,dp);
//             int case2 = recur(i,j-1,t1,t2,dp);
//             dp[i][j] = Math.max(case1,case2);
//             return dp[i][j];
//         }
//     }
// }


// Recursion Top Down without Incrementing
// class Solution {
//     public int longestCommonSubsequence(String text1, String text2) {
//         int n = text1.length();
//         int m = text2.length();
//         int[][] dp = new int[n][m];
//         for(int i = 0;i<n;i++){
//             Arrays.fill(dp[i],-1);
//         }
//         return recur(n-1,m-1,text1,text2,dp);
//     }
//     public int recur(int i, int j,String t1, String t2,int[][] dp){
//         if(i<0 || j<0){
//             return 0;
//         }
//         if(dp[i][j] != -1) return dp[i][j];
//         if(t1.charAt(i) == t2.charAt(j)){
//             dp[i][j] = 1 + recur(i-1,j-1,t1,t2,dp);
//             return dp[i][j];
//         }else{
//             int case1 = recur(i-1,j,t1,t2,dp);
//             int case2 = recur(i,j-1,t1,t2,dp);
//             dp[i][j] = Math.max(case1,case2);
//             return dp[i][j];
//         }
//     }
// }
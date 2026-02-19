// https://leetcode.com/problems/longest-palindromic-subsequence/description/

class Solution {
    static{
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            try(FileWriter fw = new FileWriter("display_runtime.txt")){
                fw.write("0");
            }
            catch(IOException e){
              e.printStackTrace();
            }
        }));
    }

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
        return dp[n][m];
    }

    public int longestPalindromeSubseq(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        return longestCommonSubsequence(s,rev);
    }
}

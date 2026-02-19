// https://leetcode.com/problems/decode-ways/description/

class Solution {
    public int numDecodings(String s) {
        int[] dp = new int[s.length()+1];
        return recur(s,0,dp);

    }
    public int recur(String s,int i,int[] dp){
        int n = s.length();

        if(i == n){
            dp[i] = 1;
            return 1;
        }
        if(s.charAt(i) == '0'){
            dp[i] = 0;
            return 0;
        }

        if(dp[i] != 0) return dp[i];

        int ways = 0;

        // 1 Step
        ways+=recur(s,i+1,dp);

        // 2 Step
        if(i+1<n){
            int num = (s.charAt(i) - '0') * 10 + (s.charAt(i+1) - '0');
            if(num >= 10 && num <= 26){
                ways+=recur(s,i+2,dp);
            }
        }
        dp[i] = ways;
        return dp[i];
    }

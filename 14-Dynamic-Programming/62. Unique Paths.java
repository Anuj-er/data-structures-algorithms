// https://leetcode.com/problems/unique-paths/description/

// Bottom-Up Approach
class Solution {
    public int uniquePaths(int m, int n) {
        int[] prev = new int[n];

        for(int j = 0; j<n ;j++) prev[j] = 1;

        for(int i = 1; i<m;i++){
            int[] curr = new int[n];
            curr[0] = 1;
            for(int j=1;j<n;j++){
                curr[j] = prev[j] + curr[j-1];
            }
            prev = curr;
        }
        return prev[n-1];
    }
}

// Bottom-Up Approach
// class Solution {
//     int[][] dp;
//     public int uniquePaths(int m, int n) {
//         dp = new int[m][n];

//         for(int i = 0; i<m ;i++) dp[i][0] = 1;
//         for(int j = 0; j<n ;j++) dp[0][j] = 1;

//         for(int i = 1; i<m;i++){
//             for(int j=1;j<n;j++){
//                 dp[i][j] = dp[i-1][j] + dp[i][j-1];
//             }
//         }
//         return dp[m-1][n-1];
//     }

// }

// Top-Down Approch 
// class Solution {
//     int[][] dp;
//     public int uniquePaths(int m, int n) {
//         dp = new int[m][n];
//         return recur(0,0,m,n);
//     }
//     public int recur(int x, int y, int m,int n){
//         if(x == m-1 && y == n-1){
//             return 1;
//         }

//         if(x >= m || y >= n) return 0;

//         if(dp[x][y] != 0) return dp[x][y];

//         int right = recur(x,y+1,m,n);
//         int down = recur(x+1,y,m,n);

//         dp[x][y] = right+down;
//         return dp[x][y];
//     }
// }
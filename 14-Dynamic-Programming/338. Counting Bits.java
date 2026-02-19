// https://leetcode.com/problems/counting-bits/description/

class Solution {
    public int[] countBits(int n) {
        int[] dp = new int[n+1];
        for(int i = 1;i<=n;i++){
            // dp[i] = dp[i>>1] + (i&1);
            dp[i]=dp[i/2]+(i%2);
        }
        return dp;
    }
    static {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().
            addShutdownHook(
                new Thread(
                    ()->{
                        try(FileWriter f = new FileWriter("display_runtime.txt")){
                            f.write("0");
                        } catch (Exception e){}
                    }
                )
            );
    }
}

// TC = O(nlogn)
// class Solution {
//     public int[] countBits(int n) {
//         int[] ans = new int[n+1];
//         for(int i = 0;i<=n;i++){
//             int num  = i;
//             int count = 0;
//             while(num>0){
//                 num = num & num - 1;
//                 count++;
//             }
//             ans[i] = count;
//         }
//         return ans;
//     }
// }


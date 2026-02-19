// https://leetcode.com/problems/partition-equal-subset-sum/description/

// Bottom-Up Approach
class Solution {                                                                           
    public boolean canPartition(int[] nums) {
        int total = 0;
        for(int num:nums) total += num;
        int n = nums.length;
        int target = total / 2;
        int[][] dp = new int[n][target+1];
        if (total % 2 != 0) return false;

        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],-1);
        }
        return recur(nums,n-1,target,dp) == 1;
    }
    public int recur(int[] nums,int index,int target,int[][] dp){
        if(target == 0) return 1;
        if(index==0){
            return (nums[index] == target)?1:0;
        }
        if(dp[index][target]!= -1) return dp[index][target];

        int pick = 0;
        if(target >= nums[index]){
            pick = recur(nums,index-1,target-nums[index],dp);
        }
        int noPick = recur(nums,index-1,target,dp);
        dp[index][target] = (pick == 1 || noPick == 1)?1:0;
        return dp[index][target];
    }
}

// Top-Down Approach 
// class Solution {

//     static {
//         Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//             try (FileWriter writer = new FileWriter("display_runtime.txt")) {
//                 writer.write("0");
//                 } catch (IOException e) {
//                 }
//             }));
//                                                                                                 }
//     public boolean canPartition(int[] nums) {
//         int total = 0;
//         for(int num:nums) total += num;
//         int n = nums.length;
//         int target = total / 2;
//         int[][] dp = new int[n][target+1];
//         if (total % 2 != 0) return false;

//         for(int i=0;i<n;i++){
//             Arrays.fill(dp[i],-1);
//         }

//         return recur(nums,n-1,target,dp) == 1;

//     }
//     public int recur(int[] nums,int index,int target,int[][] dp){
//         if(target == 0) return 1;
//         if(index==0){
//             return (nums[index] == target)?1:0;
//         }
//         if(dp[index][target]!= -1) return dp[index][target];

//         int pick = 0;
//         if(target >= nums[index]){
//             pick = recur(nums,index-1,target-nums[index],dp);
//         }
//         int noPick = recur(nums,index-1,target,dp);
//         dp[index][target] = (pick == 1 || noPick == 1)?1:0;
//         return dp[index][target];
//     }
// }
// https://leetcode.com/problems/longest-increasing-subsequence/description/

class Solution {

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int lis[] = new int[n];
        int p[] = new int[n];
        int maxLen = 1;
        int maxInd = 0;
        for(int i=0;i<n;i++){
            lis[i] = 1;
            p[i] = i;
        }

        for(int i=1;i<n;i++){
            for(int prev = 0;prev<i;prev++){
                if(nums[prev] < nums[i]){
                    if(lis[i] < 1 + lis[prev]){
                        lis[i] = 1 + lis[prev];
                        p[i] = prev;
                    }
                }
            }
            if(lis[i] > maxLen){
                maxLen = lis[i];
                maxInd = i;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(nums[maxInd]);
        while(maxInd != p[maxInd]){
            maxInd = p[maxInd];
            list.add(0, nums[maxInd]);
        }
        for(int i = 0;i<list.size();i++){
            System.out.print(list.get(i) + " ");
        }
        return maxLen;
    }
}
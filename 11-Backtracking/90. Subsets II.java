// https://leetcode.com/problems/subsets-ii/

class Solution {
    List<List<Integer>> ans;

    public void backTrack(int idx, List<Integer> path,int[] nums){
        ans.add(new ArrayList<>(path));
        
        for(int i=idx;i<nums.length;i++){
            if(i>idx && nums[i] == nums[i-1]) continue;
            path.add(nums[i]);
            backTrack(i+1,path,nums);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
       ans = new ArrayList<>();
       Arrays.sort(nums);
       backTrack(0,new ArrayList<>(),nums);
       return ans; 
    }
}
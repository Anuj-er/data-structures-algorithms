// https://leetcode.com/problems/subsets/description/

class Solution {
    List<List<Integer>> ans;
    public void backtrack(int idx, List<Integer> path, int[] nums){
        ans.add(new ArrayList<>(path));
        for(int i = idx; i<nums.length;i++){
            path.add(nums[i]);
            backtrack(i+1,path,nums);
            path.remove(path.size()-1);
        }
    }
    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        backtrack(0,new ArrayList<>(),nums);
        return ans;
    }
}
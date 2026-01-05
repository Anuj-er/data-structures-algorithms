// https://leetcode.com/problems/combination-sum-ii/description/

class Solution {
    List<List<Integer>> ans;
    int size;
    public void backTrack(int idx,List<Integer> path,int[] candidates,int target){
        if(target == 0){
            ans.add(new ArrayList<>(path));
            return;
        }
        if(target < 0) return;
        for(int i=idx;i<size;i++){
            if(candidates[i] > target) return;
            if(i>idx && candidates[i] == candidates[i-1]) continue;
            path.add(candidates[i]);
            backTrack(i+1,path,candidates,target-candidates[i]);
            path.remove(path.size()-1);
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        size = candidates.length;
        Arrays.sort(candidates); 
        ans = new ArrayList<>();
        backTrack(0,new ArrayList<>(),candidates,target);
        return ans;
    }
}
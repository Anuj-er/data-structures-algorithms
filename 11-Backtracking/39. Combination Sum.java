// https://leetcode.com/problems/combination-sum/description/

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
            path.add(candidates[i]);
            backTrack(i,path,candidates,target-candidates[i]);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        size = candidates.length;
        ans = new ArrayList<>();
        backTrack(0,new ArrayList<>(),candidates,target);
        return ans;
    }
}
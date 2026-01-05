// https://leetcode.cn/problems/permutations-ii/description/

class Solution {
    List<List<Integer>> ans;
    boolean[] used;

    public void backTrack(List<Integer> path,int[] nums,int size){
        if(path.size() == size){
            ans.add(new ArrayList<>(path));
            return;
        }
        for(int i=0;i<size;i++){
            if(i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            if(used[i]) continue;
            used[i] = true;
            path.add(nums[i]);
            backTrack(path,nums,size);
            path.remove(path.size()-1);
            used[i] = false;
        }
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        int size = nums.length;
        ans = new ArrayList<>();
        used = new boolean[size];
        backTrack(new ArrayList<>(),nums,size);
        return ans;
    }
}
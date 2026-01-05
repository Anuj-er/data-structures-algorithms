// https://leetcode.com/problems/combination-sum-iii/description/

class Solution {

    List<List<Integer>> ans;
    int size;

    public void backTrack(int idx, List<Integer> path, int[] nums, int target, int k) {
        if (target == 0 && path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        if (target < 0)
            return;
        for (int i = idx; i < size; i++) {
            if (nums[i] > target)
                return;
            if (i > idx && nums[i] == nums[i - 1])
                continue;
            path.add(nums[i]);
            backTrack(i + 1, path, nums, target - nums[i],k);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        int[] nums = {1,2,3,4,5,6,7,8,9};
        size = nums.length;
        ans = new ArrayList<>();
        backTrack(0,new ArrayList<>(),nums,n,k);
        return ans;
    }
}

// https://leetcode.com/problems/merge-adjacent-equal-elements/description/

class Solution {
    public List<Long> mergeAdjacent(int[] nums) {
        List<Long> res = new ArrayList<>();
        for (int x : nums) {
            long cur = x;
            while (!res.isEmpty() && res.get(res.size() - 1) == cur) {
                cur += res.remove(res.size() - 1);
            }
            res.add(cur);
        }
        return res;
    }
}
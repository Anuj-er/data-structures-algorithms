// https://leetcode.com/problems/3sum/

class Solution {

    private List<List<Integer>> result = new ArrayList<>();

    public void twoSum(int[] nums,int target,int start,int end){
        while(start<end){
            if(nums[start]+nums[end]>target) end--;
            else if(nums[start]+nums[end]<target) start++;
            else{
                while(start<end && nums[start] == nums[start+1]) start++;
                while(start<end && nums[end] == nums[end-1]) end--;
                result.add(Arrays.asList(-target,nums[start],nums[end]));
                start++;
                end--;
            }
            
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        result.clear();

        if(len<3){
            return new ArrayList<>();
        }

        for(int i=0;i<len;i++){
            if(i!=0 && nums[i-1]==nums[i]){
                continue;
            }
            int n1 = nums[i];
            int target = -n1;

            twoSum(nums,target,i+1,len-1);
        }
        return result;
    }
}
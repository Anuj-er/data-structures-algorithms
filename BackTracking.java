import java.util.*;

class BackTracking {
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

    public void printList(List<List<Integer>> lists){
        System.out.print("[");
        for(int i=0;i<lists.size();i++){
            System.out.print(lists.get(i));
            if(i < lists.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        BackTracking bt = new BackTracking();
        List<List<Integer>> res = bt.permuteUnique(nums);
        bt.printList(res);
    }
}

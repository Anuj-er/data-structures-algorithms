// https://leetcode.com/problems/sort-an-array/

class Solution {
    public static void mergeSort(int[] nums){
        int len = nums.length;
        if(len==0 || len==1){
            return;
        }

        int middleIndex = len/2;
        int[] firstHalf = new int[middleIndex];
        int[] secondHalf = new int[len-middleIndex];

        for(int i=0;i<middleIndex;i++){
            firstHalf[i] = nums[i];
        }
        for(int i=middleIndex;i<len;i++){
            secondHalf[i-middleIndex] = nums[i];
        }

        mergeSort(firstHalf);
        mergeSort(secondHalf);

        merge(nums,firstHalf,secondHalf);
    }
    public static void merge(int[] nums , int[] firstHalf , int[] secondHalf){
        int len1 = firstHalf.length;
        int len2 = secondHalf.length;
        int i=0,j=0,k=0;
        while(i<len1 && j<len2){
            if(firstHalf[i]<secondHalf[j]){
                nums[k] = firstHalf[i];
                i++;
            }else{
                nums[k] = secondHalf[j];
                j++;
            }
            k++;
        }
        while(i<len1){
            nums[k] = firstHalf[i];
            i++;
            k++;
        }
        while(j<len2){
            nums[k] = secondHalf[j];
            j++;
            k++;
        }
    }
    public int[] sortArray(int[] nums) {
        mergeSort(nums);
        return nums;
    }
}
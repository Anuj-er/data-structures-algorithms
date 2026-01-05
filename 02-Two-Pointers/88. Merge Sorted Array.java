// https://leetcode.com/problems/merge-sorted-array/

class Solution1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len1 = nums1.length;
        int[] res = new int[len1];
        int i = 0 , j =  0 , k =0; 
        while(i<m&&j<n){
            if(nums1[i]<nums2[j]){
                res[k] = nums1[i];
                i++;
            }else{
                res[k] = nums2[j];
                j++;
            }
        k++;
        }
        while(i<m){
            res[k++] = nums1[i++];
        }
        while(j<n){
            res[k++] = nums2[j++];
        }
        for(int u = 0; u<len1;u++){
            nums1[u] = res[u];
        }
    }
}
// Time Complexity: O(m+n)
// Space Complexity: O(m+n)
// The above code uses extra space to store the merged array.


class Solution2 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int k = nums1.length -1;
        while(j>=0){
            if(i>=0 && nums1[i]>nums2[j]){
                nums1[k--] = nums1[i--];
            }else{
                nums1[k--] = nums2[j--];
            }
        }
    }
}
// Time Complexity: O(m+n)
// Space Complexity: O(1)
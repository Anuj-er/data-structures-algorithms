// https://leetcode.com/problems/next-greater-element-i/

import java.util.*;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;

        // Storing each with its index
        HashMap<Integer,Integer> store = new HashMap<>();
        for(int i=0;i<size2;i++){
            store.put(nums2[i],i);
        }

        int[] res = new int[size2];
        Stack<Integer> st = new Stack<>();
        for(int i=size2-1;i>=0;i--){
            while(!st.isEmpty() && nums2[i]>=st.peek()){
                st.pop();
            }
            if(st.isEmpty()){
                res[i]=-1;
            }else{
                res[i]=st.peek();
            }
            st.push(nums2[i]);
        }
        int[] finRes = new int[size1];
        for(int i=0;i<size1;i++){
            finRes[i] = res[store.get(nums1[i])];
        }
        return finRes;
    }
}


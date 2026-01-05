// https://leetcode.com/problems/daily-temperatures/

import java.util.*;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> st = new Stack<>();
        int n = temperatures.length;
        int[] res = new int[n];
        for(int i=n-1;i>=0;i--){

            // in this time we are storing indices instead of values
            while(!st.isEmpty() && temperatures[i]>=temperatures[st.peek()]){
                st.pop();
            }
            if(st.isEmpty()){
                res[i] = 0;
            }else{
                //  the difference between the current index and the index of the next greater element
                res[i] = st.peek() - i;
            }
            st.push(i);
        }
        return res;
    }
}
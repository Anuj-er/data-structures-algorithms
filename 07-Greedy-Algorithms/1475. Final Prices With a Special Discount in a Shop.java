// https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/

import java.util.*;

class Solution {
    public int[] finalPrices(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int size = prices.length;
        int[] res = new int[size];
        for(int i=size-1;i>=0;i--){
            while(!stack.isEmpty() && prices[i]<stack.peek()){
                stack.pop();
            }
            if(stack.isEmpty()){
                res[i] = prices[i];
            }else{
                res[i] = prices[i] - stack.peek();
            }
            stack.push(prices[i]);
        }
        return res; 
    }
}
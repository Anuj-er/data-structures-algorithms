// https://leetcode.com/problems/online-stock-span/

import java.util.*;

class StockSpanner {

    Stack<int[]> stack;
    int ind = -1;
    public StockSpanner() {
        ind = -1;
        stack = new Stack<>();
    }
    
    public int next(int price) {
        ind++;
        while(!stack.isEmpty() && price>=stack.peek()[0]){
            stack.pop();
        }
        int span;
        if(stack.isEmpty()){
            span = ind + 1;
        }else{
            span = ind - stack.peek()[1];
        }
        stack.push(new int[]{price,ind});
        return span;
    }
}

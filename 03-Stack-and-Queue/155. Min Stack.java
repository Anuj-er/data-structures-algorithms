// https://leetcode.com/problems/min-stack/

import java.util.*;

class MinStack {

    private Stack<int[]> res;

    public MinStack() {
        res = new Stack<>();
    }
    
    public void push(int val) {
        if(res.isEmpty()){
            res.push(new int[]{val,val});
            return;
        }
        int[] top = res.peek();
        int min = top[1];
        if(val>min){
            res.push(new int[]{val,min});
        }else{
            res.push(new int[]{val,val});
        }
    }
    
    public void pop() {
        res.pop();
    }
    
    public int top() {
        int[] fin = res.peek();
        return fin[0];
    }
    
    public int getMin() {
        int[] fin = res.peek();
        return fin[1];
    }
}

// Key learnings:
// In the stack we are storing a pair of both value and the min value by using  Stack<int[]> 


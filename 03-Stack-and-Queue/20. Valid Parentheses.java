// https://leetcode.com/problems/valid-parentheses/

import java.util.*;

class Solution {
    public boolean isValid(String s) {
        if(s.length()%2!=0){
            return false;
        }
        Stack<Character> res = new Stack<>();
        for(char c : s.toCharArray()){
            if(c=='(' || c=='[' || c=='{'){
                res.push(c);
            } else {
                if(res.isEmpty()){
                    return false;
                }
                char top = res.peek();
                if(c==')' && top == '(' || c==']' && top == '[' || c=='}' && top == '{'){
                    res.pop();
                }else{
                    return false;
                }
            }
        }
        return res.isEmpty();
}
}



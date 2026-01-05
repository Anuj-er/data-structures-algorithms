// https://leetcode.com/problems/baseball-game/

import java.util.*;

class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> score = new Stack<>();
        int sum = 0;
        for(String c: operations){
            if(c.equals("+") || c.equals("D") || c.equals("C")){
                if(c.equals("+")){
                    int first = score.pop();
                    int second = score.peek();
                    int sumN = first+second;
                    score.push(first);
                    score.push(sumN);
                    sum += sumN;

                }else if(c.equals("D")){
                    int first = score.peek();
                    int sumN = 2*first;
                    score.push(sumN);
                    sum += sumN; 

                }else if(c.equals("C")){
                    int rem = score.pop();
                    sum = sum - rem;    
                }
            }else{
                int num = Integer.parseInt(c);
                score.push(num);
                sum = sum + num;
            }
        }
        return sum;
    }
}
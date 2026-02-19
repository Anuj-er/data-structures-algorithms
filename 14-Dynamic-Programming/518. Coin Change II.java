// https://leetcode.com/problems/coin-change-ii/description/

class Solution {

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] prev = new int[amount+1];
        for(int a=0;a<=amount;a++){
            if(a % coins[0] == 0)
                prev[a] =  1;//a / coins[0];
            else
                prev[a] = 0;//(int)(1e9) ;
        }
        
        for(int i=1;i<n;i++){
            int[] curr = new int[amount+1];
            for(int a=0;a<=amount;a++){
               int pick = 0;
                if(a >= coins[i])
                    pick = curr[a-coins[i]];
                int noPick = prev[a];
                curr[a] = pick+noPick;// Math.min(pick,noPick); 
            }
            prev = curr;
        }
        return prev[amount];
    }
}
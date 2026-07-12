1class Solution {
2    public int divide(int dividend, int divisor) {
3        int quo = dividend / divisor;
4        if(dividend==Integer.MIN_VALUE && divisor==-1){
5            return Integer.MAX_VALUE;
6        }
7        return quo;
8    }
9}
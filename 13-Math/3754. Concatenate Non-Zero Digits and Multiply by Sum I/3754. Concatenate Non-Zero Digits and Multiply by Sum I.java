1class Solution {
2    public long sumAndMultiply(int n) {
3        long x = 0, s = 0;
4        for (char c : String.valueOf(n).toCharArray())
5            if (c != '0') {
6                x = x * 10 + c - '0';
7                s += c - '0';
8            }
9        return x * s;
10    }
11}
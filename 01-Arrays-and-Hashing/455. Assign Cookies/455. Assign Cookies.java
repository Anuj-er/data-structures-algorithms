1class Solution {
2    public int findContentChildren(int[] g, int[] s) {
3        Arrays.sort(g);
4        Arrays.sort(s);
5        int child=0;
6        int index=0;
7        while(index<s.length && child<g.length){
8            if(s[index]>=g[child]){
9                child++;
10            }
11            index++;
12        }
13        return child;
14    }
15}
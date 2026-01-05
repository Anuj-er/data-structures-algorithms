// https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/

class Solution {
    public int strStr(String haystack, String needle) {
        int firstIdx = -1;
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();

        for(int i=0;i<=h.length-n.length;i++){
            if(h[i]==n[0]){
                boolean match = true;
                for(int j=1;j<n.length;j++){
                    if(i+j >= h.length || h[i+j] != n[j]){
                        match = false;
                        break;
                    }
                }
                if(match){
                    firstIdx = i;
                    break;
                }
            }
        }
        return firstIdx;

    }
}
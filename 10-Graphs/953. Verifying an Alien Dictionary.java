// https://leetcode.com/problems/verifying-an-alien-dictionary/description/

class Solution {
    int[] rank;
    private boolean compare(String a, String b){
        int len = Math.min(a.length(),b.length());
        for(int i = 0; i<len;i++){
            int c1 = a.charAt(i);
            int c2 = b.charAt(i);

            if(c1 != c2) return rank[c1-'a'] < rank[c2 - 'a'];
        }
        return a.length() <= b.length();
    }
    public boolean isAlienSorted(String[] words, String order) {
        rank = new int[26];
        for(int i = 0;i<26;i++) rank[order.charAt(i) - 'a'] = i;

        for(int i = 0; i<words.length-1;i++){
            if(!compare(words[i],words[i+1])) return false;
        }
        return true;
    }
}
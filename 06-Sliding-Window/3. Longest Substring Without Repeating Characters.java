// https://leetcode.com/problems/longest-substring-without-repeating-characters/

class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> mp = new HashMap<>();
        int l = 0, maxLen = 0;
        for (int r = 0; r < s.length(); r++) {
            char ch = s.charAt(r);
            if (mp.containsKey(ch) && mp.get(ch) >= l)
                l = mp.get(ch) + 1;
            mp.put(ch, r);
            maxLen = Math.max(r - l + 1, maxLen);
        }
        return maxLen;
    }
}

// class Solution {
//     public int lengthOfLongestSubstring(String s) {
//         Map<Character,Integer> mp = new HashMap<>();
//         int size = s.length();
//         int l = 0, r = 0, maxLen = 0;
//         while(r<size){
//             if(mp.containsKey(s.charAt(r))){
//                 if(mp.get(s.charAt(r))>=l) l = mp.get(s.charAt(r))+1;
//             }
//             int len = r - l + 1;
//             maxLen = Math.max(len,maxLen);
//             mp.put(s.charAt(r),r);
//             r++;
//         }
//         return maxLen;
//     }
// }
// https://leetcode.com/problems/valid-anagram/description/

import java.util.HashMap;
class Solution {
    public boolean isAnagram(String s, String t) {
        // If lengths are different, not anagrams
        if(s.length() != t.length()) return false;

        HashMap<Character, Integer> map = new HashMap<>();

        // Count frequencies of characters in string s
        for(char c: s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        // Decrement frequencies based on string t
        for(char c: t.toCharArray()){
            if(!map.containsKey(c)) {
                return false;  // Character in t not in s
            }
            map.put(c, map.get(c) - 1);
        }
        
        // Check if all counts are zero (balanced character frequencies)
        for(int count : map.values()){
            if(count != 0){
                return false;  // Mismatch in counts
            }
        }
        
        return true;  // All characters match in frequency
    }
}

// https://leetcode.com/problems/valid-palindrome/

class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder filtered = new StringBuilder();
        for(char a : s.toCharArray()){
            if(Character.isLetterOrDigit(a)){
                filtered.append(Character.toLowerCase(a));
            }
        }
        StringBuilder reversed = new StringBuilder(filtered);
        reversed.reverse();
        return filtered.toString().equals(reversed.toString());
    }
}
// Time Complexity: O(n)
// Space Complexity: O(n)
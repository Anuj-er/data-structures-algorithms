// https://leetcode.com/problems/excel-sheet-column-number/

class Solution {
    public int titleToNumber(String n) {
        int ans = 0;
        for(int i = 0;i<n.length();i++){
            ans = ans*26+n.charAt(i)-64;
        }
        return ans;
    }
}
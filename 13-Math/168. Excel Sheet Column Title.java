// https://leetcode.com/problems/excel-sheet-column-title/

// faster solution
class Solution {
    public String convertToTitle(int n) {
        StringBuilder ans = new StringBuilder();
        while(n>0){
            n--;
            char c = (char) ('A' + n % 26);
            ans.append(c);
            n /= 26;
        }
        return ans.reverse().toString();
    }
}

// SLower answer to insert at first as it is shifting everytime;
// class Solution {
//     public String convertToTitle(int n) {
//         StringBuilder ans = new StringBuilder();
//         while(n>0){
//             n--;
//             char c = (char) ('A' + n % 26);
//             ans.insert(0,c);
//             n /= 26;
//         }
//         return ans.toString();
//     }
// }
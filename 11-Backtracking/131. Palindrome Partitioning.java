// https://leetcode.com/problems/palindrome-partitioning/description/

class Solution {

    List<List<String>> ans;

    private boolean isPalindrome(String s , int left , int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    private void solve(int start ,String s, List<String> partitions){
        if(start == s.length()){
            ans.add(new ArrayList<>(partitions));
            return;
        }
        for(int end = start;end<s.length();end++){
            if(isPalindrome(s,start,end)){
                partitions.add(s.substring(start,end+1));
                solve(end+1,s,partitions);
                partitions.remove(partitions.size()-1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        ans = new ArrayList<>();
        solve(0,s,new ArrayList<>());
        return ans;
    }
}

// class Solution {

//     List<List<String>> ans;

//     private boolean isPalindrome(String s) {
//         int left = 0, right = s.length() - 1;

//         while (left < right) {
//             if (s.charAt(left) != s.charAt(right)) {
//                 return false;
//             }
//             left++;
//             right--;
//         }
//         return true;
//     }

//     private void solve(int start ,String s, List<String> partitions){
//         if(start == s.length()){
//             ans.add(new ArrayList<>(partitions));
//             return;
//         }
//         for(int end = start;end<s.length();end++){
//             String part = s.substring(start,end+1);
//             if(isPalindrome(part)){
//                 partitions.add(part);
//                 solve(end+1,s,partitions);
//                 partitions.remove(partitions.size()-1);
//             }
//         }
//     }

//     public List<List<String>> partition(String s) {
//         ans = new ArrayList<>();
//         solve(0,s,new ArrayList<>());
//         return ans;
//     }
// }

// class Solution {

//     List<List<String>> ans;

//     private boolean isPalindrome(String s) {
//         int left = 0, right = s.length() - 1;

//         while (left < right) {
//             if (s.charAt(left) != s.charAt(right)) {
//                 return false;
//             }
//             left++;
//             right--;
//         }
//         return true;
//     }

//     private void solve(String s, List<String> partitions){
//         if(s.length() == 0){
//             ans.add(new ArrayList<>(partitions));
//             return;
//         }
//         for(int i=0;i<s.length();i++){
//             String part = s.substring(0,i+1);
//             if(isPalindrome(part)){
//                 partitions.add(part);
//                 solve(s.substring(i+1),partitions);
//                 partitions.remove(partitions.size()-1);
//             }
//         }
//     }

//     public List<List<String>> partition(String s) {
//         ans = new ArrayList<>();
//         solve(s,new ArrayList<>());
//         return ans;
//     }
// }
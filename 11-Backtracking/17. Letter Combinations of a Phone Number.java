// https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/

class Solution {
    List<String> ans;

    private static final String[] keyPad = {
        "",
        "",
        "abc",
        "def",
        "ghi",
        "jkl",
        "mno",
        "pqrs",
        "tuv",
        "wxyz"
    };

    private void solve(int idx, String digits, StringBuilder sb) {
        if (idx == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        int digit = digits.charAt(idx) - '0';
        String letters = keyPad[digit];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            solve(idx + 1, digits, sb);
            sb.setLength(sb.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        ans = new ArrayList<>();
        if (digits.length() == 0)
            return ans;
        solve(0, digits, new StringBuilder());
        return ans;
    }
}

// class Solution {
//     Map<Character, String> mp;
//     List<String> ans;

//     private void solve(int idx, String digits, StringBuilder sb) {
//         if (idx == digits.length()) {
//             ans.add(sb.toString());
//             return;
//         }
//         String letters = mp.get(digits.charAt(idx));
//         for (char c : letters.toCharArray()) {
//             sb.append(c);
//             solve(idx + 1, digits, sb);
//             sb.setLength(sb.length() - 1);
//         }
//     }

//     public List<String> letterCombinations(String digits) {
//         mp = getMap();
//         ans = new ArrayList<>();
//         if (digits.length() == 0)
//             return ans;
//         solve(0, digits, new StringBuilder());
//         return ans;
//     }

//     private Map<Character, String> getMap() {
//         Map<Character, String> map = new HashMap<>();
//         map.put('2', "abc");
//         map.put('3', "def");
//         map.put('4', "ghi");
//         map.put('5', "jkl");
//         map.put('6', "mno");
//         map.put('7', "pqrs");
//         map.put('8', "tuv");
//         map.put('9', "wxyz");
//         return map;
//     }
// }

// class Solution {
//     Map<Character,String> mp;
//     List<String> ans;

//     private void solve(int idx , String digits , String s){
//         if(idx == digits.length()){
//             ans.add(s);
//             return;
//         }
//         for(char c : mp.get(digits.charAt(idx)).toCharArray()){
//             String temp = s + c;
//             solve(idx+1,digits,temp);
//         }
//     }

//     public List<String> letterCombinations(String digits) {
//         mp = getMap();
//         ans = new ArrayList<>();
//         if(digits.length() == 0) return ans;
//         solve(0,digits,"");
//         return ans;
//     }

//     private Map<Character,String> getMap(){
//         Map<Character,String> map = new HashMap<>();
//         map.put('2',"abc");
//         map.put('3',"def");
//         map.put('4',"ghi");
//         map.put('5',"jkl");
//         map.put('6',"mno");
//         map.put('7',"pqrs");
//         map.put('8',"tuv");
//         map.put('9',"wxyz");
//         return map;
//     }
// }
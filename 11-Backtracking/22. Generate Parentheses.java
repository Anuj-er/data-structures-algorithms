// https://leetcode.com/problems/generate-parentheses/

// // =======================
// // Optimized Backtracking Solution
// // Uses StringBuilder with pre-defined capacity
// // Uses setLength() for faster backtracking
// // =======================

class Solution {

    // Stores all valid parenthesis combinations
    List<String> ans;

    // Entry point: initializes result list and starts backtracking
    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        // Pre-allocating capacity avoids internal resizing
        solve(new StringBuilder(2 * n), 0, 0, n);
        return ans;
    }

    // Backtracking function
    // s     -> current constructed string
    // open  -> number of '(' used
    // close -> number of ')' used
    // total -> total pairs required
    private void solve(StringBuilder s, int open, int close, int total) {

        // Base case: valid sequence of required length
        if (s.length() == 2 * total) {
            ans.add(s.toString());
            return;
        }

        // Try adding '(' if limit not reached
        if (open < total) {
            solve(s.append('('), open + 1, close, total);
            // Backtrack: remove last character
            s.setLength(s.length() - 1);
        }

        // Try adding ')' only if it keeps sequence valid
        if (close < open) {
            solve(s.append(')'), open, close + 1, total);
            // Backtrack: remove last character
            s.setLength(s.length() - 1);
        }
    }
}

// // =======================
// // Standard Backtracking Solution
// // Uses StringBuilder with deleteCharAt()
// // =======================

// class Solution {

//     // Stores all valid parenthesis combinations
//     List<String> ans;

//     // Entry point
//     public List<String> generateParenthesis(int n) {
//         ans = new ArrayList<>();
//         solve(new StringBuilder(), 0, 0, n);
//         return ans;
//     }

//     // Backtracking helper
//     private void solve(StringBuilder s, int open, int close, int total) {

//         // Base condition: full-length valid sequence
//         if (s.length() == 2 * total) {
//             ans.add(s.toString());
//             return;
//         }

//         // Add '(' if allowed
//         if (open < total) {
//             solve(s.append('('), open + 1, close, total);
//             // Undo the append
//             s.deleteCharAt(s.length() - 1);
//         }

//         // Add ')' only when it keeps the string valid
//         if (close < open) {
//             solve(s.append(')'), open, close + 1, total);
//             // Undo the append
//             s.deleteCharAt(s.length() - 1);
//         }
//     }
// }

// =======================
// Immutable String Version
// Simpler but less efficient due to string copying
// =======================

// class Solution {

//     // Stores all valid parenthesis combinations
//     List<String> ans;

//     // Entry point
//     public List<String> generateParenthesis(int n) {
//         ans = new ArrayList<>();
//         solve("", 0, 0, n);
//         return ans;
//     }

//     // Backtracking using immutable String
//     private void solve(String s, int open, int close, int total) {

//         // Base case: completed valid string
//         if (s.length() == 2 * total) {
//             ans.add(s);
//             return;
//         }

//         // Add '(' if we still have remaining opens
//         if (open < total)
//             solve(s + '(', open + 1, close, total);

//         // Add ')' only if it does not invalidate the sequence
//         if (close < open)
//             solve(s + ')', open, close + 1, total);
//     }
// }
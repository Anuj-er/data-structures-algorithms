// https://leetcode.com/problems/word-search/

class Solution {

    static final int[][] dirs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

    private boolean solve(int curr, int i, int j, char[][] board, String word) {
        if (curr == word.length() - 1)
            return true;

        for (int[] d : dirs) {
            int x = i + d[0];
            int y = j + d[1];

            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                if (board[x][y] == word.charAt(curr + 1)) {
                    char temp = board[x][y];
                    board[x][y] = '#';
                    boolean ans = solve(curr + 1, x, y, board, word);
                    board[x][y] = temp;
                    if (ans)
                        return true;
                }
            }
        }
        return false;
    }

    public boolean exist(char[][] board, String word) {
        if (word.length() == 0)
            return false;

        int[] freq = new int[128];
        for (char[] row : board) {
            for (char c : row) {
                freq[c]++;
            }
        }
        for (char c : word.toCharArray()) {
            if (--freq[c] < 0)
                return false;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    char temp = board[i][j];
                    board[i][j] = '#';
                    if (solve(0, i, j, board, word))
                        return true;
                    board[i][j] = temp;
                }
            }
        }
        return false;
    }
}

// class Solution {

//     static final int[][] dirs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

//     private boolean solve(int curr, int i, int j, char[][] board, String word) {
//         if (curr == word.length()-1)
//             return true;

//         for (int[] d : dirs) {
//             int x = i + d[0];
//             int y = j + d[1];

//             if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
//                 if (board[x][y] == word.charAt(curr + 1)) {
//                     char temp = board[x][y];
//                     board[x][y] = '#';
//                     boolean ans = solve(curr + 1, x, y, board, word);
//                     board[x][y] = temp;
//                     if (ans)
//                         return true;
//                 }
//             }
//         }
//         return false;
//     }

//     public boolean exist(char[][] board, String word) {
//         if (word.length() == 0)
//             return false;
//         for (int i = 0; i < board.length; i++) {
//             for (int j = 0; j < board[0].length; j++) {
//                 if (board[i][j] == word.charAt(0)) {
//                     char temp = board[i][j];
//                     board[i][j] = '#';
//                     if (solve(0, i, j, board, word))
//                         return true;
//                     board[i][j] = temp;
//                 }
//             }
//         }
//         return false;
//     }
// }

// class Solution {
//     boolean[][] visited;

//     private boolean solve(int curr, int i, int j, char[][] board, String word) {
//         if (curr == word.length() - 1)
//             return true;

//         if (i - 1 >= 0 && board[i - 1][j] == word.charAt(curr + 1) && !visited[i - 1][j]) {
//             visited[i - 1][j] = true;
//             boolean ans = solve(curr + 1, i - 1, j, board, word);
//             if (ans)
//                 return true;
//             visited[i - 1][j] = false;
//         }

//         if (j - 1 >= 0 && board[i][j - 1] == word.charAt(curr + 1) && !visited[i][j - 1]) {
//             visited[i][j - 1] = true;
//             boolean ans = solve(curr + 1, i, j - 1, board, word);
//             if (ans)
//                 return true;
//             visited[i][j - 1] = false;
//         }

//         if (i + 1 < board.length && board[i + 1][j] == word.charAt(curr + 1) && !visited[i + 1][j]) {
//             visited[i + 1][j] = true;
//             boolean ans = solve(curr + 1, i + 1, j, board, word);
//             if (ans)
//                 return true;
//             visited[i + 1][j] = false;
//         }

//         if (j + 1 < board[0].length && board[i][j + 1] == word.charAt(curr + 1) && !visited[i][j + 1]) {
//             visited[i][j + 1] = true;
//             boolean ans = solve(curr + 1, i, j + 1, board, word);
//             if (ans)
//                 return true;
//             visited[i][j + 1] = false;
//         }

//         return false;
//     }

//     public boolean exist(char[][] board, String word) {
//         visited = new boolean[board.length][board[0].length];
//         if(word.length() == 0) return false;
//         for (int i = 0; i < board.length; i++) {
//             for (int j = 0; j < board[0].length; j++) {
//                 if (board[i][j] == word.charAt(0)) {
//                     visited[i][j] = true;
//                     if (solve(0, i, j, board, word))
//                         return true;
//                     visited[i][j] = false;
//                 }
//             }
//         }
//         return false;
//     }
// }
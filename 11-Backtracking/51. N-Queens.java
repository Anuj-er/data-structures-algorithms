// https://leetcode.com/problems/N-Queens/

class Solution {
    List<List<String>> ans;
    boolean[] col,diag1,diag2;

    private void nQueens(int row, int n, char[][] board) {
        if (row == n) {
            List<String> res = new ArrayList<>(n);
            for(int i = 0;i<n;i++){
                res.add(new String(board[i]));
            }
            ans.add(res);
            return;
        }
        for(int c = 0; c < n ;c++){
            int d1 = row - c + n - 1;
            int d2 = row + c;

            if(col[c] || diag1[d1] || diag2[d2]) continue;

            board[row][c] = 'Q';
            col[c] = diag1[d1] = diag2[d2] = true;
            nQueens(row+1,n,board);
            board[row][c] = '.';
            col[c] = diag1[d1] = diag2[d2] = false;
        }
    }

    public List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        col = new boolean[n];
        diag1 = new boolean[2*n-1];
        diag2 = new boolean[2*n-1];
        char[][] board = new char[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(board[i],'.');
        }
        nQueens(0, n, board);
        return ans;
    }
}

// class Solution {
//     List<List<String>> ans;
//     boolean[] col,diag1,diag2;

//     private void nQueens(int row, int n, List<StringBuilder> board) {
//         if (row == n) {
//             List<String> res = new ArrayList<>(n);
//             for(StringBuilder sb: board){
//                 res.add(sb.toString());
//             }
//             ans.add(res);
//             return;
//         }
//         for(int c = 0; c < n ;c++){
//             int d1 = row - c + n - 1;
//             int d2 = row + c;

//             if(col[c] || diag1[d1] || diag2[d2]) continue;

//             board.get(row).setCharAt(c,'Q');
//             col[c] = diag1[d1] = diag2[d2] = true;
//             nQueens(row+1,n,board);
//             board.get(row).setCharAt(c,'.');
//             col[c] = diag1[d1] = diag2[d2] = false;
//         }
//     }

//     public List<List<String>> solveNQueens(int n) {
//         ans = new ArrayList<>();
//         col = new boolean[n];
//         diag1 = new boolean[2*n-1];
//         diag2 = new boolean[2*n-1];
//         List<StringBuilder> res = new ArrayList<>();
//         for(int i = 0;i<n;i++){
//             StringBuilder sb = new StringBuilder();
//             for(int j=0;j<n;j++){
//                 sb.append('.');
//             }
//             res.add(sb);
//         }
//         nQueens(0, n, res);
//         return ans;
//     }
// }

// class Solution {
//     List<List<String>> ans;

//     private boolean isSafe(List<StringBuilder> board,int n,int row, int col){
//         for(int i = 0; i<row;i++){
//             if(board.get(i).charAt(col) == 'Q') return false;
//         }
//         // Left Diagonal;
//         int r = row-1;
//         int c = col-1;
//         while(r>=0 && c>=0){
//             if(board.get(r--).charAt(c--) == 'Q') return false; 
//         }

//         // Right Diagonal
//         r = row-1;
//         c = col+1;
//         while(r>=0 && c<n){
//             if(board.get(r--).charAt(c++) == 'Q') return false;
//         }
//         return true;
//     }

//     private void nQueens(int row, int n, List<StringBuilder> board) {
//         if (row == n) {
//             List<String> res = new ArrayList<>();
//             for(StringBuilder sb: board){
//                 res.add(sb.toString());
//             }
//             ans.add(res);
//             return;
//         }
//         for (int j = 0; j < n; j++) {
//             if (isSafe(board, n,row, j)) {
//                 board.get(row).setCharAt(j,'Q');
//                 nQueens(row + 1, n, board);
//                 board.get(row).setCharAt(j,'.');
//             }
//         }
//     }

//     public List<List<String>> solveNQueens(int n) {
//         ans = new ArrayList<>();
//         List<StringBuilder> res = new ArrayList<>();
//         for(int i = 0;i<n;i++){
//             StringBuilder sb = new StringBuilder();
//             for(int j=0;j<n;j++){
//                 sb.append('.');
//             }
//             res.add(sb);
//         }
//         nQueens(0, n, res);
//         return ans;
//     }
// }
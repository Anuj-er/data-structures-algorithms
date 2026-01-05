// https://leetcode.com/problems/sudoku-solver/description/

class Solution {

    boolean[][] rowUsed;
    boolean[][] colUsed;
    boolean[][] boxUsed;

    private boolean solve(char[][] board, int row, int col) {
        if (row == 9)
            return true;

        int nextRow = row, nextCol = col + 1;
        if (nextCol == 9) {
            nextRow++;
            nextCol = 0;
        }

        if (board[row][col] != '.') {
            return solve(board, nextRow, nextCol);
        }

        int box = (row / 3) * 3 + (col / 3);
        for (int d = 1; d <= 9; d++) {
            if (!rowUsed[row][d] && !colUsed[col][d] && !boxUsed[box][d]) {

                board[row][col] = (char) (d + '0');
                rowUsed[row][d] = colUsed[col][d] = boxUsed[box][d] = true;

                if (solve(board, nextRow, nextCol))
                    return true;

                board[row][col] = '.';
                rowUsed[row][d] = colUsed[col][d] = boxUsed[box][d] = false;
            }
        }
        return false;
    }

    public void solveSudoku(char[][] board) {
        rowUsed = new boolean[9][10];
        colUsed = new boolean[9][10];
        boxUsed = new boolean[9][10];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int d = board[i][j] - '0';
                    rowUsed[i][d] = true;
                    colUsed[j][d] = true;
                    boxUsed[(i / 3) * 3 + (j / 3)][d] = true;
                }
            }
        }
        solve(board, 0, 0);
    }
}

// class Solution {

//     private boolean isSafe(char[][] board, int row, int col, char dig) {
//         // Horizontal
//         for(int j = 0;j<9;j++){
//             if(board[row][j] == dig) return false;
//         }
//         // Vertical
//         for(int i = 0;i<9;i++){
//             if(board[i][col] == dig) return false;
//         }
//         // Grid
//         int sr =  (row/3) * 3;
//         int sc = (col/3) * 3;
//         for(int i = sr;i<sr+3;i++){
//             for(int j = sc;j<sc+3;j++){
//                 if(board[i][j] == dig) return false;
//             }
//         }
//         return true;
//     }

//     public boolean solve(char[][] board, int row,int col){
//         if(row == 9) return true;

//         int nextRow = row, nextCol = col+1;
//         if(nextCol == 9){
//             nextRow = row + 1;
//             nextCol = 0;
//         } 

//         if(board[row][col] != '.'){
//             return solve(board,nextRow,nextCol);
//         }

//         // Placing Digit
//         for(char dig = '1';dig<='9';dig++){
//             if(isSafe(board,row,col,dig)){
//                 board[row][col] = dig;
//                 if(solve(board,nextRow,nextCol)) return true;
//                 board[row][col] = '.';
//             }
//         }
//         return false;
//     }

//     public void solveSudoku(char[][] board) {
//         solve(board,0,0);
//     }
// }

// https://leetcode.com/problems/flood-fill/description/

class Solution {

    private void dfs(int[][] image, int row, int col, int color, int[] delRow, int[] delCol, int[][] ans,
            int iniColor) {

        ans[row][col] = color;
        int n = ans.length;
        int m = ans[0].length;

        for (int i = 0; i < 4; i++) {
            int nrow = row + delRow[i];
            int ncol = col + delCol[i];
            if (nrow >= 0 && ncol >= 0 && nrow < n && ncol < m) {
                if (image[nrow][ncol] == iniColor && ans[nrow][ncol] != color) {
                    dfs(image, nrow, ncol, color, delRow, delCol, ans, iniColor);
                }
            }
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int iniColor = image[sr][sc];
        int[][] ans = image;

        int[] delRow = { 0, 1, 0, -1 };
        int[] delCol = { -1, 0, 1, 0 };

        dfs(image, sr, sc, color, delRow, delCol, ans, iniColor);
        return ans;
    }
}
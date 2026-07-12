int minPathSum(vector<vector<int>>& grid, int i, int j, vector<vector<int>>& memo) {
    int m = grid.size();
    int n = grid[0].size();
    
    // Check if we have already calculated the minimum path sum for this cell
    if (memo[i][j] != -1) {
        return memo[i][j];
    }
    
    // Base case: we have reached the bottom right corner
    if (i == m - 1 && j == n - 1) {
        memo[i][j] = grid[i][j];
        return memo[i][j];
    }
    
    // Calculate the minimum path sum by moving right and down
    int rightSum = INT_MAX;
    int downSum = INT_MAX;
    if (j < n - 1) {
        rightSum = minPathSum(grid, i, j + 1, memo);
    }
    if (i < m - 1) {
        downSum = minPathSum(grid, i + 1, j, memo);
    }
    
    // Store the minimum path sum for this cell in the memoization matrix
    memo[i][j] = min(rightSum, downSum) + grid[i][j];
    
    // Return the minimum path sum
    return memo[i][j];
}

int minPathSum(vector<vector<int>>& grid) {
    int m = grid.size();
    int n = grid[0].size();
    
    // Create a memoization matrix to store the minimum path sum for each cell
    vector<vector<int>> memo(m, vector<int>(n, -1));
    
    // Call the helper function to find the minimum path sum
    return minPathSum(grid, 0, 0, memo);
}
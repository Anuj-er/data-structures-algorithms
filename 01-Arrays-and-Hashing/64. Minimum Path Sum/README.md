<h2><a href="https://leetcode.com/problems/minimum-path-sum">64. Minimum Path Sum</a></h2>

<p>Given a <code>m x n</code> <code>grid</code> filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.</p>

<p><strong>Note:</strong> You can only move either down or right at any point in time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/05/minpath.jpg" style="width: 242px; height: 242px;" />
<pre>
<strong>Input:</strong> grid = [[1,3,1],[1,5,1],[4,2,1]]
<strong>Output:</strong> 7
<strong>Explanation:</strong> Because the path 1 &rarr; 3 &rarr; 1 &rarr; 1 &rarr; 1 minimizes the sum.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,2,3],[4,5,6]]
<strong>Output:</strong> 12
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>0 &lt;= grid[i][j] &lt;= 200</code></li>
</ul>


---

# 🛍️ Minimum-Path-Sum | Explained

## Approach 1: Dynamic Programming
### Intuition
The intuition behind this approach is to break down the problem into smaller sub-problems and store the solutions to these sub-problems to avoid redundant calculations. In the context of the minimum-path-sum problem, this approach is analogous to a traveler who is trying to find the shortest path to a destination. The traveler will choose the path that has the minimum cost at each step, and the cost of reaching a particular cell is the minimum cost of reaching the cell above it or to its left, plus the cost of the cell itself.

### Approach
The approach is to iterate over the grid and at each cell, calculate the minimum cost of reaching that cell by considering the costs of the cells above and to the left. This is a bottom-up dynamic programming approach, where we start with the base cases (the first row and the first column) and fill up the rest of the grid based on these base cases.

### Detailed Code Analysis
The code starts by getting the number of rows (`m`) and columns (`n`) in the grid. Then, it iterates over the first column of the grid, starting from the second row (`i = 1`), and for each cell, it adds the cost of the cell above it to the current cell's cost. This is because the minimum cost of reaching a cell in the first column is the cost of the cell above it plus the cost of the current cell.

Next, the code iterates over the first row of the grid, starting from the second column (`j = 1`), and for each cell, it adds the cost of the cell to the left to the current cell's cost. This is because the minimum cost of reaching a cell in the first row is the cost of the cell to the left plus the cost of the current cell.

Then, the code iterates over the rest of the grid, starting from the second row and the second column (`i = 1` and `j = 1`). For each cell, it calculates the minimum cost of reaching that cell by considering the costs of the cells above and to the left, and adds the cost of the current cell to the minimum cost. This is the core of the dynamic programming approach, where we break down the problem into smaller sub-problems and store the solutions to these sub-problems to avoid redundant calculations.

Finally, the code returns the minimum cost of reaching the bottom-right cell of the grid, which is the solution to the minimum-path-sum problem.

### Code
```java
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i-1][0];
        }
        
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j-1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        
        return grid[m-1][n-1];
    }
}
```

### Complexity
- **Time:** The time complexity of this approach is O(m*n), where m is the number of rows and n is the number of columns in the grid. This is because we are iterating over the grid once to calculate the minimum costs of reaching each cell.
- **Space:** The space complexity of this approach is O(1), not including the space required for the input grid. This is because we are modifying the input grid in-place to store the minimum costs of reaching each cell, and we are not using any additional data structures that scale with the input size.
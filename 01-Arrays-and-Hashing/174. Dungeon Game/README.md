<h2><a href="https://leetcode.com/problems/minimum-path-sum">174. Dungeon Game</a></h2>

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

# 🛍️ Dungeon-Game | Explained

## Approach 1: Dynamic Programming
### Intuition
The Dungeon Game problem can be solved using dynamic programming, which is an approach that breaks down complex problems into smaller subproblems, solves each subproblem only once, and stores the solutions to subproblems to avoid redundant computation. In this case, the intuition is to simulate the process of moving through the dungeon grid from top to bottom and from left to right, keeping track of the minimum sum of health points needed to reach each cell.

### Algorithm Visualized
```mermaid
graph LR
    A[Start] --> B[Initialize first row and column]
    B --> C[Iterate through the rest of the grid]
    C --> D[Calculate minimum sum at each cell]
    D --> E[Return minimum sum at the bottom-right cell]
```
### Approach
The approach involves iterating through the grid and at each cell, calculating the minimum sum of health points needed to reach that cell from the top or from the left.

### Detailed Code Analysis
Let's take a closer look at the code:
- Lines 3-4: `int m = grid.length;` and `int n = grid[0].length;` are used to store the number of rows and columns in the grid, respectively.
- Lines 6-8: The first column of the grid is initialized by adding the value of each cell to the value of the cell above it. This is because the minimum sum of health points needed to reach each cell in the first column is the sum of the health points needed to reach the cell above it.
- Lines 10-12: The first row of the grid is initialized in a similar way, by adding the value of each cell to the value of the cell to its left.
- Lines 13-17: The rest of the grid is then filled in by iterating through each cell and adding its value to the minimum of the values of the cells above and to its left. This ensures that the minimum sum of health points needed to reach each cell is calculated correctly.
- Line 19: Finally, the minimum sum of health points needed to reach the bottom-right cell is returned.

### Code
```java
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
```
### Complexity
- **Time:** O(m*n), where m is the number of rows and n is the number of columns in the grid, because we need to iterate through each cell in the grid once.
- **Space:** O(1), because we are modifying the input grid in-place and not using any additional space that scales with the input size.

## 🕵️‍♂️ Follow-up Questions (Optional)
Some common follow-up questions for this pattern include: 
1. How would you modify the solution to handle a grid with negative values?
Answer: The solution already handles grids with negative values correctly, because it uses the minimum sum of health points needed to reach each cell.
2. How would you optimize the solution for very large grids?
Answer: The solution already has a time complexity of O(m*n), which is optimal for this problem. However, you could consider using a more efficient algorithm or data structure, such as a priority queue or a heap, to further improve performance.
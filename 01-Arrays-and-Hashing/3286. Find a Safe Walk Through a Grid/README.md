<h2><a href="https://leetcode.com/problems/find-a-safe-walk-through-a-grid">3286. Find a Safe Walk Through a Grid</a></h2>

<p>You are given an <code>m x n</code> binary matrix <code>grid</code> and an integer <code>health</code>.</p>

<p>You start on the upper-left corner <code>(0, 0)</code> and would like to get to the lower-right corner <code>(m - 1, n - 1)</code>.</p>

<p>You can move up, down, left, or right from one cell to another adjacent cell as long as your health <em>remains</em> <strong>positive</strong>.</p>

<p>Cells <code>(i, j)</code> with <code>grid[i][j] = 1</code> are considered <strong>unsafe</strong> and reduce your health by 1.</p>

<p>Return <code>true</code> if you can reach the final cell with a health value of 1 or more, and <code>false</code> otherwise.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]], health = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The final cell can be reached safely by walking along the gray cells below.</p>
<img alt="" src="https://assets.leetcode.com/uploads/2024/08/04/3868_examples_1drawio.png" style="width: 301px; height: 121px;"></div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[0,1,1,0,0,0],[1,0,1,0,0,0],[0,1,1,1,0,1],[0,0,1,0,1,0]], health = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<p>A minimum of 4 health points is needed to reach the final cell safely.</p>
<img alt="" src="https://assets.leetcode.com/uploads/2024/08/04/3868_examples_2drawio.png" style="width: 361px; height: 161px;"></div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">grid = [[1,1,1],[1,0,1],[1,1,1]], health = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The final cell can be reached safely by walking along the gray cells below.</p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/08/04/3868_examples_3drawio.png" style="width: 181px; height: 121px;"></p>

<p>Any path that does not go through the cell <code>(1, 1)</code> is unsafe since your health will drop to 0 when reaching the final cell.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 50</code></li>
	<li><code><font face="monospace">2 &lt;= m * n</font></code></li>
	<li><code>1 &lt;= health &lt;= m + n</code></li>
	<li><code>grid[i][j]</code> is either 0 or 1.</li>
</ul>


---

# 🛍️ Find-a-Safe-Walk-Through-a-Grid | Explained

## Approach 1 (Memoized Depth-First Search with a Visited Array)
### Intuition
Imagine you are navigating a grid with obstacles, trying to find a safe path to the destination. This approach uses a memoized depth-first search to explore all possible paths while avoiding visiting the same cell multiple times with the same health. It's like using a map to keep track of the paths you've already explored and the health you had when you visited each cell.
### Approach
1. Initialize a memoization table `memo` to store the results of subproblems and a visited array `va` to keep track of the cells you've visited with a specific health.
2. Start the depth-first search from the top-left cell with the given health.
3. At each cell, subtract the obstacle value from the current health and check if you've visited this cell with the same health before.
4. If you have, return false. Otherwise, mark the cell as visited with the current health.
5. Explore all possible neighboring cells and recursively call the depth-first search function.
6. If you reach the destination with a positive health, return true. Otherwise, return false and backtrack to explore other paths.
### Code
```java
private boolean go(int i, int j, int h, HashSet<Point> v, List<List<Integer>> grid, HashMap<Point, Boolean> map, Boolean[][][] memo, boolean[][][] va) {
    h = h - grid.get(i).get(j);
    // ...
    if (va[i][j][h])
        return false;
    // ...
    va[i][j][h] = true;
    for (int[] move : moves) {
        int nextI = i + move[0];
        int nextJ = j + move[1];
        if (isValid(nextI, nextJ, grid.size(), grid.get(0).size())) {
            if (go(nextI, nextJ, h, v, grid, map, memo, va))
                return true;
        }
    }
    // ...
    va[i][j][h] = false;
    // ...
}
```
### Complexity
- Time: O(r * c * health), where r and c are the number of rows and columns in the grid, and health is the given health.
- Space: O(r * c * health), for the memoization table and the visited array.

## Approach 2 (Memoized Depth-First Search with a HashMap)
### Intuition
This approach is similar to the first one, but instead of using a visited array, it uses a HashMap to keep track of the cells you've visited with a specific health.
### Approach
1. Initialize a memoization table `memo` to store the results of subproblems and a HashMap `map` to keep track of the cells you've visited with a specific health.
2. Start the depth-first search from the top-left cell with the given health.
3. At each cell, subtract the obstacle value from the current health and check if you've visited this cell with the same health before.
4. If you have, return the stored result. Otherwise, mark the cell as visited with the current health.
5. Explore all possible neighboring cells and recursively call the depth-first search function.
6. If you reach the destination with a positive health, return true. Otherwise, return false and backtrack to explore other paths.
### Code
```java
// commented out code
if (map.containsKey(p))
    return map.get(p);
// ...
if (v.contains(p))
    return false;
// ...
map.put(p, false);
```
### Complexity
- Time: O(r * c * health), where r and c are the number of rows and columns in the grid, and health is the given health.
- Space: O(r * c * health), for the memoization table and the HashMap.

## 🕵️‍♂️ Follow-up Questions (Optional)
Some possible follow-up questions for this problem are:
1. How would you optimize the solution if the grid is very large?
 Answer: You can use a more efficient data structure, such as a Trie or a Bloom filter, to keep track of the visited cells.
2. What if the grid has a dynamic size, and the health value can change over time?
 Answer: You can use a dynamic programming approach with a sliding window to keep track of the maximum health value at each cell.
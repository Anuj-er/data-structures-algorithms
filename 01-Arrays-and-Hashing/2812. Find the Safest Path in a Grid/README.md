<h2><a href="https://leetcode.com/problems/find-the-safest-path-in-a-grid">2812. Find the Safest Path in a Grid</a></h2>

<p>You are given a <strong>0-indexed</strong> 2D matrix <code>grid</code> of size <code>n x n</code>, where <code>(r, c)</code> represents:</p>

<ul>
	<li>A cell containing a thief if <code>grid[r][c] = 1</code></li>
	<li>An empty cell if <code>grid[r][c] = 0</code></li>
</ul>

<p>You are initially positioned at cell <code>(0, 0)</code>. In one move, you can move to any adjacent cell in the grid, including cells containing thieves.</p>

<p>The <strong>safeness factor</strong> of a path on the grid is defined as the <strong>minimum</strong> manhattan distance from any cell in the path to any thief in the grid.</p>

<p>Return <em>the <strong>maximum safeness factor</strong> of all paths leading to cell </em><code>(n - 1, n - 1)</code><em>.</em></p>

<p>An <strong>adjacent</strong> cell of cell <code>(r, c)</code>, is one of the cells <code>(r, c + 1)</code>, <code>(r, c - 1)</code>, <code>(r + 1, c)</code> and <code>(r - 1, c)</code> if it exists.</p>

<p>The <strong>Manhattan distance</strong> between two cells <code>(a, b)</code> and <code>(x, y)</code> is equal to <code>|a - x| + |b - y|</code>, where <code>|val|</code> denotes the absolute value of val.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/07/02/example1.png" style="width: 362px; height: 242px;">
<pre><strong>Input:</strong> grid = [[1,0,0],[0,0,0],[0,0,1]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/07/02/example2.png" style="width: 362px; height: 242px;">
<pre><strong>Input:</strong> grid = [[0,0,1],[0,0,0],[0,0,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The path depicted in the picture above has a safeness factor of 2 since:
- The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
It can be shown that there are no other paths with a higher safeness factor.
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2023/07/02/example3.png" style="width: 362px; height: 242px;">
<pre><strong>Input:</strong> grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The path depicted in the picture above has a safeness factor of 2 since:
- The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
- The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
It can be shown that there are no other paths with a higher safeness factor.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= grid.length == n &lt;= 400</code></li>
	<li><code>grid[i].length == n</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
	<li>There is at least one thief in the <code>grid</code>.</li>
</ul>


---

# 🛍️ Find-the-Safest-Path-in-a-Grid | Explained

## Approach 1 (BFS and Binary Search)
### Intuition
Imagine you're navigating through a grid where some cells are considered "unsafe" due to the presence of thieves. Your goal is to find the safest path from the top-left corner to the bottom-right corner. This can be thought of as finding the maximum "safeness factor" where you can avoid being within a certain distance of a thief. The idea is to use Breadth-First Search (BFS) to calculate the distance from each thief to all other cells, and then use Binary Search to find the maximum safeness factor that allows a path from the start to the end.

### Approach
1. First, perform a BFS traversal from all thief locations to calculate the minimum distance from each cell to a thief. This is done by initializing a distance matrix (`dist`) with all values set to -1, except for thief locations which are set to 0, and then iterating over all neighboring cells in a BFS manner.
2. Once the distance matrix is calculated, perform a Binary Search over the possible range of safeness factors (from 0 to 2n, where n is the size of the grid). For each mid-value, check if it's possible to reach the bottom-right corner from the top-left corner without being within the current safeness factor distance of a thief.
3. The check is performed using another BFS traversal, but this time from the top-left corner, and only moving to cells that are not within the current safeness factor distance of a thief.
4. If it's possible to reach the bottom-right corner, update the answer and continue the Binary Search in the upper half; otherwise, continue in the lower half.

### Code
```java
// Distance calculation using BFS
int[][] dist = new int[n][n];
for (int[] row : dist)
    Arrays.fill(row, -1);

Queue<int[]> queue = new LinkedList<>();

// Start BFS from every thief
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        if (grid.get(i).get(j) == 1) {
            dist[i][j] = 0;
            queue.offer(new int[] { i, j });
        }
    }
}

// ... (rest of the BFS code)

// Binary Search
int left = 0;
int right = 2 * n;
int ans = 0;

while (left <= right) {
    int mid = left + (right - left) / 2;
    
    if (canReach(dist, mid, n)) {
        ans = mid;
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}

// canReach function
private boolean canReach(int[][] dist, int limit, int n) {
    // ... (canReach function code)
}
```

### Complexity
- Time: O(n^2 * log(n)) (due to the Binary Search over the possible safeness factors and the BFS traversals)
- Space: O(n^2) (for the distance matrix and the queue)

## 🕵️‍♂️ Follow-up Questions (Optional)
Some common follow-up questions for this problem could be:
- How would you optimize the solution if the grid size is extremely large?
  - Answer: You could consider using a more efficient data structure, such as a heap, to store the cells to be visited, or use a more advanced algorithm, such as A\* search, to find the safest path.
- What if there are multiple safest paths? How would you modify the solution to find all of them?
  - Answer: You could modify the `canReach` function to keep track of all possible safest paths, or use a more advanced algorithm, such as a variant of Dijkstra's algorithm, to find all shortest paths.
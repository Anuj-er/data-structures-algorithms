<h2><a href="https://leetcode.com/problems/count-servers-that-communicate">1267. Count Servers that Communicate</a></h2>

<p>You are given a map of a server center, represented as a <code>m * n</code> integer matrix&nbsp;<code>grid</code>, where 1 means that on that cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on the same row or on the same column.<br />
<br />
Return the number of servers&nbsp;that communicate with any other server.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/11/14/untitled-diagram-6.jpg" style="width: 202px; height: 203px;" /></p>

<pre>
<strong>Input:</strong> grid = [[1,0],[0,1]]
<strong>Output:</strong> 0
<b>Explanation:</b>&nbsp;No servers can communicate with others.</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2019/11/13/untitled-diagram-4.jpg" style="width: 203px; height: 203px;" /></strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0],[1,1]]
<strong>Output:</strong> 3
<b>Explanation:</b>&nbsp;All three servers can communicate with at least one other server.
</pre>

<p><strong class="example">Example 3:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/11/14/untitled-diagram-1-3.jpg" style="width: 443px; height: 443px;" /></p>

<pre>
<strong>Input:</strong> grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
<strong>Output:</strong> 4
<b>Explanation:</b>&nbsp;The two servers in the first row can communicate with each other. The two servers in the third column can communicate with each other. The server at right bottom corner can&#39;t communicate with any other server.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m &lt;= 250</code></li>
	<li><code>1 &lt;= n &lt;= 250</code></li>
	<li><code>grid[i][j] == 0 or 1</code></li>
</ul>


---

# 🛍️ Count-Servers-that-Communicate | Explained

## Approach 1: Row‑&‑Column Count Scan  

### Intuition  
Think of the grid as a city map where each `1` marks a server building.  
A server can **communicate** with another server if they share the same street (row) **or** the same avenue (column).  
If a street (row) contains at least two servers, every server on that street can talk to at least one neighbor.  
Similarly, if an avenue (column) holds at least two servers, every server on that column can talk to a neighbor.  
Therefore, a server is *counted* when **either** its row **or** its column has more than one server.  
The algorithm first tallies how many servers live on each row and each column, then makes a single pass to see which individual cells satisfy the “row + column > 2” condition (the `+2` comes from the server itself being counted twice).

### Algorithm Visualized
```mermaid
flowchart TD
    A[Start] --> B[Read grid dimensions m,n]
    B --> C[Initialize rows[m] and cols[n] to 0]
    C --> D[First pass: for each cell (i,j) if grid[i][j]==1 add to rows[i] & cols[j]]
    D --> E[Second pass: for each cell (i,j) if grid[i][j]==1 && rows[i]+cols[j] > 2 then ans++]
    E --> F[Return ans]
    F --> G[End]
```

### Approach  
1. **Dimension extraction** – `m` = number of rows, `n` = number of columns.  
2. **Counting phase** – Traverse the entire matrix once, accumulating the count of servers per row (`rows[i]`) and per column (`cols[j]`).  
3. **Evaluation phase** – Traverse the matrix a second time. For each server (`grid[i][j] == 1`) check whether the sum `rows[i] + cols[j]` exceeds `2`.  
   *Why `> 2`?* Because the server itself contributes `1` to its row count **and** `1` to its column count. If there is *any* other server sharing either its row or column, the sum becomes at least `3`.  
4. **Answer accumulation** – Increment `ans` for every server that meets the condition.  
5. **Return** the final count.

### Detailed Code Analysis  
| Line | Code | Explanation |
|------|------|-------------|
| 1 | `class Solution {` | Standard LeetCode wrapper class. |
| 2 | `public int countServers(int[][] grid) {` | Method signature: receives a 2‑D int array (`0`/`1`) and returns the number of communicating servers. |
| 3 | `int m = grid.length, n = grid[0].length;` | `m` = rows, `n` = columns. Assumes `grid` is non‑empty (guaranteed by problem constraints). |
| 4 | `int[] rows = new int[m], cols = new int[n];  // servers on each row and column` | Two auxiliary arrays: `rows[i]` will hold the total `1`s in row `i`; `cols[j]` will hold the total `1`s in column `j`. Initialized to `0`. |
| 5‑9 | `for (int i = 0; i < m; i++)`<br>`    for (int j = 0; j < n; j++) {`<br>`        rows[i] += grid[i][j];`<br>`        cols[j] += grid[i][j];`<br>`    }` | **First pass** – Double loop over every cell. Because `grid[i][j]` is either `0` or `1`, adding it directly increments the appropriate counters. This runs in `O(m·n)` time. |
| 11 | `int ans = 0;` | Counter for servers that can communicate. |
| 12‑15 | `for (int i = 0; i < m; i++)`<br>`    for (int j = 0; j < n; j++)`<br>`        if (grid[i][j] == 1 && rows[i] + cols[j] > 2)  // it's counted in both, so more than 2 means company`<br>`            ans++;` | **Second pass** – Again scan the whole matrix. For each server (`grid[i][j] == 1`) we test the condition: <br>• `rows[i]` counts the server itself plus any other servers on the same row.<br>• `cols[j]` counts the server itself plus any other servers on the same column.<br>If there is **any** other server sharing either dimension, the sum becomes at least `3`. The comment clarifies that `> 2` guarantees at least one partner. When true, increment `ans`. |
| 16 | `return ans;` | Output the final count. |
| 17 | `}` | Close method. |
| 18 | `}` | Close class. |

**Why the two‑pass design?**  
- The first pass gathers global statistics (`rows` and `cols`) without needing extra memory per cell.  
- The second pass uses those pre‑computed statistics to decide in **O(1)** per cell whether the server is isolated.  
Attempting to decide on the fly (single pass) would require maintaining a dynamic view of future rows/columns, complicating the logic and potentially increasing time.

### Code
```java
class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] rows = new int[m], cols = new int[n];  // servers on each row and column
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                rows[i] += grid[i][j];
                cols[j] += grid[i][j];
            }

        int ans = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1 && rows[i] + cols[j] > 2)  // it's counted in both, so more than 2 means company
                    ans++;
        return ans;
    }
}
```

### Complexity  
- **Time:** `O(m * n)` – two full traversals of the matrix; each cell is visited exactly twice.  
- **Space:** `O(m + n)` – two auxiliary integer arrays storing row and column counts. No additional data structures proportional to `m * n`.

## 🕵️‍♂️ Follow‑up Questions (Optional)

| Question | Typical Answer |
|----------|----------------|
| *Can we solve it in a single pass?* | Yes, by using a hashmap or by first marking rows/columns with ≥2 servers, then a second pass to count. However, a true single‑pass solution would still need to store either the entire row/column state or defer counting, which usually ends up with the same asymptotic space. |
| *What if the grid is extremely sparse (few servers)?* | A space‑optimized variant could store only positions of `1`s in a list, then use a hash map to count per row/column, achieving `O(k)` time where `k` is the number of servers, while still using `O(k)` extra space. |
| *Why not use `rows[i] > 1 || cols[j] > 1` instead of `rows[i] + cols[j] > 2`?* | Both are logically equivalent because the server itself contributes `1` to each count. The sum check is a concise way to capture the “either row or column has another server” condition in a single expression. |
| *How would you adapt the solution for a mutable grid (servers added/removed online)?* | Maintain the `rows` and `cols` arrays incrementally: when a server is added, increment its row and column counters; when removed, decrement. Querying the count of communicating servers would then require recomputing the answer or maintaining a running total with careful updates. |
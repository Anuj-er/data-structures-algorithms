<h2><a href="https://leetcode.com/problems/path-existence-queries-in-a-graph-ii">3534. Path Existence Queries in a Graph II</a></h2>

<p>You are given an integer <code>n</code> representing the number of nodes in a graph, labeled from 0 to <code>n - 1</code>.</p>

<p>You are also given an integer array <code>nums</code> of length <code>n</code> and an integer <code>maxDiff</code>.</p>

<p>An <strong>undirected </strong>edge exists between nodes <code>i</code> and <code>j</code> if the <strong>absolute</strong> difference between <code>nums[i]</code> and <code>nums[j]</code> is <strong>at most</strong> <code>maxDiff</code> (i.e., <code>|nums[i] - nums[j]| &lt;= maxDiff</code>).</p>

<p>You are also given a 2D integer array <code>queries</code>. For each <code>queries[i] = [u<sub>i</sub>, v<sub>i</sub>]</code>, find the <strong>minimum</strong> distance between nodes <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code><sub>.</sub> If no path exists between the two nodes, return -1 for that query.</p>

<p>Return an array <code>answer</code>, where <code>answer[i]</code> is the result of the <code>i<sup>th</sup></code> query.</p>

<p><strong>Note:</strong> The edges between the nodes are unweighted.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, nums = [1,8,3,4,2], maxDiff = 3, queries = [[0,3],[2,4]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The resulting graph is:</p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2025/03/25/4149example1drawio.png" style="width: 281px; height: 161px;"></p>

<table><tbody><tr><th>Query</th><th>Shortest Path</th><th>Minimum Distance</th></tr><tr><td>[0, 3]</td><td>0 → 3</td><td>1</td></tr><tr><td>[2, 4]</td><td>2 → 4</td><td>1</td></tr></tbody></table>

<p>Thus, the output is <code>[1, 1]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 5, nums = [5,3,1,9,10], maxDiff = 2, queries = [[0,1],[0,2],[2,3],[4,3]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[1,2,-1,1]</span></p>

<p><strong>Explanation:</strong></p>

<p>The resulting graph is:</p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2025/03/25/4149example2drawio.png" style="width: 281px; height: 121px;"></p>
</div>

<table><tbody><tr><th>Query</th><th>Shortest Path</th><th>Minimum Distance</th></tr><tr><td>[0, 1]</td><td>0 → 1</td><td>1</td></tr><tr><td>[0, 2]</td><td>0 → 1 → 2</td><td>2</td></tr><tr><td>[2, 3]</td><td>None</td><td>-1</td></tr><tr><td>[4, 3]</td><td>3 → 4</td><td>1</td></tr></tbody></table>

<p>Thus, the output is <code>[1, 2, -1, 1]</code>.</p>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, nums = [3,6,1], maxDiff = 1, queries = [[0,0],[0,1],[1,2]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[0,-1,-1]</span></p>

<p><strong>Explanation:</strong></p>

<p>There are no edges between any two nodes because:</p>

<ul>
	<li>Nodes 0 and 1: <code>|nums[0] - nums[1]| = |3 - 6| = 3 &gt; 1</code></li>
	<li>Nodes 0 and 2: <code>|nums[0] - nums[2]| = |3 - 1| = 2 &gt; 1</code></li>
	<li>Nodes 1 and 2: <code>|nums[1] - nums[2]| = |6 - 1| = 5 &gt; 1</code></li>
</ul>

<p>Thus, no node can reach any other node, and the output is <code>[0, -1, -1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= maxDiff &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= queries.length &lt;= 10<sup>5</sup></code></li>
	<li><code>queries[i] == [u<sub>i</sub>, v<sub>i</sub>]</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
</ul>


---

# 🛍️ Path-Existence-Queries-in-a-Graph-II | Explained

## Approach 1 (Optimized)
### Intuition
The core idea behind this approach is to first sort the input numbers while maintaining their original indices. This is similar to creating a map of a city, where each number is a location, and the index is the actual street address. By sorting these numbers, we can efficiently determine the range of indices that satisfy a certain condition (i.e., the difference between the numbers is within a maximum allowed difference). The approach then uses a sparse table to precompute the farthest reachable index for each number, which allows for fast query answering. This is analogous to creating a highway system that connects each location to its farthest reachable destination within the allowed difference, enabling us to efficiently determine if a path exists between two locations.

### Approach
The high-level approach can be broken down into the following steps:

1. Sort the input numbers while maintaining their original indices.
2. Create a sparse table to precompute the farthest reachable index for each number.
3. Answer each query by using the sparse table to determine if a path exists between the two input indices.

### Detailed Code Analysis
Let's dive into the code block:

*   Lines 3-6: `newNums[i] = new int[] { nums[i], i };` This creates a new array `newNums` where each element is an array containing the number and its original index. This allows us to sort the numbers while maintaining their original indices.
*   Lines 8-9: `Arrays.sort(newNums, (a, b) -> a[0] - b[0]);` This sorts the `newNums` array based on the numbers. This is the first step in creating our map of the city, where each number is a location, and the index is the street address.
*   Lines 10-13: `getI[newNums[i][1]] = i;` This creates an array `getI` that maps the original index to the new index in the sorted array. This is like creating a mapping between the street address and the location in our map.
*   Lines 15-28: The code creates a sparse table `st` where `st[i][0]` represents the farthest reachable index from `i` within the allowed difference. This is like building the highway system that connects each location to its farthest reachable destination.
*   Lines 30-34: The code fills in the rest of the sparse table using the previously computed values. This is like extending the highway system to cover all possible distances.
*   Lines 36-67: The code answers each query by using the sparse table to determine if a path exists between the two input indices.

### Code
```java
int[][] newNums = new int[n][2];
for (int i = 0; i < n; i++) {
    newNums[i] = new int[] { nums[i], i };
}
Arrays.sort(newNums, (a, b) -> a[0] - b[0]);

int[] getI = new int[n];
for (int i = 0; i < n; i++) {
    getI[newNums[i][1]] = i;
}

int[][] st = new int[n][18];
int r = 0;

for (int i = 0; i < n; i++) {
    if (r < i) r = i;

    while (r + 1 < n &&
            newNums[r + 1][0] - newNums[r][0] <= maxDiff &&
            newNums[r + 1][0] - newNums[i][0] <= maxDiff) {
        r++;
    }

    st[i][0] = r;
}

for (int j = 1; j < 18; j++) {
    for (int i = 0; i < n; i++) {
        st[i][j] = st[st[i][j - 1]][j - 1];
    }
}

int[] ans = new int[queries.length];

for (int i = 0; i < queries.length; i++) {
    int a = getI[queries[i][0]];
    int b = getI[queries[i][1]];

    if (a > b) {
        int t = a;
        a = b;
        b = t;
    }

    if (a == b) {
        ans[i] = 0;
        continue;
    }

    int curr = a;
    int steps = 0;

    for (int j = 17; j >= 0; j--) {
        if (st[curr][j] < b) {
            curr = st[curr][j];
            steps += 1 << j;
        }
    }

    ans[i] = (st[curr][0] >= b) ? steps + 1 : -1;
}
```

### Complexity
*   Time: The time complexity is O(n log n + n log(maxDiff) + q log n), where n is the number of elements in the input array, maxDiff is the maximum allowed difference, and q is the number of queries. This is because we first sort the input array, then build the sparse table, and finally answer each query.
*   Space: The space complexity is O(n log n), which is used to store the sparse table and other auxiliary arrays.

## 🕵️‍♂️ Follow-up Questions (Optional)
Some potential follow-up questions for this problem are:

*   How would you handle the case where the input array is empty or contains duplicate numbers?
*   Can you optimize the solution to reduce the time complexity for large input arrays?
*   How would you extend this solution to handle queries for paths between multiple indices, rather than just two indices?
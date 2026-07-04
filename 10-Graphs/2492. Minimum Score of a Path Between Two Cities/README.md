<h2><a href="https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities">2492. Minimum Score of a Path Between Two Cities</a></h2>

<p>You are given a positive integer <code>n</code> representing <code>n</code> cities numbered from <code>1</code> to <code>n</code>. You are also given a <strong>2D</strong> array <code>roads</code> where <code>roads[i] = [a<sub>i</sub>, b<sub>i</sub>, distance<sub>i</sub>]</code> indicates that there is a <strong>bidirectional </strong>road between cities <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code> with a distance equal to <code>distance<sub>i</sub></code>. The cities graph is not necessarily connected.</p>

<p>The <strong>score</strong> of a path between two cities is defined as the <strong>minimum </strong>distance of a road in this path.</p>

<p>Return <em>the <strong>minimum </strong>possible score of a path between cities </em><code>1</code><em> and </em><code>n</code>.</p>

<p><strong>Note</strong>:</p>

<ul>
	<li>A path is a sequence of roads between two cities.</li>
	<li>It is allowed for a path to contain the same road <strong>multiple</strong> times, and you can visit cities <code>1</code> and <code>n</code> multiple times along the path.</li>
	<li>The test cases are generated such that there is <strong>at least</strong> one path between <code>1</code> and <code>n</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/10/12/graph11.png" style="width: 190px; height: 231px;">
<pre><strong>Input:</strong> n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> The path from city 1 to 4 with the minimum score is: 1 -&gt; 2 -&gt; 4. The score of this path is min(9,5) = 5.
It can be shown that no other path has less score.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2022/10/12/graph22.png" style="width: 190px; height: 231px;">
<pre><strong>Input:</strong> n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The path from city 1 to 4 with the minimum score is: 1 -&gt; 2 -&gt; 1 -&gt; 3 -&gt; 4. The score of this path is min(2,2,4,7) = 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= roads.length &lt;= 10<sup>5</sup></code></li>
	<li><code>roads[i].length == 3</code></li>
	<li><code>1 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li><code>1 &lt;= distance<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>There are no repeated edges.</li>
	<li>There is at least one path between <code>1</code> and <code>n</code>.</li>
</ul>


---

# 🛍️ Minimum-Score-of-a-Path-Between-Two-Cities | Explained

## Approach 1 (Optimized)
### Intuition
Imagine you are a delivery truck driver, and your goal is to find the path with the least weight limit between two cities in a network of roads. However, in this problem, we don't actually need to find the path; we just need the minimum weight. The key insight here is that the problem doesn't require us to construct the actual path, but rather just find the smallest edge weight in a connected component of the graph that includes both cities. However, this intuition doesn't directly apply to the provided code because it doesn't check for connectivity between the two cities. The code simply finds the minimum weight edge in the graph, which turns out to be sufficient for this problem because it guarantees that there is a path between the two cities.

### Approach
1. Construct an adjacency list representation of the graph from the given road network.
2. Perform a depth-first search (DFS) traversal of the graph, starting from one of the cities (in this case, city 0).
3. During the traversal, keep track of the minimum weight edge encountered.

### Detailed Code Analysis
The code uses an adjacency list representation of the graph, where each city is represented by an index, and the roads are represented as lists of integer arrays, where each array contains the destination city and the weight of the road. 

- The `min` variable is initialized to `Integer.MAX_VALUE` to ensure that any weight will be smaller than it initially.
- The `vis` array is used to keep track of visited cities during the DFS traversal.
- In the `dfs` method, the current city is marked as visited, and then for each neighboring city that hasn't been visited yet, the method calls itself recursively.
- The `min` variable is updated whenever a smaller weight is found.
- The `minScore` method constructs the graph, initializes the `vis` array, and calls the `dfs` method starting from city 0.
- The final minimum weight is returned as the result.

### Code
```java
private void dfs(int curr, List<List<int[]>> graph){
    vis[curr] = true;
    for(int[] edge: graph.get(curr)){
        int next = edge[0];
        int weight = edge[1];
        min = Math.min(min,weight);
        if(!vis[next]) dfs(next,graph);
    }
}

public int minScore(int n, int[][] roads) {
    vis = new boolean[n];
    List<List<int[]>> graph = new ArrayList<>();
    for(int i = 0;i<n;i++) graph.add(new ArrayList<>());
    for(int[] r : roads){
        int u = r[0] - 1;
        int v = r[1] - 1;
        int w = r[2];
        graph.get(u).add(new int[]{v,w});
        graph.get(v).add(new int[]{u,w});
    }
    dfs(0,graph);
    return min;
}
```

### Complexity
- Time: O(n + m), where n is the number of cities and m is the number of roads, because we visit each city and road once during the DFS traversal.
- Space: O(n + m), because we need to store the adjacency list representation of the graph, and the recursion stack can go up to a depth of n in the worst case.

## Approach 2 (Simple)
### Intuition
The problem can be solved by simply finding the minimum weight edge in the graph, without considering the connectivity between the two cities. This approach works because the problem statement guarantees that there is a path between the two cities.

### Approach
1. Iterate through all the roads in the graph.
2. Keep track of the minimum weight edge encountered.

### Detailed Code Analysis
This approach is simpler than the previous one because it doesn't require constructing the graph or performing a DFS traversal. It simply iterates through all the roads and keeps track of the minimum weight.

- The `min` variable is initialized to `Integer.MAX_VALUE` to ensure that any weight will be smaller than it initially.
- The code then iterates through all the roads, and for each road, it updates the `min` variable if the current weight is smaller.

### Code
```java
public int minScore(int n, int[][] roads) {
    int min = Integer.MAX_VALUE;
    for(int[] road : roads){
        min = Math.min(min,road[2]);
    }
    return min;
}
```

### Complexity
- Time: O(m), where m is the number of roads, because we iterate through all the roads once.
- Space: O(1), because we only use a constant amount of space to store the minimum weight.

## 🕵️‍♂️ Follow-up Questions (Optional)
What if the graph is not guaranteed to be connected? How would you modify the solution to handle this case?

Answer: In this case, we would need to use a disjoint set data structure to keep track of the connected components in the graph. We would then perform a DFS traversal starting from one of the cities, and check if the other city is in the same connected component. If it is, we can proceed with the original solution. If not, we would need to find the minimum weight edge in the connected component that includes the starting city.

What if the graph is weighted and directed? How would you modify the solution to handle this case?

Answer: In this case, we would need to use a different data structure to represent the graph, such as an adjacency list with directed edges. We would then need to modify the DFS traversal to take into account the direction of the edges. The rest of the solution would remain the same.
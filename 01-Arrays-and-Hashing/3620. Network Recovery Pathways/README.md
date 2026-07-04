<h2><a href="https://leetcode.com/problems/network-recovery-pathways">3620. Network Recovery Pathways</a></h2>

<p data-end="502" data-start="75">You are given a directed acyclic graph of <code>n</code> nodes numbered from 0 to <code>n − 1</code>. This is represented by a 2D array <code data-end="201" data-start="194">edges</code> of length<font face="monospace"> <code>m</code></font>, where <code data-end="255" data-start="227">edges[i] = [u<sub>i</sub>, v<sub>i</sub>, cost<sub>i</sub>]</code> indicates a one‑way communication from node <code data-end="304" data-start="300">u<sub>i</sub></code> to node <code data-end="317" data-start="313">v<sub>i</sub></code> with a recovery cost of <code data-end="349" data-start="342">cost<sub>i</sub></code>.</p>

<p data-end="502" data-start="75">Some nodes may be offline. You are given a boolean array <code data-end="416" data-start="408">online</code> where <code data-end="441" data-start="423">online[i] = true</code> means node <code data-end="456" data-start="453">i</code> is online. Nodes 0 and <code>n − 1</code> are always online.</p>

<p data-end="547" data-start="504">A path from 0 to <code>n − 1</code> is <strong data-end="541" data-start="532">valid</strong> if:</p>

<ul>
	<li>All intermediate nodes on the path are online.</li>
	<li data-end="676" data-start="605">The total recovery cost of all edges on the path does not exceed <code>k</code>.</li>
</ul>

<p data-end="771" data-start="653">For each valid path, define its <strong data-end="694" data-start="685">score</strong> as the minimum edge‑cost along that path.</p>

<p data-end="913" data-start="847">Return the <strong>maximum</strong> path score (i.e., the largest <strong>minimum</strong>-edge cost) among all valid paths. If no valid path exists, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]], online = [true,true,true,true], k = 10</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2025/06/06/graph-10.png" style="width: 239px; height: 267px;"></p>

<ul data-end="551" data-start="146">
	<li data-end="462" data-start="146">
	<p data-end="206" data-start="148">The graph has two possible routes from node 0 to node 3:</p>

	<ol data-end="462" data-start="209">
		<li data-end="315" data-start="209">
		<p data-end="228" data-start="212">Path <code>0 → 1 → 3</code></p>

		<ul data-end="315" data-start="234">
			<li data-end="315" data-start="234">
			<p data-end="315" data-start="236">Total cost = <code>5 + 10 = 15</code>, which exceeds k (<code>15 &gt; 10</code>), so this path is invalid.</p>
			</li>
		</ul>
		</li>
		<li data-end="462" data-start="318">
		<p data-end="337" data-start="321">Path <code>0 → 2 → 3</code></p>

		<ul data-end="462" data-start="343">
			<li data-end="397" data-start="343">
			<p data-end="397" data-start="345">Total cost = <code>3 + 4 = 7 &lt;= k</code>, so this path is valid.</p>
			</li>
			<li data-end="462" data-start="403">
			<p data-end="462" data-start="405">The minimum edge‐cost along this path is <code>min(3, 4) = 3</code>.</p>
			</li>
		</ul>
		</li>
	</ol>
	</li>
	<li data-end="551" data-start="463">
	<p data-end="551" data-start="465">There are no other valid paths. Hence, the maximum among all valid path‐scores is 3.</p>
	</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">edges = [[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]], online = [true,true,true,false,true], k = 12</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2025/06/06/graph-11.png" style="width: 343px; height: 194px;"></p>

<ul>
	<li data-end="790" data-start="726">
	<p data-end="790" data-start="728">Node 3 is offline, so any path passing through 3 is invalid.</p>
	</li>
	<li data-end="1231" data-start="791">
	<p data-end="837" data-start="793">Consider the remaining routes from 0 to 4:</p>

	<ol data-end="1231" data-start="840">
		<li data-end="985" data-start="840">
		<p data-end="859" data-start="843">Path <code>0 → 1 → 4</code></p>

		<ul data-end="985" data-start="865">
			<li data-end="920" data-start="865">
			<p data-end="920" data-start="867">Total cost = <code>7 + 5 = 12 &lt;= k</code>, so this path is valid.</p>
			</li>
			<li data-end="985" data-start="926">
			<p data-end="985" data-start="928">The minimum edge‐cost along this path is <code>min(7, 5) = 5</code>.</p>
			</li>
		</ul>
		</li>
		<li data-end="1083" data-start="988">
		<p data-end="1011" data-start="991">Path <code>0 → 2 → 3 → 4</code></p>

		<ul data-end="1083" data-start="1017">
			<li data-end="1083" data-start="1017">
			<p data-end="1083" data-start="1019">Node 3 is offline, so this path is invalid regardless of cost.</p>
			</li>
		</ul>
		</li>
		<li data-end="1231" data-start="1086">
		<p data-end="1105" data-start="1089">Path <code>0 → 2 → 4</code></p>

		<ul data-end="1231" data-start="1111">
			<li data-end="1166" data-start="1111">
			<p data-end="1166" data-start="1113">Total cost = <code>6 + 6 = 12 &lt;= k</code>, so this path is valid.</p>
			</li>
			<li data-end="1231" data-start="1172">
			<p data-end="1231" data-start="1174">The minimum edge‐cost along this path is <code>min(6, 6) = 6</code>.</p>
			</li>
		</ul>
		</li>
	</ol>
	</li>
	<li data-end="1314" data-is-last-node="" data-start="1232">
	<p data-end="1314" data-is-last-node="" data-start="1234">Among the two valid paths, their scores are 5 and 6. Therefore, the answer is 6.</p>
	</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li data-end="42" data-start="20"><code data-end="40" data-start="20">n == online.length</code></li>
	<li data-end="63" data-start="45"><code data-end="61" data-start="45">2 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li data-end="102" data-start="66"><code data-end="100" data-start="66">0 &lt;= m == edges.length &lt;= </code><code>min(10<sup>5</sup>, n * (n - 1) / 2)</code></li>
	<li data-end="102" data-start="66"><code data-end="127" data-start="105">edges[i] = [u<sub>i</sub>, v<sub>i</sub>, cost<sub>i</sub>]</code></li>
	<li data-end="151" data-start="132"><code data-end="149" data-start="132">0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt; n</code></li>
	<li data-end="166" data-start="154"><code data-end="164" data-start="154">u<sub>i</sub> != v<sub>i</sub></code></li>
	<li data-end="191" data-start="169"><code data-end="189" data-start="169">0 &lt;= cost<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li data-end="213" data-start="194"><code data-end="211" data-start="194">0 &lt;= k &lt;= 5 * 10<sup>13</sup></code></li>
	<li data-end="309" data-start="216"><code data-end="227" data-start="216">online[i]</code> is either <code data-end="244" data-is-only-node="" data-start="238">true</code> or <code data-end="255" data-start="248">false</code>, and both <code data-end="277" data-start="266">online[0]</code> and <code data-end="295" data-start="282">online[n − 1]</code> are <code data-end="306" data-start="300">true</code>.</li>
	<li data-end="362" data-is-last-node="" data-start="312">The given graph is a directed acyclic graph.</li>
</ul>


---

# 🛍️ Network-Recovery-Pathways | Explained

## Approach 1 (Optimized Binary Search with Topological Sorting)
### Intuition
Imagine a network of nodes and edges with weights representing the strength of connection. The goal is to find the maximum weight that ensures a path from the starting node to the ending node while considering the constraints of online nodes and a given total weight limit. This approach works by first topologically sorting the nodes based on their dependencies (edges), then using binary search to iteratively narrow down the range of possible maximum weights. The intuition behind using binary search is to efficiently find the threshold at which the path becomes possible, leveraging the fact that if a path is possible for a given weight, it will also be possible for all higher weights.

### Approach
1. **Topological Sorting**: Create a directed acyclic graph (DAG) from the edges and use it to order the nodes such that for every edge (u, v), node u comes before v in the ordering. This is essential for ensuring that we process each node only after all its predecessors have been processed.
2. **Binary Search**: Perform a binary search over the range of possible maximum weights (from the minimum edge weight to the maximum edge weight). For each mid-weight, attempt to find a path from the start to the end node, considering only edges with weights greater than or equal to the mid-weight and taking into account the online status of the nodes.
3. **Dynamic Programming (DP)**: Within the binary search loop, use DP to efficiently calculate the shortest path to each node, considering the constraints. The DP state `dp[i]` represents the minimum weight needed to reach node `i` from the start node, adhering to the online node and edge weight constraints.

### Detailed Code Analysis
- **Initialization (Lines 5-13)**: The code initializes an adjacency list representation of the graph (`graph`) and calculates the in-degree of each node (`indegree`). This setup is crucial for the topological sorting step that follows.
- **Topological Sorting (Lines 15-30)**: A queue-based approach is used for topological sorting. Nodes with an in-degree of 0 are added to the queue, and as each node is processed, its successors' in-degrees are decremented. Once a successor's in-degree reaches 0, it is added to the queue, ensuring that all nodes are processed in a valid order.
- **Binary Search (Lines 32-71)**: The binary search iteratively adjusts the `left` and `right` bounds based on whether a path can be found within the given weight limit `k` for a specific mid-weight. This is the core of finding the maximum weight threshold.
- **DP within Binary Search (Lines 38-63)**: For each node `u` in the topological order, if `u` is online or is the start or end node, the code checks all its outgoing edges. If an edge's weight is greater than or equal to the current mid-weight and the destination node `v` is online (or is the end node), it updates `dp[v]` with the minimum of its current value and `dp[u] + w`, where `w` is the edge weight.

### Code
```java
ArrayList<int[]>[] graph = new ArrayList[n];
// ...
Queue<Integer> q = new ArrayDeque<>();
for (int i = 0; i < n; i++)
    if (indegree[i] == 0)
        q.offer(i);
// ...
while (left <= right) {
    int mid = left + (right - left) / 2;
    long INF = Long.MAX_VALUE / 4;
    long[] dp = new long[n];
    Arrays.fill(dp, INF);
    dp[0] = 0;
    for (int u : topo) {
        if (dp[u] == INF)
            continue;
        if (u != 0 && u != n - 1 && !online[u])
            continue;
        for (int[] edge : graph[u]) {
            int v = edge[0];
            int w = edge[1];
            if (w < mid)
                continue;
            if (v != n - 1 && !online[v])
                continue;
            dp[v] = Math.min(dp[v], dp[u] + w);
        }
    }
    if (dp[n - 1] <= k) {
        ans = mid;
        left = mid + 1;
    } else {
        right = mid - 1;
    }
}
```

### Complexity
- **Time**: The time complexity can be broken down into two parts: topological sorting (O(n + m)) and the binary search with DP (O(m * log(max_w))), where n is the number of nodes, m is the number of edges, and max_w is the maximum edge weight. Thus, the overall time complexity is O(m * log(max_w)) dominated by the binary search and DP loop.
- **Space**: The space complexity includes the space needed for the graph representation (O(n + m)), the in-degree array (O(n)), the queue for topological sorting (O(n)), and the DP array (O(n)). Therefore, the overall space complexity is O(n + m).

## 🕵️‍♂️ Follow-up Questions (Optional)
1. **Q**: How would you modify this solution to also consider the case where the network can have cycles, and we still want to find the maximum weight ensuring a path from start to end?
   - **A**: For cyclic graphs, a similar approach could be used but would require more complex handling to avoid infinite loops. This could involve using a more advanced algorithm like Bellman-Ford for the DP part, which can handle negative weight edges and detect negative cycles, or using a modified version of Dijkstra's algorithm that can handle cyclic graphs by maintaining a distance array that can be updated.
2. **Q**: How does the online status of nodes affect the pathfinding, and are there any optimizations for this aspect?
   - **A**: The online status of nodes is crucial as it restricts the possible paths. Optimizations could include preprocessing the graph to remove nodes that are never online, thereby reducing the size of the graph and the number of edges to consider. Additionally, using more advanced graph pruning techniques could further reduce the search space based on the online/offline pattern.
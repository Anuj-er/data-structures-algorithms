import java.util.*;

/**
 * Graph Theory - Complete Reference Guide
 * Topics Covered: Input Handling, Adjacency Matrix, Adjacency List, Weighted
 * Graphs
 */
class Main {

    // =============================================================================
    // 1. BASIC INPUT HANDLING FOR ARRAYS AND INTEGERS
    // =============================================================================

    static class BasicInputHandling {
        public static void demonstrateArrayInput() {
            Scanner sc = new Scanner(System.in);

            // Read first line as array: "1 2 3 4 5"
            String line = sc.hasNextLine() ? sc.nextLine().trim() : "";
            if (line.isEmpty()) {
                System.out.println("No array input provided");
                sc.close();
                return;
            }

            System.out.println("Array: " + line);
            String[] parts = line.split(" ");
            int[] arr = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                arr[i] = Integer.parseInt(parts[i]);
            }

            System.out.print("Parsed array: ");
            for (int x : arr)
                System.out.print(x + " ");
            System.out.println();

            // Read second line as an integer variable: "2"
            int second = 0;
            if (sc.hasNextLine()) {
                String s = sc.nextLine().trim();
                if (!s.isEmpty())
                    second = Integer.parseInt(s);
            } else if (sc.hasNextInt()) {
                second = sc.nextInt();
            }

            System.out.println("Second line value: " + second);
            sc.close();
        }
    }

    // =============================================================================
    // 2. GRAPH REPRESENTATION USING ADJACENCY MATRIX
    // =============================================================================

    static class GraphAdjacencyMatrix {
        public static void createUndirectedGraph() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter vertices and edges:");
            int vertices = sc.nextInt(); // number of vertices
            int edges = sc.nextInt(); // number of edges

            // Create adjacency matrix (vertices+1 to handle 1-indexed vertices)
            int[][] adjacencyMatrix = new int[vertices + 1][vertices + 1];

            System.out.println("Enter edges (u v format):");
            for (int i = 0; i < edges; i++) {
                int u = sc.nextInt(); // source vertex
                int v = sc.nextInt(); // destination vertex

                // For undirected graph, mark both directions
                adjacencyMatrix[u][v] = 1;
                adjacencyMatrix[v][u] = 1;
            }

            // Print adjacency matrix
            System.out.println("Adjacency Matrix:");
            for (int i = 1; i <= vertices; i++) {
                for (int j = 1; j <= vertices; j++) {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
                System.out.println();
            }
            sc.close();
        }
    }

    // =============================================================================
    // 3. GRAPH REPRESENTATION USING ADJACENCY LIST (UNWEIGHTED)
    // =============================================================================

    static class GraphAdjacencyList {
        public static void createDirectedGraph() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter vertices and edges:");
            int vertices = 5;
            int edges = 6;

            // Create adjacency list using ArrayList
            List<List<Integer>> adjacencyList = new ArrayList<>();

            // Initialize vertices+1 empty lists (to handle 1-indexed vertices)
            for (int i = 0; i <= vertices; i++) {
                adjacencyList.add(new ArrayList<Integer>());
            }

            System.out.println("Enter edges (u v format):");
            for (int i = 0; i < edges; i++) {
                int u = sc.nextInt(); // source vertex
                int v = sc.nextInt(); // destination vertex

                // Add edge u -> v (directed graph)
                adjacencyList.get(u).add(v);

                // For undirected graph, uncomment the next line:
                // adjacencyList.get(v).add(u);
            }

            System.out.println("Adjacency List: " + adjacencyList);
            sc.close();
        }
    }

    // =============================================================================
    // 4. WEIGHTED GRAPH USING ADJACENCY LIST WITH PAIR CLASS
    // =============================================================================

    static class WeightedGraph {
        // Pair class to store vertex and weight information
        static class Pair {
            int vertex; // destination vertex
            int weight; // edge weight

            Pair(int vertex, int weight) {
                this.vertex = vertex;
                this.weight = weight;
            }

            @Override
            public String toString() {
                return "(" + vertex + "," + weight + ")";
            }
        }

        public static void createWeightedUndirectedGraph() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter vertices and edges:");
            int vertices = 5;
            int edges = 6;

            // Create adjacency list for weighted graph
            List<List<Pair>> weightedAdjacencyList = new ArrayList<>();

            // Initialize vertices+1 empty lists
            for (int i = 0; i <= vertices; i++) {
                weightedAdjacencyList.add(new ArrayList<Pair>());
            }

            System.out.println("Enter edges (u v weight format):");
            for (int i = 0; i < edges; i++) {
                int u = sc.nextInt(); // source vertex
                int v = sc.nextInt(); // destination vertex
                int weight = sc.nextInt(); // edge weight

                // Add edge u -> v with weight (undirected graph)
                weightedAdjacencyList.get(u).add(new Pair(v, weight));
                weightedAdjacencyList.get(v).add(new Pair(u, weight));

                // For directed graph, comment out the second line above
            }

            // Print the weighted adjacency list
            System.out.println("Weighted Graph Adjacency List:");
            for (int i = 1; i <= vertices; i++) {
                System.out.print("Vertex " + i + ": ");
                System.out.println(weightedAdjacencyList.get(i));
            }

            sc.close();
        }
    }

    // =============================================================================
    // 5. BFS TRAVERSAL (BREADTH FIRST SEARCH)
    // =============================================================================

    static class BFSTraversal {
        // Function to return Breadth First Search Traversal of given graph
        // Works for both directed and undirected graphs
        public static ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
            ArrayList<Integer> bfsResult = new ArrayList<>();
            boolean[] visited = new boolean[adj.size()];
            Queue<Integer> queue = new LinkedList<>();

            // Handle disconnected components - traverse all nodes
            for (int i = 0; i < visited.length; i++) {
                if (visited[i] == false) {
                    // Start BFS from unvisited node
                    queue.add(i);
                    visited[i] = true;

                    // Process all nodes reachable from current component
                    while (!queue.isEmpty()) {
                        Integer currentNode = queue.poll();
                        bfsResult.add(currentNode);

                        // Visit all unvisited neighbors
                        for (Integer neighbor : adj.get(currentNode)) {
                            if (visited[neighbor] == false) {
                                visited[neighbor] = true;
                                queue.add(neighbor);
                            }
                        }
                    }
                }
            }
            return bfsResult;
        }

        public static void demonstrateBFS() {
            // Create a sample graph: 0-1-2-3 and 4 (disconnected)
            // 0
            // / \
            // 1 2
            // |
            // 3 4 (disconnected)

            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

            // Initialize adjacency list for 5 vertices (0 to 4)
            for (int i = 0; i < 5; i++) {
                adjList.add(new ArrayList<>());
            }

            // Add edges (undirected graph)
            // Edge 0-1
            adjList.get(0).add(1);
            adjList.get(1).add(0);

            // Edge 0-2
            adjList.get(0).add(2);
            adjList.get(2).add(0);

            // Edge 2-3
            adjList.get(2).add(3);
            adjList.get(3).add(2);

            // Node 4 is disconnected (no edges)

            System.out.println("Graph adjacency list: " + adjList);

            ArrayList<Integer> bfsTraversal = bfs(adjList);
            System.out.println("BFS Traversal: " + bfsTraversal);
            System.out.println("Expected: [0, 1, 2, 3, 4] or similar order");
        }
    }

    // =============================================================================
    // 6. DFS TRAVERSAL (DEPTH FIRST SEARCH)
    // =============================================================================

    static class DFSTraversal {
        // Recursive helper function for DFS traversal
        public static void dfsHelper(Integer node, ArrayList<ArrayList<Integer>> adj,
                ArrayList<Integer> result, boolean[] visited) {
            // Mark current node as visited
            visited[node] = true;
            result.add(node);

            // Recursively visit all unvisited neighbors
            for (Integer neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    dfsHelper(neighbor, adj, result, visited);
                }
            }
        }

        // Function to return a list containing the DFS traversal of the graph
        // Note: This version only traverses from node 0 (doesn't handle disconnected
        // components)
        public static ArrayList<Integer> dfsFromZero(ArrayList<ArrayList<Integer>> adj) {
            ArrayList<Integer> result = new ArrayList<>();
            boolean[] visited = new boolean[adj.size()];

            // Start DFS from node 0
            dfsHelper(0, adj, result, visited);

            return result;
        }

        // Enhanced version: DFS that handles disconnected components (Your Updated
        // Code)
        public static ArrayList<Integer> dfsComplete(ArrayList<ArrayList<Integer>> adj) {
            int adjSize = adj.size();
            ArrayList<Integer> result = new ArrayList<>();
            boolean[] visited = new boolean[adjSize];

            // Handle disconnected components - traverse all nodes
            for (int i = 0; i < adjSize; i++) {
                if (!visited[i]) {
                    // IMPORTANT: Call dfs(i, ...) not dfs(0, ...)
                    dfsHelper(i, adj, result, visited);
                }
            }

            return result;
        }

        public static void demonstrateDFS() {
            // Create a sample graph: 0-1-2-3 and 4 (disconnected)
            // 0
            // / \
            // 1 2
            // |
            // 3 4 (disconnected)

            ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

            // Initialize adjacency list for 5 vertices (0 to 4)
            for (int i = 0; i < 5; i++) {
                adjList.add(new ArrayList<>());
            }

            // Add edges (undirected graph)
            // Edge 0-1
            adjList.get(0).add(1);
            adjList.get(1).add(0);

            // Edge 0-2
            adjList.get(0).add(2);
            adjList.get(2).add(0);

            // Edge 2-3
            adjList.get(2).add(3);
            adjList.get(3).add(2);

            // Node 4 is disconnected (no edges)

            System.out.println("Graph adjacency list: " + adjList);

            // Test all three versions
            ArrayList<Integer> dfsFromZero = dfsFromZero(adjList);
            System.out.println("DFS from node 0: " + dfsFromZero);
            System.out.println("Expected: [0, 1, 2, 3] - node 4 not visited");

            ArrayList<Integer> dfsComplete = dfsComplete(adjList);
            System.out.println("DFS complete traversal: " + dfsComplete);
            System.out.println("Expected: [0, 1, 2, 3, 4] - all nodes visited");
        }
    }

    // =============================================================================
    // 7. CYCLE DETECTION (UNDIRECTED GRAPH) - BFS and DFS Approaches
    // =============================================================================

    static class CycleDetection {
        static class Pair {
            int node;
            int parent;

            Pair(int node, int parent) {
                this.node = node;
                this.parent = parent;
            }
        }

        static class Solution {
            public boolean bfs(int src, List<List<Integer>> adj, boolean[] vis) {
                vis[src] = true;
                Queue<Pair> q = new LinkedList<>();
                q.add(new Pair(src, -1));

                while (!q.isEmpty()) {
                    int node = q.peek().node;
                    int parent = q.peek().parent;
                    q.remove();

                    for (int neighbor : adj.get(node)) {
                        if (!vis[neighbor]) {
                            vis[neighbor] = true;
                            q.add(new Pair(neighbor, node));
                        } else if (parent != neighbor) {
                            return true;
                        }
                    }
                }

                return false;
            }

            public boolean dfs(int node, int parent, boolean[] vis, List<List<Integer>> adj) {
                vis[node] = true;
                for (Integer neighbor : adj.get(node)) {
                    if (!vis[neighbor]) {
                        if (dfs(neighbor, node, vis, adj) == true)
                            return true;
                    } else if (neighbor != parent)
                        return true;
                }
                return false;
            }

            public boolean isCycle(int V, int[][] edges) {
                List<List<Integer>> adj = new ArrayList<>();
                for (int i = 0; i < V; i++)
                    adj.add(new ArrayList<>());

                for (int[] edge : edges) {
                    int u = edge[0];
                    int v = edge[1];
                    adj.get(u).add(v);
                    adj.get(v).add(u);
                }

                boolean[] vis = new boolean[V];

                // BFS approach
                for (int i = 0; i < V; i++) {
                    if (!vis[i]) {
                        if (bfs(i, adj, vis))
                            return true;
                    }
                }

                // DFS approach
                // for (int i = 0; i < V; i++) {
                //     if (vis[i] == false) {
                //         if (dfs(i, -1, vis, adj) == true)
                //             return true;
                //     }
                // }

                return false;
            }
        }

        // Small demo to show usage
        public static void demonstrateIsCycle() {
            System.out.println("\n--- Cycle Detection Demo ---");

            // Example 1: Graph with a cycle
            int V1 = 5;
            int[][] edges1 = new int[][] {
                    { 0, 1 }, { 1, 2 }, { 2, 0 }, { 3, 4 }
            };

            // Example 2: Acyclic graph (forest)
            int V2 = 4;
            int[][] edges2 = new int[][] {
                    { 0, 1 }, { 1, 2 }, { 2, 3 }
            };

            Solution sol = new Solution();
            System.out.println("Graph 1 has cycle: " + sol.isCycle(V1, edges1) + " (expected: true)");
            System.out.println("Graph 2 has cycle: " + sol.isCycle(V2, edges2) + " (expected: false)");
        }
    }

    // =============================================================================
    // MAIN METHOD - CHOOSE WHICH EXAMPLE TO RUN
    // =============================================================================

    public static void main(String[] args) {
        System.out.println("=== Graph Theory Examples ===");
        System.out.println("1. Basic Input Handling");
        System.out.println("2. Adjacency Matrix (Undirected)");
        System.out.println("3. Adjacency List (Directed)");
        System.out.println("4. Weighted Graph (Undirected)");
        System.out.println();

        // Uncomment the method you want to test:

        // BasicInputHandling.demonstrateArrayInput();
        // GraphAdjacencyMatrix.createUndirectedGraph();
        // GraphAdjacencyList.createDirectedGraph();
        // WeightedGraph.createWeightedUndirectedGraph();
        // BFSTraversal.demonstrateBFS();
        DFSTraversal.demonstrateDFS();
        // Demonstrate cycle detection (undirected graph)
        CycleDetection.demonstrateIsCycle();

        /*
         * REVISION NOTES:
         * 
         * 1. ADJACENCY MATRIX:
         * - Space: O(V²), Time: O(1) for edge lookup
         * - Good for dense graphs, bad for sparse graphs
         * 
         * 2. ADJACENCY LIST:
         * - Space: O(V + E), Time: O(degree) for edge lookup
         * - Good for sparse graphs, memory efficient
         * 
         * 3. WEIGHTED GRAPHS:
         * - Use Pair class to store (vertex, weight)
         * - Essential for algorithms like Dijkstra, Prim's
         * 
         * 4. INPUT PATTERNS:
         * - First line: number of vertices and edges
         * - Next E lines: edge information (u v) or (u v weight)
         * 
         * 5. BFS TRAVERSAL:
         * - Time: O(V + E), Space: O(V)
         * - Uses Queue for level-by-level traversal
         * - Handles disconnected components with outer loop
         * - Works for both directed and undirected graphs
         * 
         * 6. DFS TRAVERSAL:
         * - Time: O(V + E), Space: O(V) for recursion stack
         * - Uses Recursion for depth-first exploration
         * - Two versions: from node 0 only, or complete traversal
         * - Explores as deep as possible before backtracking
         */
    }
}

char[][] test8 = {{'.','.','3','.','.','.','.','.','.'}, {'4','.','.','.','8','.','.','3','6'}, {'.','.','8','3','.','.','1','.','.'},  {'.','4','.','.','.','6','.','7','3'}, {'.','.','.','.','9','.','.','1','.'},  {'.','.','.','.','.','2','.','.','.'}, {'.','.','4','.','7','.','.','6','8'}, {'6','.','.','.','.','.','.','.','.'},  {'7','.','.','.','.','.','5','.','.'}};
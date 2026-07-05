<h2><a href="https://leetcode.com/problems/number-of-paths-with-max-score">1301. Number of Paths with Max Score</a></h2>

<p>You are given a square <code>board</code>&nbsp;of characters. You can move on the board starting at the bottom right square marked with the character&nbsp;<code>'S'</code>.</p>

<p>You need&nbsp;to reach the top left square marked with the character <code>'E'</code>. The rest of the squares are labeled either with a numeric character&nbsp;<code>1, 2, ..., 9</code> or with an obstacle <code>'X'</code>. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.</p>

<p>Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the second is the number of such paths that you can take to get that maximum sum, <strong>taken modulo <code>10^9 + 7</code></strong>.</p>

<p>In case there is no path, return&nbsp;<code>[0, 0]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> board = ["E23","2X2","12S"]
<strong>Output:</strong> [7,1]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> board = ["E12","1X1","21S"]
<strong>Output:</strong> [4,2]
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> board = ["E11","XXX","11S"]
<strong>Output:</strong> [0,0]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= board.length == board[i].length &lt;= 100</code></li>
</ul>

---

# 🛍️ Number-of-Paths-with-Max-Score | Explained

## Approach 1 (Dynamic Programming)
### Intuition
This approach uses dynamic programming to solve the problem. It starts by initializing the base case for the last row of the board, where the score is calculated based on the presence of 'S' or 'E' characters. Then, it iterates through each cell in the board from bottom to top, updating the maximum score and the number of paths that achieve this score. This approach works because it uses a bottom-up dynamic programming strategy, where the solution to the problem depends on the solutions to smaller sub-problems.

### Approach
The algorithm starts by initializing two arrays, `path` and `pathSum`, to keep track of the number of paths and the maximum score at each position. Then, it iterates through each cell in the board from bottom to top. For each cell, it calculates the maximum score by considering the scores from the bottom, bottom-right, and right cells. It then updates the `path` and `pathSum` arrays accordingly. Finally, it returns the maximum score and the number of paths that achieve this score.

### Detailed Code Analysis
The code starts by defining the `mod` variable, which is used to prevent overflow when calculating the number of paths. The `pathWithMaxScore` function initializes the `path` and `pathSum` arrays and sets the base case for the last row of the board.

```java
long mod = 1_000_000_007; 
int m = board.size(); 
long[] path = new long[m + 1]; 
long[] pathSum = new long[m + 1]; 
```
The code then iterates through each cell in the last row of the board and sets the base case for the `path` and `pathSum` arrays.

```java
for(int i = m - 1; i >= 0; i--) {
    char c = board.get(m - 1).charAt(i); 
    if(c == 'X') {
        break; 
    }
    if(c == 'S') {
        path[i] = 1L; 
        continue; 
    }
    path[i] = 1L; 
    pathSum[i] = (pathSum[i + 1] + (c - '0')); 
}
```
The code then iterates through each cell in the board from bottom to top, updating the `path` and `pathSum` arrays accordingly.

```java
for(int i = m - 2; i >= 0; i--) {
    long[] curPath = new long[m + 1]; 
    long[] curPathSum = new long[m + 1]; 
    for(int j = m - 1; j>= 0; j--) {
        char c = board.get(i).charAt(j); 
        if(c == 'X') continue; 
        if(c != 'E') {
            curPathSum[j] = (c - '0'); 
        }
        long maxPathSum = Math.max(pathSum[j], Math.max(curPathSum[j + 1], pathSum[j + 1])); 
        curPathSum[j] = (curPathSum[j] + maxPathSum) % mod; 
        if(pathSum[j] == maxPathSum) {
            curPath[j] = (curPath[j] + path[j]) % mod; 
        }
        if(pathSum[j + 1] == maxPathSum) {
            curPath[j] = (curPath[j] + path[j + 1]) % mod; 
        }
        if(curPathSum[j + 1] == maxPathSum) {
            curPath[j] = (curPath[j] + curPath[j + 1]) % mod; 
        }
    }
    path = curPath; 
    pathSum = curPathSum; 
}
```
Finally, the code returns the maximum score and the number of paths that achieve this score.

```java
if(path[0] == 0) pathSum[0] = 0; 
return new int[] {(int)pathSum[0], (int)path[0]}; 
```
### Code
```java
public int[] pathsWithMaxScore(List<String> board) {
    // DP 
    int m = board.size(); 
    long[] path = new long[m + 1]; 
    long[] pathSum = new long[m + 1]; 

    for(int i = m - 1; i >= 0; i--) {
        char c = board.get(m - 1).charAt(i); 
        if(c == 'X') {
            break; 
        }
        if(c == 'S') {
            path[i] = 1L; 
            continue; 
        }
        path[i] = 1L; 
        pathSum[i] = (pathSum[i + 1] + (c - '0')); 
    }

    for(int i = m - 2; i >= 0; i--) {
        long[] curPath = new long[m + 1]; 
        long[] curPathSum = new long[m + 1]; 
        for(int j = m - 1; j>= 0; j--) {
            char c = board.get(i).charAt(j); 
            if(c == 'X') continue; 
            if(c != 'E') {
                curPathSum[j] = (c - '0'); 
            }
            long maxPathSum = Math.max(pathSum[j], Math.max(curPathSum[j + 1], pathSum[j + 1])); 
            curPathSum[j] = (curPathSum[j] + maxPathSum) % mod; 
            if(pathSum[j] == maxPathSum) {
                curPath[j] = (curPath[j] + path[j]) % mod; 
            }
            if(pathSum[j + 1] == maxPathSum) {
                curPath[j] = (curPath[j] + path[j + 1]) % mod; 
            }
            if(curPathSum[j + 1] == maxPathSum) {
                curPath[j] = (curPath[j] + curPath[j + 1]) % mod; 
            }
        }
        path = curPath; 
        pathSum = curPathSum; 
    }
    if(path[0] == 0) pathSum[0] = 0; 
    return new int[] {(int)pathSum[0], (int)path[0]}; 
}
```
### Complexity
- Time: O(m^2), where m is the size of the board. This is because the code has two nested loops that iterate through each cell in the board.
- Space: O(m), where m is the size of the board. This is because the code uses two arrays of size m to store the `path` and `pathSum` values.

## 🕵️‍♂️ Follow-up Questions (Optional)
1. **How would you handle the case where the input board is empty?** 
   You can add a check at the beginning of the function to return a default value, such as [0, 0], if the input board is empty.

2. **How would you optimize the code to handle large input boards?** 
   You can use more efficient data structures, such as arrays instead of lists, and optimize the loops to reduce the number of iterations. Additionally, you can use dynamic programming to store and reuse intermediate results to avoid redundant calculations.
1class Solution {
2    long mod = 1_000_000_007; 
3    public int[] pathsWithMaxScore(List<String> board) {
4        // DP 
5        int m = board.size(); 
6
7        long[] path = new long[m + 1]; // defautl value = 0 
8        long[] pathSum = new long[m + 1]; 
9
10        // base case 
11        for(int i = m - 1; i >= 0; i--) {
12            char c = board.get(m - 1).charAt(i); 
13            if(c == 'X') {
14                break; 
15            }
16
17            if(c == 'S') {
18                path[i] = 1L; 
19                continue; 
20            }
21
22            path[i] = 1L; 
23            pathSum[i] = (pathSum[i + 1] + (c - '0')); 
24        }
25
26        // traverse our board 
27        for(int i = m - 2; i >= 0; i--) {
28            long[] curPath = new long[m + 1]; 
29            long[] curPathSum = new long[m + 1]; 
30
31            for(int j = m - 1; j>= 0; j--) {
32                char c = board.get(i).charAt(j); 
33                if(c == 'X') continue; // no update to cur 
34                
35                if(c != 'E') {
36                    curPathSum[j] = (c - '0'); 
37                }
38
39                // bottom, bottom - right, right 
40                long maxPathSum = Math.max(pathSum[j], Math.max(curPathSum[j + 1], pathSum[j + 1])); 
41                curPathSum[j] = (curPathSum[j] + maxPathSum) % mod; 
42                // bottom 
43                if(pathSum[j] == maxPathSum) {
44                    curPath[j] = (curPath[j] + path[j]) % mod; 
45                }
46
47                // bottom-right 
48                if(pathSum[j + 1] == maxPathSum) {
49                    curPath[j] = (curPath[j] + path[j + 1]) % mod; 
50                }
51
52                // rigth 
53                if(curPathSum[j + 1] == maxPathSum) {
54                    curPath[j] = (curPath[j] + curPath[j + 1]) % mod; 
55                }
56            }
57            path = curPath; 
58            pathSum = curPathSum; 
59        }
60
61        // if the path[0] = 
62        if(path[0] == 0) pathSum[0] = 0; 
63        return new int[] {(int)pathSum[0], (int)path[0]}; 
64    }
65}
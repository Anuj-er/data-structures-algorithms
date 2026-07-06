1class Solution {
2    public int removeCoveredIntervals(int[][] intervals) {
3        Arrays.sort(intervals, (a, b) ->
4            a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]
5        );
6
7        int count = 0;
8        int maxEnd = 0;
9
10        for (int[] interval : intervals) {
11            if (interval[1] > maxEnd) {
12                count++;
13                maxEnd = interval[1];
14            }
15        }
16
17        return count;
18    }
19}
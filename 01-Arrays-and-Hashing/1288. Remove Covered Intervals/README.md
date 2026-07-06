<h2><a href="https://leetcode.com/problems/remove-covered-intervals">1288. Remove Covered Intervals</a></h2>

<p>Given an array <code>intervals</code> where <code>intervals[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> represent the interval <code>[l<sub>i</sub>, r<sub>i</sub>)</code>, remove all intervals that are covered by another interval in the list.</p>

<p>The interval <code>[a, b)</code> is covered by the interval <code>[c, d)</code> if and only if <code>c &lt;= a</code> and <code>b &lt;= d</code>.</p>

<p>Return <em>the number of remaining intervals</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> intervals = [[1,4],[3,6],[2,8]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Interval [3,6] is covered by [2,8], therefore it is removed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre><strong>Input:</strong> intervals = [[1,4],[2,3]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= intervals.length &lt;= 1000</code></li>
	<li><code>intervals[i].length == 2</code></li>
	<li><code>0 &lt;= l<sub>i</sub> &lt; r<sub>i</sub> &lt;= 10<sup>5</sup></code></li>
	<li>All the given intervals are <strong>unique</strong>.</li>
</ul>


---

# 🛍️ Remove-Covered-Intervals | Explained

## Approach 1: Sorting and Interval Comparison
### Intuition
Imagine you're organizing intervals on a number line, where each interval has a start and end point. To find the covered intervals, we can first sort these intervals based on their start points and then compare their end points. If an interval's start point is greater than or equal to the previous interval's start point and its end point is less than or equal to the previous interval's end point, it means the current interval is covered by the previous one. This approach is similar to categorizing items by their start points and then checking if they fit within a larger category.
### Approach
1. Create a custom sorting comparator to sort the intervals based on their start points and end points.
2. Initialize variables to keep track of the current start point and end point.
3. Iterate over the sorted intervals. If an interval's start point is within the current range (i.e., its start point is greater than or equal to the current start point) and its end point is within the current range (i.e., less than or equal to the current end point), it means the interval is covered, so increment the count of covered intervals.
4. If the interval is not covered, update the current start and end points to the current interval's start and end points.
5. Return the total number of intervals minus the count of covered intervals.
### Code
```cpp
class Solution {
public:
    struct comp {
        bool operator()(const vector<int>& a, const vector<int>& b) const {
            if (a[0] == b[0]) {
                return a[1] > b[1]; // descending by end
            }
            return a[0] < b[0]; // ascending by start
        }
    };

    int removeCoveredIntervals(vector<vector<int>>& intervals) {
        sort(intervals.begin(), intervals.end(), comp());

        int count = 0;
        int startPoint = intervals[0][0];
        int endPoint = intervals[0][1];

        for (int i = 1; i < intervals.size(); i++) {
            if (startPoint <= intervals[i][0] && intervals[i][1] <= endPoint) {
                count++;
            } else {
                startPoint = intervals[i][0];
                endPoint = intervals[i][1];
            }
        }

        return intervals.size() - count;
    }
};
```
### Complexity
- Time: O(n log n) due to the sorting operation, where n is the number of intervals.
- Space: O(n) for sorting in the worst case, depending on the implementation of the sorting algorithm.

## 🕵️‍♂️ Follow-up Questions (Optional)
1. Q: How would you optimize this solution for very large inputs?
A: To optimize the solution for very large inputs, consider using a more efficient sorting algorithm like quicksort or mergesort, and ensure the sorting comparator is efficient and doesn't lead to unnecessary comparisons. Additionally, consider using a data structure like a set or a map to keep track of unique intervals.
2. Q: How does this solution handle duplicate intervals?
A: This solution treats duplicate intervals as separate intervals because it checks for covered intervals based on both the start and end points. If two intervals are identical (i.e., same start and end points), the solution will count them as separate intervals, and if one is not covering the other (they are identical), it won't increment the count. However, if you want to ignore duplicate intervals, you can add a step to remove duplicates before sorting and counting covered intervals.
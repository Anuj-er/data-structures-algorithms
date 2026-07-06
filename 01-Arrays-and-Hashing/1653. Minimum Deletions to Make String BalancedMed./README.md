<h2><a href="https://leetcode.com/problems/remove-covered-intervals">1653. Minimum Deletions to Make String BalancedMed.</a></h2>

<p>Given an array <code>intervals</code> where <code>intervals[i] = [l<sub>i</sub>, r<sub>i</sub>]</code> represent the interval <code>[l<sub>i</sub>, r<sub>i</sub>)</code>, remove all intervals that are covered by another interval in the list.</p>

<p>The interval <code>[a, b)</code> is covered by the interval <code>[c, d)</code> if and only if <code>c &lt;= a</code> and <code>b &lt;= d</code>.</p>

<p>Return <em>the number of remaining intervals</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,4],[3,6],[2,8]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> Interval [3,6] is covered by [2,8], therefore it is removed.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> intervals = [[1,4],[2,3]]
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

# 🛍️ Minimum-Deletions-to-Make-String-BalancedMed. | Explained

## Approach 1 (Optimized)
### Intuition
The core idea behind this approach is to first sort the intervals based on their start value and in case of a tie, sort them in descending order of their end value. This ensures that if two intervals have the same start value, the one with the larger end value comes first. By doing this, we can iterate through the sorted intervals and count the number of non-covered intervals, which will give us the minimum number of deletions required to make the string balanced. The intuition is that by always choosing the interval with the larger end value when there are multiple intervals with the same start value, we minimize the number of deletions required.

### Approach
The high-level algorithmic breakdown is as follows:
1. Sort the intervals based on their start value and end value.
2. Initialize a variable to keep track of the maximum end value seen so far and a counter to keep track of the number of non-covered intervals.
3. Iterate through the sorted intervals and for each interval, check if its end value is greater than the maximum end value seen so far. If it is, increment the counter and update the maximum end value.

### Detailed Code Analysis
Let's break down the code block:
- The `Arrays.sort()` function is used to sort the intervals. The comparator function `(a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]` sorts the intervals based on their start value and in case of a tie, sorts them in descending order of their end value.
- The `int count = 0;` line initializes a variable to keep track of the number of non-covered intervals.
- The `int maxEnd = 0;` line initializes a variable to keep track of the maximum end value seen so far.
- The `for (int[] interval : intervals)` loop iterates through the sorted intervals.
- The `if (interval[1] > maxEnd)` condition checks if the current interval's end value is greater than the maximum end value seen so far.
- If the condition is true, the `count++` line increments the counter and the `maxEnd = interval[1];` line updates the maximum end value.

### Code
```java
class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) ->
            a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]
        );

        int count = 0;
        int maxEnd = 0;

        for (int[] interval : intervals) {
            if (interval[1] > maxEnd) {
                count++;
                maxEnd = interval[1];
            }
        }

        return count;
    }
}
```

### Complexity
- Time: The time complexity is O(n log n) due to the sorting operation, where n is the number of intervals. The subsequent for loop has a time complexity of O(n), but it is dominated by the sorting operation.
- Space: The space complexity is O(1) if we don't consider the space required for the input and output. The sorting operation is done in-place, and we only use a constant amount of space to store the variables.

## 🕵️‍♂️ Follow-up Questions (Optional)
Some common follow-up questions for this pattern could be:
- What if the input intervals are not valid (e.g., the start value is greater than the end value)? How would you handle such cases?
- Can you optimize the solution further? For example, can you use a different sorting algorithm or a different data structure to reduce the time complexity? 

Brief answers:
- To handle invalid input intervals, you could add a validation step before sorting the intervals. This step would check each interval and throw an exception or return an error if the start value is greater than the end value.
- The current solution has a time complexity of O(n log n) due to the sorting operation. This is the most efficient time complexity we can achieve for this problem, as we need to sort the intervals to ensure that the intervals with the same start value are processed in the correct order. However, we could consider using a different sorting algorithm, such as quicksort or mergesort, which could have a slightly better performance in practice.
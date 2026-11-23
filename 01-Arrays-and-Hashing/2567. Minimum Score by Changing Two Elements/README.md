<h2><a href="https://leetcode.com/problems/minimum-score-by-changing-two-elements">2567. Minimum Score by Changing Two Elements</a></h2>

<p>You are given an integer array <code>nums</code>.</p>

<ul>
	<li>The <strong>low</strong> score of <code>nums</code> is the <strong>minimum</strong> absolute difference between any two integers.</li>
	<li>The <strong>high</strong> score of <code>nums</code> is the <strong>maximum</strong> absolute difference between any two integers.</li>
	<li>The <strong>score</strong> of <code>nums</code> is the sum of the <strong>high</strong> and <strong>low</strong> scores.</li>
</ul>

<p>Return the <strong>minimum score</strong> after <strong>changing two elements</strong> of <code>nums</code>.</p>

<p>&nbsp;</p>
<p><span class="float-right -my-0.5 ml-2 inline-flex select-none items-center" data-example-lang-controls=""><span role="group" class="inline-flex h-6 items-center rounded-sd-md bg-sd-muted p-0.5" data-example-lang-toggle=""><button type="button" aria-pressed="true" class="h-5 max-w-[10rem] truncate whitespace-nowrap rounded-sd-sm border px-2 text-xs font-normal leading-none border-sd-border bg-sd-background text-sd-foreground">Original</button><button type="button" aria-pressed="false" class="h-5 max-w-[10rem] truncate whitespace-nowrap rounded-sd-sm border px-2 text-xs font-normal leading-none border-transparent text-sd-muted-foreground hover:text-sd-foreground">Java</button></span></span><strong class="example">Example 1:</strong></p>

<div class="example-block" bis_skin_checked="1">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,7,8,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Change <code>nums[0]</code> and <code>nums[1]</code> to be 6 so that <code>nums</code> becomes [6,6,7,8,5].</li>
	<li>The low score is the minimum absolute difference: |6 - 6| = 0.</li>
	<li>The high score is the maximum absolute difference: |8 - 5| = 3.</li>
	<li>The sum of high and low score is 3.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block" bis_skin_checked="1">
<p><strong>Input:</strong> <span class="example-io">nums = [1,4,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Change <code>nums[1]</code> and <code>nums[2]</code> to 1 so that <code>nums</code> becomes [1,1,1].</li>
	<li>The sum of maximum absolute difference and minimum absolute difference is 0.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>


---

# 🛍️ Minimum-Score-by-Changing-Two-Elements | Explained

## Approach 1: **Two‑Pass Minimum/Maximum Tracker**

### Intuition
Imagine you have a row of numbers and you are allowed to pick **exactly two positions** and replace their values with any integers you like.  
The *score* after the change equals  

\[
\text{score}= \max(\text{array}) - \min(\text{array})
\]

To make this difference as small as possible we would like the **new maximum** to be as low as possible **and** the **new minimum** to be as high as possible.  
Because we can set the two changed elements to any values, the optimal strategy is:

1. **Replace the current maximum** with a very small number (ideally the smallest number that already exists in the array).  
2. **Replace the current minimum** with a very large number (ideally the largest number that already exists in the array).

Consequently, after the two replacements the new maximum will be the **second‑largest** original element (or the largest if we also replace the largest) and the new minimum will be the **second‑smallest** original element (or the smallest if we also replace the smallest).  

The problem reduces to **finding the three smallest values and the three largest values** in one linear scan.  
Why three? Because we have to consider three possible pairings of the two changed elements:

* replace the largest with the smallest → new score = `secondLargest - smallest`
* replace the largest with the second smallest → new score = `largest - secondSmallest`
* replace the second largest with the smallest → new score = `secondLargest - smallest` (already covered)  

In fact, the formula used in the code evaluates the three candidate scores:

```
(-neg[0] - small[2])   // largest - thirdSmallest
(-neg[1] - small[1])   // secondLargest - secondSmallest
(-neg[2] - small[0])   // thirdLargest - smallest
```

The minimum among them is the answer.

### Algorithm Visualized
```mermaid
flowchart TD
    A[Start] --> B[Initialize small[3] = INF, neg[3] = INF]
    B --> C[For each x in nums]
    C --> D[keep(small, x)      // keep three smallest]
    D --> E[keep(neg, -x)       // keep three smallest of negated values → three largest]
    E --> C
    C --> F[Compute candidates:
            cand1 = -neg[0] - small[2]
            cand2 = -neg[1] - small[1]
            cand3 = -neg[2] - small[0]]
    F --> G[answer = min(cand1, cand2, cand3)]
    G --> H[Return answer]
    H --> I[End]
```

### Approach
1. **Prepare two fixed‑size containers** (`small` and `neg`) each of length 3, filled with `Integer.MAX_VALUE`.  
   * `small` will store the three smallest numbers seen so far in **ascending** order.  
   * `neg` will store the three smallest numbers of the *negated* array, i.e. the three most negative values of `-x`. After negation, `-neg[i]` equals the *i‑th largest* original value.
2. **Iterate once through `nums`.** For each element `x`:
   * Call `keep(small, x)` → inserts `x` into `small` if it belongs to the three smallest values, shifting larger values to the right.
   * Call `keep(neg, -x)` → does the same on the negated value, effectively tracking the three largest original values.
3. **After the scan** we have:
   * `small[0] ≤ small[1] ≤ small[2]` → 1st, 2nd, 3rd smallest.
   * `-neg[0] ≥ -neg[1] ≥ -neg[2]` → 1st, 2nd, 3rd largest.
4. **Compute three candidate scores** that correspond to the three ways we could replace two elements:
   * `-neg[0] - small[2]` → replace the current largest with the current smallest.
   * `-neg[1] - small[1]` → replace the current largest with the second smallest **or** replace the second largest with the smallest (both yield the same expression).
   * `-neg[2] - small[0]` → replace the second largest with the smallest.
5. **Return the minimum** of those three candidates – that is the smallest possible score after changing exactly two elements.

### Detailed Code Analysis
| Line(s) | Explanation |
|--------|-------------|
|`1 class Solution {`|Standard LeetCode class wrapper.|
|`2     public int minimizeSum(int[] nums) {`|Public method that receives the input array and must return the minimal possible score.|
|`3         int[] small = new int[3], neg = new int[3];`|Allocate two arrays of length 3. `small` will hold the three smallest values; `neg` will hold the three smallest *negated* values (i.e., the three largest original values).|
|`4         Arrays.fill(small, Integer.MAX_VALUE);`|Initialize every slot in `small` with the largest possible `int` so that any real number will be smaller and will be inserted by `keep`.|
|`5         Arrays.fill(neg, Integer.MAX_VALUE);`|Same initialization for `neg`.|
|`6         for (int x : nums) {`|Iterate over each element of the input array exactly once (O(n) time).|
|`7             keep(small, x);`|Attempt to insert `x` into `small`. If `x` is among the three smallest seen so far, `keep` will shift larger elements right and place `x` in its correct sorted position.|
|`8             keep(neg, -x);`|Insert the negated value `-x` into `neg`. Because we keep the three smallest *negatives*, after the loop `-neg[0]` is the **largest** original number, `-neg[1]` the second largest, and `-neg[2]` the third largest.|
|`9         }`|End of the linear scan.|
|`10        return Math.min(-neg[0] - small[2], Math.min(-neg[1] - small[1], -neg[2] - small[0]));`|Compute three possible scores (as described above) and return the smallest. The nested `Math.min` ensures we compare all three candidates.|
|`11    }`|Close `minimizeSum`.|
|`13    private void keep(int[] three, int x) {`|Helper that maintains an **ascending sorted** list of the three smallest numbers seen so far.|
|`14        if (x >= three[2])`|If `x` is not smaller than the current third‑smallest (`three[2]`), it cannot belong to the top‑3 smallest, so we skip insertion.|
|`15            return;`|Early exit when `x` is too large.|
|`16        int i = 2;`|Start from the rightmost index (the largest among the three stored).|
|`17        for (; i > 0 && three[i - 1] > x; i--)`|Shift elements right while the element to the left is larger than `x`. This loop moves larger values one position to the right, making space for `x`.|
|`18            three[i] = three[i - 1];`|Perform the actual shift.|
|`19        three[i] = x;`|Place `x` into its correct sorted slot (index `i`).|
|`20    }`|End of `keep`.|
|`21}`|End of class.|

**Why this works:**  
`keep` guarantees that after processing all numbers, `three[0] ≤ three[1] ≤ three[2]` and they are the three smallest values seen. By feeding `-x` into the same routine we effectively keep the three *largest* original values (because the smallest negatives correspond to the largest positives). The final formula then enumerates every distinct way of replacing two elements with extreme values and selects the best one.

### Code
```java
class Solution {
    public int minimizeSum(int[] nums) {
        int[] small = new int[3], neg = new int[3];  // three smallest values, and three smallest negated values (i.e., three largest)
        Arrays.fill(small, Integer.MAX_VALUE);
        Arrays.fill(neg, Integer.MAX_VALUE);
        for (int x : nums) {
            keep(small, x);      // track three smallest
            keep(neg, -x);       // track three largest via negation
        }
        return Math.min(-neg[0] - small[2],
                Math.min(-neg[1] - small[1], -neg[2] - small[0]));
    }

    private void keep(int[] three, int x) {  // maintains three smallest values in ascending order
        if (x >= three[2])
            return;
        int i = 2;
        for (; i > 0 && three[i - 1] > x; i--)
            three[i] = three[i - 1];
        three[i] = x;
    }
}
```

### Complexity
- **Time:**  
  *The loop over `nums` runs `n` times, and each call to `keep` does at most 3 assignments (constant work). Hence overall time is **O(n)**.*

- **Space:**  
  *Only two fixed‑size arrays of length 3 and a few scalar variables are allocated. This is **O(1)** extra space.*

## 🕵️‍♂️ Follow-up Questions (Optional)

1. **What if we were allowed to change only one element instead of two?**  
   *Answer:* We would only need the smallest and largest original values. The minimal score would be `Math.min(largest - secondSmallest, secondLargest - smallest)`. The algorithm can be adapted by keeping just two minima and two maxima (or three to handle edge cases).

2. **Can the solution be extended to “change up to k elements”?**  
   *Answer:* Yes. We would need to keep the `k+1` smallest and `k+1` largest values. The candidate scores become `largest_i - smallest_{k-i}` for `i = 0 … k`, and we take the minimum. The `keep` helper can be generalized to maintain the top `k+1` elements in O(k) per insertion, yielding O(n·k) time and O(k) space. For very large `k` a heap or quickselect could be used to achieve O(n) average time.
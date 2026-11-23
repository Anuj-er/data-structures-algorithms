<h2><a href="https://leetcode.com/problems/maximize-consecutive-elements-in-an-array-after-modification">3041. Maximize Consecutive Elements in an Array After Modification</a></h2>

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>Initially, you can increase the value of <strong>any</strong> element in the array by <strong>at most</strong> <code>1</code>.</p>

<p>After that, you need to select <strong>one or more</strong> elements from the final array such that those elements are <strong>consecutive</strong> when sorted in increasing order. For example, the elements <code>[3, 4, 5]</code> are consecutive while <code>[3, 4, 6]</code> and <code>[1, 1, 2, 3]</code> are not.<!-- notionvc: 312f8c5d-40d0-4cd1-96cc-9e96a846735b --></p>

<p>Return <em>the <strong>maximum</strong> number of elements that you can select</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,1,5,1,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can increase the elements at indices 0 and 3. The resulting array is nums = [3,1,5,2,1].
We select the elements [<u><strong>3</strong></u>,<u><strong>1</strong></u>,5,<u><strong>2</strong></u>,1] and we sort them to obtain [1,2,3], which are consecutive.
It can be shown that we cannot select more than 3 consecutive elements.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,7,10]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The maximum consecutive elements that we can select is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>


---

# 🛍️ Maximize-Consecutive-Elements-in-an-Array-After-Modification | Explained

## Approach 1: Greedy Linear Scan on Sorted Array (Keep / Bump Strategy)

### Intuition
Imagine you have a line of numbered tiles on a floor. You can **paint one tile** to become any number you like, but you cannot move tiles. After painting, you want the longest stretch of *consecutive* tiles (e.g., 3‑4‑5‑6).  

If the tiles are sorted, the only thing that matters is the **gap** between a tile and the tile that came right before it:

* **Gap = 0** – the tile is a duplicate. You can “paint” this duplicate to extend the current stretch by one extra element (the **bump**).
* **Gap = 1** – the tile naturally continues the current stretch (**keep**) and, if you already used your paint on the previous tile, it also continues the painted stretch (**bump**).
* **Gap = 2** – the tile is one step away from the *painted* tile (you could have painted the missing number). So the **bump** can now be turned into a new **keep**, and you start a fresh **bump** of length 1 (the current tile itself, as if you painted the missing number right before it).
* **Gap > 2** – the distance is too large; any previously painted tile cannot bridge the gap. Both counters must restart at 1.

By walking through the sorted array once and maintaining two counters—`keep` (length of a stretch without using the modification) and `bump` (length of a stretch where we have already used the single modification)—we can compute the best possible stretch on the fly.

### Algorithm Visualized
```mermaid
flowchart TD
    Start([Start]) --> Sort[Sort nums]
    Sort --> Init[Initialize best=keep=bump=1, last=-10]
    Init --> Loop{Iterate x in nums}
    Loop -->|gap=0| Gap0[ bump = keep + 1 ]
    Loop -->|gap=1| Gap1[ keep++, bump++ ]
    Loop -->|gap=2| Gap2[ keep = bump + 1, bump = 1 ]
    Loop -->|gap>2| GapOther[ keep = bump = 1 ]
    Gap0 --> Update[ best = max(best, keep, bump) ]
    Gap1 --> Update
    Gap2 --> Update
    GapOther --> Update
    Update --> SetLast[ last = x ]
    SetLast --> Loop
    Loop -->|end| End[Return best]
```

### Approach
1. **Sort** the input array so that consecutive elements appear next to each other.
2. Initialise  
   * `best` – the answer found so far.  
   * `keep` – length of the current consecutive segment **without** using the modification.  
   * `bump` – length of the current consecutive segment **with** the modification already consumed.  
   * `last` – previous element (seeded with a sentinel far enough away).
3. For each element `x` in the sorted array:
   * Compute the difference `d = x - last`.
   * Update `keep`, `bump`, and possibly restart them based on the four cases described above.
   * Update `best` with the larger of `keep` and `bump`.
   * Set `last = x` for the next iteration.
4. Return `best`.

### Detailed Code Analysis
```java
1 class Solution {
2     public int maxSelectedElements(int[] nums) {
3         Arrays.sort(nums);                     // O(n log n) – puts numbers in non‑decreasing order.
4         // New Solution
5         int best = 1, keep = 1, bump = 1, last = -10;
6         for (int x : nums) {                  // Linear scan over sorted array.
7             switch (x - last) {               // Determine the gap between current and previous value.
8                 case 0 -> bump = keep + 1;    // Duplicate element.
                                             //   We cannot extend `keep` (duplicate doesn't add a new number),
                                             //   but we can *paint* this duplicate to act as the missing
                                             //   number, so the painted segment (`bump`) becomes `keep + 1`.
9                 case 1 -> { keep++; bump++; } // Consecutive element.
                                             //   `keep` grows because the natural run continues.
                                             //   `bump` also grows because the painted run can also
                                             //   absorb this element (the paint was used earlier).
10                case 2 -> { keep = bump + 1; bump = 1; }
                                             // Gap of two.
                                             //   The element `x-1` is missing; we can *paint* that missing
                                             //   number, turning the previous `bump` segment into a new
                                             //   natural segment (`keep = bump + 1`). After using the paint,
                                             //   we start a fresh `bump` consisting only of `x` itself.
11                default -> keep = bump = 1;   // Gap ≥ 3.
                                             //   No single paint can bridge such a gap, so both counters
                                             //   must restart at length 1 (the current element alone).
12            }
13            best = Math.max(best, Math.max(keep, bump));
                                             // Keep the global optimum seen so far.
14            last = x;                         // Advance `last` for the next iteration.
15        }
16        return best;                          // Final answer.
17    }
18 }
```

* **Line 3** – Sorting guarantees that any consecutive numbers appear adjacent; this converts the problem into a *gap‑analysis* one.
* **Line 5** – `last` is seeded with `-10` (any value ≤ `-2` works) to ensure the first element falls into the `default` case, resetting counters to `1`.
* **Line 7‑12** – The `switch` distinguishes the four possible gap categories and updates the two counters accordingly.
* **Line 13** – `best` is the maximum length observed; we compare both counters because the optimal solution may either have used the modification (`bump`) or not (`keep`).
* **Line 14** – Updating `last` prepares the next gap calculation.

### Code
```java
class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        int best = 1, keep = 1, bump = 1, last = -10;
        for (int x : nums) {
            switch (x - last) {
                case 0 -> bump = keep + 1;                  // duplicate
                case 1 -> { keep++; bump++; }               // consecutive
                case 2 -> { keep = bump + 1; bump = 1; }    // gap of two
                default -> keep = bump = 1;                 // gap >= 3
            }
            best = Math.max(best, Math.max(keep, bump));
            last = x;
        }
        return best;
    }
}
```

### Complexity
- **Time:** `O(n log n)` – dominated by the initial `Arrays.sort`. The subsequent loop is `O(n)`.
- **Space:** `O(1)` – only a few integer variables are used; sorting is in‑place for primitive arrays in Java.

## 🕵️‍♂️ Follow‑up Questions (Optional)

1. **What if we were allowed to modify *any* number of elements, not just one?**  
   *Answer:* The problem reduces to finding the longest run of numbers where the number of gaps ≤ `k` (the allowed modifications). This can be solved with a sliding window that tracks the count of missing numbers within the window.

2. **Can the solution be adapted to work without sorting?**  
   *Answer:* Yes, by using a hash set to test for existence of `x-1`, `x`, and `x+1` and counting frequencies, but the overall time becomes `O(n)` on average with extra `O(n)` space. The sorted‑array approach is simpler and guarantees `O(n log n)` worst‑case time with `O(1)` extra space.
<h2><a href="https://leetcode.com/problems/assign-cookies">455. Assign Cookies</a></h2>

<p>Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.</p>

<p>Each child <code>i</code> has a greed factor <code>g[i]</code>, which is the minimum size of a cookie that the child will be content with; and each cookie <code>j</code> has a size <code>s[j]</code>. If <code>s[j] &gt;= g[i]</code>, we can assign the cookie <code>j</code> to the child <code>i</code>, and the child <code>i</code> will be content. Your goal is to maximize the number of your content children and output the maximum number.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> g = [1,2,3], s = [1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre><strong>Input:</strong> g = [1,2], s = [1,2,3]
<strong>Output:</strong> 2
<strong>Explanation:</strong> You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2. 
You have 3 cookies and their sizes are big enough to gratify all of the children, 
You need to output 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= g.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= g[i], s[j] &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Note:</strong> This question is the same as <a href="https://leetcode.com/problems/maximum-matching-of-players-with-trainers/description/" target="_blank"> 2410: Maximum Matching of Players With Trainers.</a></p>


---

# 🛍️ Assign-Cookies | Explained

## Approach 1 (Optimized)
### Intuition
The intuition behind this approach is to ensure that each child gets a cookie that satisfies their greed factor. This can be likened to a real-world scenario where a school is distributing cookies to its students. Each student has a certain level of hunger, and the school wants to distribute cookies in a way that satisfies the hunger of as many students as possible. By sorting both the students' hunger levels and the cookie sizes, the school can efficiently distribute the cookies to maximize the number of satisfied students.

### Approach
The step-by-step algorithmic breakdown is as follows:
1. Sort the children's greed factors in ascending order.
2. Sort the cookie sizes in ascending order.
3. Initialize two pointers, one for the children and one for the cookies.
4. Iterate through the cookie sizes, and for each cookie, check if it can satisfy the current child's greed factor. If it can, move to the next child.
5. Continue this process until all children have been satisfied or all cookies have been distributed.

### Detailed Code Analysis
Let's dive into the code block line by line:
- `Arrays.sort(g);` and `Arrays.sort(s);`: These lines sort the children's greed factors and the cookie sizes in ascending order, respectively. This is done to ensure that the smallest cookie is distributed to the child with the smallest greed factor, maximizing the chances of satisfying as many children as possible.
- `int child=0;` and `int index=0;`: These lines initialize the pointers for the children and the cookies, respectively. The `child` pointer keeps track of the current child being considered, while the `index` pointer keeps track of the current cookie being considered.
- `while(index<s.length && child<g.length)`: This line checks if there are still children who have not been satisfied and if there are still cookies available. If either condition is not met, the loop terminates.
- `if(s[index]>=g[child])`: This line checks if the current cookie can satisfy the current child's greed factor. If it can, the child is moved to the next child by incrementing the `child` pointer.
- `index++`: This line moves to the next cookie, regardless of whether the current cookie was distributed to a child or not.
- `return child;`: This line returns the total number of children who were satisfied with a cookie.

### Code
```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int child=0;
        int index=0;
        while(index<s.length && child<g.length){
            if(s[index]>=g[child]){
                child++;
            }
            index++;
        }
        return child;
    }
}
```
### Complexity
- Time: The time complexity of this approach is O(n log n + m log m), where n is the number of children and m is the number of cookies. This is because the approach involves sorting both the children's greed factors and the cookie sizes, which takes O(n log n) and O(m log m) time, respectively. The subsequent while loop takes O(n + m) time, but this is dominated by the sorting operations.
- Space: The space complexity of this approach is O(1), excluding the space needed for the input arrays. This is because the approach only uses a constant amount of space to store the pointers and does not allocate any additional arrays or data structures that scale with the input size.

## 🕵️‍♂️ Follow-up Questions (Optional)
Some common follow-up questions for this problem include:
- How would you modify the solution if the greed factors and cookie sizes were not integers, but floating-point numbers? 
  Answer: The approach would remain the same, but the sorting operations would need to be modified to handle floating-point numbers correctly.
- How would you optimize the solution if the input arrays were extremely large and did not fit into memory? 
  Answer: The solution would need to be modified to use external sorting algorithms or streaming algorithms that can handle large datasets without loading them entirely into memory.
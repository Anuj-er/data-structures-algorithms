<h2><a href="https://leetcode.com/problems/assign-cookies">455. Assign Cookies</a></h2>

<p>Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.</p>

<p>Each child <code>i</code> has a greed factor <code>g[i]</code>, which is the minimum size of a cookie that the child will be content with; and each cookie <code>j</code> has a size <code>s[j]</code>. If <code>s[j] &gt;= g[i]</code>, we can assign the cookie <code>j</code> to the child <code>i</code>, and the child <code>i</code> will be content. Your goal is to maximize the number of your content children and output the maximum number.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> g = [1,2,3], s = [1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong> You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3. 
And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
You need to output 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> g = [1,2], s = [1,2,3]
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
The core idea behind this approach is to sort both the greed factor of the children (`g`) and the size of the cookies (`s`) in ascending order. This works because a child can be satisfied with a cookie that is at least as large as their greed factor. By sorting both arrays, we can efficiently match the children with the cookies that satisfy their greed factor. This approach can be thought of as a real-world scenario where we line up the children and cookies in order of size, and then try to find the best match for each child.

### Approach
The algorithmic breakdown of this approach is as follows:
1. Sort the greed factor of the children (`g`) in ascending order.
2. Sort the size of the cookies (`s`) in ascending order.
3. Initialize two pointers, one for the children (`child`) and one for the cookies (`index`).
4. Iterate through the cookies array, and for each cookie, check if it can satisfy the current child's greed factor.
5. If the cookie can satisfy the child, move to the next child. Otherwise, move to the next cookie.
6. Continue this process until all children have been satisfied or all cookies have been used.

### Detailed Code Analysis
Let's break down the code line by line:

* `Arrays.sort(g);` and `Arrays.sort(s);`: These lines sort the greed factor of the children (`g`) and the size of the cookies (`s`) in ascending order using Java's built-in `Arrays.sort()` method.
* `int child=0;` and `int index=0;`: These lines initialize two pointers, `child` and `index`, to keep track of the current child and cookie being considered.
* `while(index<s.length && child<g.length)`: This line starts a loop that continues as long as there are still cookies left and children to satisfy.
* `if(s[index]>=g[child])`: This line checks if the current cookie can satisfy the current child's greed factor.
* `child++;`: If the cookie can satisfy the child, this line moves to the next child.
* `index++;`: This line moves to the next cookie, regardless of whether the current cookie satisfied a child or not.
* `return child;`: Finally, this line returns the number of children that were satisfied.

### Code
```java
public int findContentChildren(int[] g, int[] s) {
    Arrays.sort(g);
    Arrays.sort(s);
    int child = 0;
    int index = 0;
    while (index < s.length && child < g.length) {
        if (s[index] >= g[child]) {
            child++;
        }
        index++;
    }
    return child;
}
```

### Complexity
- Time: The time complexity of this approach is O(n log n + m log m), where n is the number of children and m is the number of cookies. This is because we are sorting both arrays, which takes O(n log n) and O(m log m) time respectively. The while loop then runs in O(m) time, but this is dominated by the sorting steps.
- Space: The space complexity of this approach is O(1), assuming that the input arrays can be modified in place. If the input arrays cannot be modified, we would need to create new arrays to store the sorted values, which would increase the space complexity to O(n + m).

## 🕵️‍♂️ Follow-up Questions (Optional)
Some common follow-up questions for this problem might include:
* What if the cookies and children are not represented as arrays, but as linked lists or other data structures? How would you modify the approach to handle this?
* How would you optimize the approach if the number of children and cookies is extremely large, and the available memory is limited?
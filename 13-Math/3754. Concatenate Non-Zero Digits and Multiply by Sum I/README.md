<h2><a href="https://leetcode.com/problems/concatenate-non-zero-digits-and-multiply-by-sum-i">3754. Concatenate Non-Zero Digits and Multiply by Sum I</a></h2>

<p>You are given an integer <code>n</code>.</p>

<p>Form a new integer <code>x</code> by concatenating all the <strong>non-zero digits</strong> of <code>n</code> in their original order. If there are no <strong>non-zero</strong> digits, <code>x = 0</code>.</p>

<p>Let <code>sum</code> be the <strong>sum of digits</strong> in <code>x</code>.</p>

<p>Return an integer representing the value of <code>x * sum</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 10203004</span></p>

<p><strong>Output:</strong> <span class="example-io">12340</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The non-zero digits are 1, 2, 3, and 4. Thus, <code>x = 1234</code>.</li>
	<li>The sum of digits is <code>sum = 1 + 2 + 3 + 4 = 10</code>.</li>
	<li>Therefore, the answer is <code>x * sum = 1234 * 10 = 12340</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 1000</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The non-zero digit is 1, so <code>x = 1</code> and <code>sum = 1</code>.</li>
	<li>Therefore, the answer is <code>x * sum = 1 * 1 = 1</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>


---

# 🛍️ Concatenate-Non-Zero-Digits-and-Multiply-by-Sum-I | Explained

## Approach 1 (Iterative Concatenation and Summation)
### Intuition
Imagine you have a number, and you want to concatenate all its non-zero digits and multiply the result by the sum of these non-zero digits. Think of it like a numerical puzzle where each non-zero digit contributes to both the concatenated number and the sum. This approach works because it iterates through each digit of the input number, filtering out zeros, and constructs the desired output step by step.

### Approach
1. Convert the input number into a format that allows easy iteration over its digits.
2. For each non-zero digit found, concatenate it to a growing number and add its value to a running sum.
3. After processing all digits, multiply the concatenated number by the sum of the non-zero digits to get the final result.

### Detailed Code Analysis
The provided code snippet implements this approach:
- It starts by initializing two variables, `x` and `s`, to zero. `x` will hold the concatenated non-zero digits, and `s` will hold the sum of these digits.
- The code then converts the input number `n` to a character array using `String.valueOf(n).toCharArray()`. This allows for easy iteration over each digit of the number.
- Inside the `for` loop, it checks if the current digit `c` is not equal to '0'. If it's not zero, it performs two operations:
  - It concatenates the digit to `x` by shifting the current value of `x` one decimal place to the left (multiplying by 10) and then adding the value of the digit. The expression `c - '0'` converts the character digit to its numerical value.
  - It adds the value of the digit to the sum `s`.
- Finally, after the loop has processed all digits, the code returns the product of `x` and `s`, which represents the concatenated non-zero digits multiplied by their sum.

### Code
```java
class Solution {
    public long sumAndMultiply(int n) {
        long x = 0, s = 0;
        for (char c : String.valueOf(n).toCharArray())
            if (c != '0') {
                x = x * 10 + c - '0';
                s += c - '0';
            }
        return x * s;
    }
}
```

### Complexity
- Time: The time complexity of this solution is O(log n), where n is the input number. This is because the number of digits in n is logarithmic in the size of n. The loop iterates over each digit once, performing constant time operations.
- Space: The space complexity is O(log n) as well, due to the conversion of the number to a character array. However, if we only consider the extra space not counting the input, the space complexity would be O(1) since the space used does not grow with the size of the input number, but rather with the number of digits, which is logarithmic but is considered constant for large numbers.

## 🕵️‍♂️ Follow-up Questions (Optional)
Some potential follow-up questions for this problem could include:
- How would you handle very large input numbers that exceed the limit of the `long` data type in Java? 
  - This could involve using a `BigInteger` class to handle arbitrarily large integers.
- What if the input is not a positive integer, but could be negative or a non-integer?
  - This would require additional error checking code to handle such inputs appropriately, potentially by throwing exceptions or returning error values for invalid inputs.
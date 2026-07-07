<h2><a href="https://leetcode.com/problems/word-pattern">290. Word Pattern</a></h2>

<p>Given a <code>pattern</code> and a string <code>s</code>, find if <code>s</code>&nbsp;follows the same pattern.</p>

<p>Here <b>follow</b> means a full match, such that there is a bijection between a letter in <code>pattern</code> and a <b>non-empty</b> word in <code>s</code>. Specifically:</p>

<ul>
	<li>Each letter in <code>pattern</code> maps to <strong>exactly</strong> one unique word in <code>s</code>.</li>
	<li>Each unique word in <code>s</code> maps to <strong>exactly</strong> one letter in <code>pattern</code>.</li>
	<li>No two letters map to the same word, and no two words map to the same letter.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">pattern = "abba", s = "dog cat cat dog"</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>

<p><strong>Explanation:</strong></p>

<p>The bijection can be established as:</p>

<ul>
	<li><code>'a'</code> maps to <code>"dog"</code>.</li>
	<li><code>'b'</code> maps to <code>"cat"</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">pattern = "abba", s = "dog cat cat fish"</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">pattern = "aaaa", s = "dog cat cat dog"</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= pattern.length &lt;= 300</code></li>
	<li><code>pattern</code> contains only lower-case English letters.</li>
	<li><code>1 &lt;= s.length &lt;= 3000</code></li>
	<li><code>s</code> contains only lowercase English letters and spaces <code>' '</code>.</li>
	<li><code>s</code> <strong>does not contain</strong> any leading or trailing spaces.</li>
	<li>All the words in <code>s</code> are separated by a <strong>single space</strong>.</li>
</ul>


---

# 🛍️ Word-Pattern | Explained

## Approach 1 (Two Hash Maps)
### Intuition
The core idea behind this approach is to use two hash maps to keep track of the mappings between characters in the pattern and words in the string. Think of it like a bilingual dictionary, where each word in one language is mapped to a corresponding word in the other language. In this case, each character in the pattern is mapped to a word in the string, and vice versa. This approach works because it allows us to efficiently check if a character in the pattern is already mapped to a word, and if a word is already mapped to a character.

### Approach
The algorithmic breakdown of this approach is as follows:
1. Split the input string into an array of words.
2. Check if the length of the pattern is equal to the length of the array of words. If not, return false.
3. Initialize two hash maps: one to map characters to words, and one to map words to characters.
4. Iterate over the pattern and the array of words simultaneously. For each character in the pattern and corresponding word in the array:
   - Check if the character is already mapped to a word. If it is, and the mapped word is not the current word, return false.
   - Check if the word is already mapped to a character. If it is, and the mapped character is not the current character, return false.
   - If neither of the above conditions is true, map the character to the word and the word to the character.
5. If the iteration completes without returning false, return true.

### Detailed Code Analysis
Let's dive into the code block:
- Line 4: `String[] words = s.split( );` splits the input string `s` into an array of words using spaces as the delimiter.
- Line 6-8: `if (pattern.length() != words.length) { return false; }` checks if the length of the pattern is equal to the length of the array of words. If not, it immediately returns false, as the pattern and the string cannot match.
- Lines 10-11: `HashMap<Character, String> charToWord = new HashMap<>(); HashMap<String, Character> wordToChar = new HashMap<>();` initialize the two hash maps: `charToWord` maps characters to words, and `wordToChar` maps words to characters.
- Lines 13-33: The for loop iterates over the pattern and the array of words simultaneously. For each character in the pattern and corresponding word in the array:
   - Lines 18-21: `if (charToWord.containsKey(ch)) { if (!charToWord.get(ch).equals(word)) { return false; } }` check if the character is already mapped to a word. If it is, and the mapped word is not the current word, return false.
   - Lines 25-28: `if (wordToChar.containsKey(word)) { if (wordToChar.get(word) != ch) { return false; } }` check if the word is already mapped to a character. If it is, and the mapped character is not the current character, return false.
   - Lines 23 and 31: `charToWord.put(ch, word);` and `wordToChar.put(word, ch);` map the character to the word and the word to the character, respectively.
- Line 35: `return true;` returns true if the iteration completes without returning false, indicating that the pattern and the string match.

### Code
```java
public boolean wordPattern(String pattern, String s) {
    String[] words = s.split( );
    if (pattern.length() != words.length) {
        return false;
    }
    HashMap<Character, String> charToWord = new HashMap<>();
    HashMap<String, Character> wordToChar = new HashMap<>();
    for (int i = 0; i < pattern.length(); i++) {
        char ch = pattern.charAt(i);
        String word = words[i];
        if (charToWord.containsKey(ch)) {
            if (!charToWord.get(ch).equals(word)) {
                return false;
            }
        } else {
            charToWord.put(ch, word);
        }
        if (wordToChar.containsKey(word)) {
            if (wordToChar.get(word) != ch) {
                return false;
            }
        } else {
            wordToChar.put(word, ch);
        }
    }
    return true;
}
```

### Complexity
- Time: O(n), where n is the length of the pattern (or the number of words in the string). This is because we iterate over the pattern and the array of words once.
- Space: O(n), where n is the length of the pattern (or the number of words in the string). This is because we use two hash maps to store the mappings between characters and words, and in the worst case, every character in the pattern is mapped to a unique word, and every word is mapped to a unique character.

## 🕵️‍♂️ Follow-up Questions (Optional)
Some common follow-up questions for this pattern include:
1. What if the input string is empty or null? How would you handle this case?
   - In this case, we would need to add some error checking code to handle the case where the input string is empty or null. For example, we could return false immediately if the input string is empty or null.
2. What if the pattern is empty or null? How would you handle this case?
   - Similar to the previous question, we would need to add some error checking code to handle the case where the pattern is empty or null. For example, we could return false immediately if the pattern is empty or null.
<h2><a href="https://leetcode.com/problems/most-common-word">819. Most Common Word</a></h2>

<p>Given a string <code>paragraph</code> and a string array of the banned words <code>banned</code>, return <em>the most frequent word that is not banned</em>. It is <strong>guaranteed</strong> there is <strong>at least one word</strong> that is not banned, and that the answer is <strong>unique</strong>.</p>

<p>The words in <code>paragraph</code> are <strong>case-insensitive</strong> and the answer should be returned in <strong>lowercase</strong>.</p>

<p><strong>Note</strong> that words can not contain punctuation symbols.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
<strong>Output:</strong> "ball"
<strong>Explanation:</strong> 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre><strong>Input:</strong> paragraph = "a.", banned = []
<strong>Output:</strong> "a"
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= paragraph.length &lt;= 1000</code></li>
	<li>paragraph consists of English letters, space <code>' '</code>, or one of the symbols: <code>"!?',;."</code>.</li>
	<li><code>0 &lt;= banned.length &lt;= 100</code></li>
	<li><code>1 &lt;= banned[i].length &lt;= 10</code></li>
	<li><code>banned[i]</code> consists of only lowercase English letters.</li>
</ul>


---

# 🛍️ Most-Common-Word | Explained

## Approach 1 (Active Approach)
### Intuition
The core idea behind this solution is to first preprocess the input paragraph to extract individual words and convert them to lowercase to handle case insensitivity. Then, it utilizes a `HashSet` to store banned words for efficient lookups and a `HashMap` to count the frequency of each word. This approach ensures that we can efficiently identify and skip banned words while keeping track of the frequency of the remaining words. The word with the highest frequency is then selected as the most common word.

### Approach
The algorithm works as follows:
1. Preprocess the input paragraph by converting it to lowercase.
2. Split the paragraph into individual words using a regular expression that matches one or more non-word characters (including punctuation).
3. Create a `HashSet` to store banned words for efficient lookups.
4. Iterate through each word in the paragraph. If the word is not banned and not empty, increment its frequency in the `HashMap`.
5. During the iteration, keep track of the word with the highest frequency encountered so far.
6. Once all words have been processed, the word with the highest frequency is returned as the most common word.

### Code
```java
public String mostCommonWord(String paragraph, String[] banned) {
    paragraph = paragraph.toLowerCase();
    String[] words = paragraph.split("[ !?',;.]+");
    Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
    Map<String,Integer> mp = new HashMap<>();
    StringBuilder ans = new StringBuilder();
    int maxFreq = 0;

    for(String word : words){
        if(word.isEmpty() || bannedSet.contains(word)){
            continue;
        }
        int freq = mp.getOrDefault(word,0)+1;
        mp.put(word,freq);
        
        if(freq > maxFreq){
            maxFreq = freq;
            ans.setLength(0);
            ans.append(word);
        }
    }
    
    return ans.toString();
}
```

### Complexity
- Time: O(N + M), where N is the total number of words in the paragraph and M is the number of banned words. The reason is that we're doing a constant amount of work for each word and banned word.
- Space: O(N + M), as in the worst case, we might store all words and banned words in our data structures.

## Approach 2 (First Optimized Approach)
### Intuition
This approach also starts by preprocessing the input paragraph to extract individual words and convert them to lowercase. It uses a similar strategy with a `HashSet` for banned words and a `HashMap` for word frequencies. However, instead of updating the most common word during the frequency counting process, it iterates through the `HashMap` after counting all words to find the most common word.

### Approach
The algorithm works as follows:
1. Preprocess the input paragraph by converting it to lowercase.
2. Split the paragraph into individual words using a regular expression that matches one or more non-word characters (including punctuation).
3. Create a `HashSet` to store banned words for efficient lookups.
4. Iterate through each word in the paragraph. If the word is not empty, check if it's banned. If not banned, increment its frequency in the `HashMap`.
5. After counting all words, iterate through the `HashMap` to find the word with the highest frequency.
6. Return the word with the highest frequency as the most common word.

### Code
```java
public String mostCommonWord(String paragraph, String[] banned) {
    paragraph = paragraph.toLowerCase();
    String[] words = paragraph.split("[ !?',;.]+");
    Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
    Map<String,Integer> mp = new HashMap<>();
    for(String word : words){
        if(word.isEmpty()){
            continue;
        }
        if(!bannedSet.contains(word)){
            mp.put(word,mp.getOrDefault(word,0)+1);
        }
    }
    StringBuilder ans = new StringBuilder();
    int maxFreq = 0;
    for(Map.Entry<String,Integer> entry : mp.entrySet()){
        if(entry.getValue() > maxFreq){
            maxFreq = entry.Value;
            ans.setLength(0);
            ans.append(entry.getKey());
        }
    }
    return ans.toString();
}
```

### Complexity
- Time: O(N + M), where N is the total number of words in the paragraph and M is the number of banned words. The reason is similar to the previous approach, but with an additional iteration through the `HashMap`.
- Space: O(N + M), as in the worst case, we might store all words and banned words in our data structures.

## Approach 3 (Least Optimized Approach)
### Intuition
This approach preprocessed the input paragraph and used a `HashMap` to count word frequencies but checked each word against the banned array without using a `HashSet` for banned words. This results in less efficient lookup for banned words.

### Approach
The algorithm works as follows:
1. Preprocess the input paragraph by converting it to lowercase.
2. Split the paragraph into individual words using a regular expression that matches one or more non-word characters (including punctuation).
3. Iterate through each word in the paragraph. For each word, check if it's banned by iterating through the banned array.
4. If a word is not banned, increment its frequency in the `HashMap`.
5. After counting all words, iterate through the `HashMap` to find the word with the highest frequency.
6. Return the word with the highest frequency as the most common word.

### Code
```java
public String mostCommonWord(String paragraph, String[] banned) {
    paragraph = paragraph.toLowerCase();
    String[] words = paragraph.split("[ !?',;.]+");
    Map<String,Integer> mp = new HashMap<>();
    for(String word : words){
        boolean isBanned = false;
        for(String b : banned){
            if(word.equals(b)){
                isBanned = true;
                break;
            }
        }
        if(!isBanned){
            mp.put(word,mp.getOrDefault(word,0)+1);
        }
    }
    String ans = ;
    int maxFreq = 0;
    for(Map.Entry<String,Integer> entry : mp.entrySet()){
        if(entry.getValue() > maxFreq){
            maxFreq = entry.getValue();
            ans = entry.getKey();
        }
    }
    return ans;
}
```

### Complexity
- Time: O(N * M + N), where N is the total number of words in the paragraph and M is the number of banned words. The reason is the nested loop structure where for each word, we potentially iterate through all banned words.
- Space: O(N + M), as in the worst case, we might store all words and banned words in our data structures.

## 🕵️‍♂️ Follow-up Questions
1. **What if the input paragraph is extremely large and does not fit into memory?** 
   - In such a case, we would need to process the paragraph in chunks, possibly using a streaming approach to handle the input, counting word frequencies as we go, and keeping track of the most common word seen so far.

2. **How would you handle punctuation next to words without spaces (e.g., "word," or "word.")?**
   - The regular expression used in the `split` method already accounts for punctuation by treating it as a word separator. However, to ensure consistency, especially if the input format might vary, considering using a more sophisticated text processing approach or library that can handle different types of punctuation and word boundaries.
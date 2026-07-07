1class Solution {
2    public boolean wordPattern(String pattern, String s) {
3
4        String[] words = s.split( );
5
6        if (pattern.length() != words.length) {
7            return false;
8        }
9
10        HashMap<Character, String> charToWord = new HashMap<>();
11        HashMap<String, Character> wordToChar = new HashMap<>();
12
13        for (int i = 0; i < pattern.length(); i++) {
14
15            char ch = pattern.charAt(i);
16            String word = words[i];
17
18            if (charToWord.containsKey(ch)) {
19                if (!charToWord.get(ch).equals(word)) {
20                    return false;
21                }
22            } else {
23                charToWord.put(ch, word);
24            }
25
26            if (wordToChar.containsKey(word)) {
27                if (wordToChar.get(word) != ch) {
28                    return false;
29                }
30            } else {
31                wordToChar.put(word, ch);
32            }
33        }
34
35        return true;
36    }
37}
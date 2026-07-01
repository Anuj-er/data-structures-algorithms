1class Solution {
2    public String mostCommonWord(String paragraph, String[] banned) {
3        paragraph = paragraph.toLowerCase();
4
5        String[] words = paragraph.split([ !?',;.]+);
6        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
7        Map<String,Integer> mp = new HashMap<>();
8
9        StringBuilder ans = new StringBuilder();
10        int maxFreq = 0;
11
12        for(String word : words){
13            if(word.isEmpty() || bannedSet.contains(word)){
14                continue;
15            }
16            int freq = mp.getOrDefault(word,0)+1;
17            mp.put(word,freq);
18            
19            if(freq > maxFreq){
20                maxFreq = freq;
21                ans.setLength(0);
22                ans.append(word);
23            }
24            
25        }
26        
27        return ans.toString();
28    }
29}
30
31// class Solution {
32//     public String mostCommonWord(String paragraph, String[] banned) {
33//         paragraph = paragraph.toLowerCase();
34//         String[] words = paragraph.split([ !?',;.]+);
35//         Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
36//         Map<String,Integer> mp = new HashMap<>();
37//         for(String word : words){
38//             if(word.isEmpty()){
39//                 continue;
40//             }
41//             if(!bannedSet.contains(word)){
42//                 mp.put(word,mp.getOrDefault(word,0)+1);
43//             }
44//         }
45//         StringBuilder ans = new StringBuilder();
46//         int maxFreq = 0;
47//         for(Map.Entry<String,Integer> entry : mp.entrySet()){
48//             if(entry.getValue() > maxFreq){
49//                 maxFreq = entry.getValue();
50//                 ans.setLength(0);
51//                 ans.append(entry.getKey());
52//             }
53//         }
54//         return ans.toString();
55//     }
56// }
57
58// class Solution {
59//     public String mostCommonWord(String paragraph, String[] banned) {
60//         paragraph = paragraph.toLowerCase();
61//         String[] words = paragraph.split([ !?',;.]+);
62//         Map<String,Integer> mp = new HashMap<>();
63//         for(String word : words){
64//             boolean isBanned = false;
65//             for(String b : banned){
66//                 if(word.equals(b)){
67//                     isBanned = true;
68//                     break;
69//                 }
70//             }
71//             if(!isBanned){
72//                 mp.put(word,mp.getOrDefault(word,0)+1);
73//             }
74//         }
75//         String ans = ;
76//         int maxFreq = 0;
77//         for(Map.Entry<String,Integer> entry : mp.entrySet()){
78//             if(entry.getValue() > maxFreq){
79//                 maxFreq = entry.getValue();
80//                 ans = entry.getKey();
81//             }
82//         }
83//         return ans;
84//     }
85// }
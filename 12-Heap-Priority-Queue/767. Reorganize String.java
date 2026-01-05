// https://leetcode.com/problems/reorganize-string/description/

class CharFreq {
    char ch;
    int freq;

    CharFreq(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}

class Solution {
    public String reorganizeString(String s) {
        int n = s.length();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        PriorityQueue<CharFreq> maxHeap =
            new PriorityQueue<>((a, b) -> b.freq - a.freq);

        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                maxHeap.add(new CharFreq((char) ('a' + i), count[i]));
            }
        }

        int limit = (n + 1) / 2;
        if (maxHeap.peek().freq > limit) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        while (!maxHeap.isEmpty()) {

            if (maxHeap.size() == 1) {
                CharFreq last = maxHeap.poll();
                result.append(last.ch);
                break;
            }

            CharFreq first = maxHeap.poll();
            CharFreq second = maxHeap.poll();

            result.append(first.ch);
            result.append(second.ch);

            first.freq--;
            second.freq--;

            if (first.freq > 0) {
                maxHeap.add(first);
            }
            if (second.freq > 0) {
                maxHeap.add(second);
            }
        }

        return result.toString();
    }
}


// class Pair{
//     char ch;
//     int freq;
//     Pair(char ch,int freq){
//         this.ch = ch;
//         this.freq = freq;
//     }
// }

// class Solution {
//     public String reorganizeString(String s) {
//         int n = s.length();
//         int[] freq = new int[26];

//         StringBuilder sb = new StringBuilder();

//         PriorityQueue<Pair> pq = new PriorityQueue<>(
//             (a,b) -> Integer.compare(b.freq,a.freq)
//         );

//         for(char c: s.toCharArray()) {
//             freq[c - 'a']++;
//         }
        
//         for(int i=0;i<26;i++){
//             if(freq[i]>0) pq.add(new Pair((char)(i+'a'),freq[i]));
//         }

//         if(pq.peek().freq > (n+1)/2) return sb.toString();

//         while(!pq.isEmpty()){
//             if(pq.size() == 1){
//                 Pair a = pq.poll();
//                 a.freq--;
//                 sb.append(a.ch);
//                 continue;
//             }
//             Pair p = pq.poll();
//             Pair q = pq.poll();
//             p.freq--;
//             q.freq--;
//             sb.append(p.ch);
//             sb.append(q.ch);
//             if(p.freq>0) pq.add(p);
//             if(q.freq>0) pq.add(q);
//         }

//         return sb.toString();
//     }
// }
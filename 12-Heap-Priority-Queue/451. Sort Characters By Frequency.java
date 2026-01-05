// https://leetcode.com/problems/sort-characters-by-frequency/

class Pair {
    char s;
    int freq;

    Pair(char s, int freq) {
        this.s = s;
        this.freq = freq;
    }
}

class Solution {
    public String frequencySort(String s) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> mp = new HashMap<>();
        for (char ch : s.toCharArray()) {
            if (mp.containsKey(ch))
                mp.put(ch, mp.get(ch) + 1);
            else
                mp.put(ch, 1);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.freq, a.freq));
        for (Map.Entry<Character, Integer> e : mp.entrySet()) {
            pq.add(new Pair(e.getKey(), e.getValue()));
        }

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            char ch = p.s;
            int freq = p.freq;
            for (int i = 0; i < freq; i++) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
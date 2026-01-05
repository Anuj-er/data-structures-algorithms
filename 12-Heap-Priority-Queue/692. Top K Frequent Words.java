// https://leetcode.com/problems/top-k-frequent-elements/description/

// Correct Approach Using MinHeap and HashMap
class Solution {
    class Pair {
        int freq, num;

        Pair(int f, int n) {
            freq = f;
            num = n;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> mp = new HashMap<>();
        for(int n:nums){
            if(mp.containsKey(n)) mp.put(n,mp.get(n)+1);
            else mp.put(n,1);
        }
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a,b) -> a.freq - b.freq);
        for(Map.Entry<Integer,Integer> e : mp.entrySet()){
           minHeap.add(new Pair(e.getValue(),e.getKey()));
           if(minHeap.size()>k) minHeap.poll();
        }       
        int[] ans = new int[k];
        for(int i=0;i<k;i++){
            ans[i] = minHeap.poll().num;
        }
        return ans; 
    }
}

// // Incorrect Approach Tried to use HashMap;
// class Solution {
//     public int[] topKFrequent(int[] nums, int k) {
//         HashMap<Integer,Integer> mp = new HashMap<>();
//         for(int n:nums){
//             if(mp.containsKey(n)) mp.put(n,mp.get(n)+1);
//             else mp.put(n,1);
//         }
//         ArrayList<Integer> ans = new ArrayList<>();
//         for (Map.Entry<Integer,Integer> e : mp.entrySet()) {
//             int key = e.getKey();
//             int freq = e.getValue();

//             if(freq>=k){
//                 ans.add(key);
//             }
//         }
//         int[] arr = new int[k];
//         for(int i=0;i<k;i++){
//             arr[i] = ans.get(i);
//         }
//         return arr;
//     }
// }
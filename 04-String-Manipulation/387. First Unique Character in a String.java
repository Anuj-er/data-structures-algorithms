// https://leetcode.com/problems/first-unique-character-in-a-string/description/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


// Solving Using Hashmap only
// class Solution {
//     public int firstUniqChar(String s) {
//         HashMap<Character, Integer> hash = new HashMap<>();
//         char[] charA = s.toCharArray();

//         // Count frequency
//         for(char c: charA){
//             hash.put(c, hash.getOrDefault(c, 0) + 1);
//         }

//         // Find first unique character
//         for(int i=0; i<charA.length; i++){
//             if(hash.get(charA[i]) == 1){
//                 return i;
//             }
//         }

//         return -1;
//     }
// }


// Using queue
class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> freq = new HashMap<>();
        Queue<Integer> qu = new LinkedList<>();
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            qu.add(i);

            while (!qu.isEmpty() && freq.get(arr[qu.peek()]) > 1) {
                qu.poll(); // remove indices of repeated characters
            }
        }

        return qu.isEmpty() ? -1 : qu.peek();
    }
}
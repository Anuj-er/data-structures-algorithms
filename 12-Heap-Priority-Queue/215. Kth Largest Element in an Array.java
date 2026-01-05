// https://leetcode.com/problems/kth-largest-element-in-an-array/description/

// Using Min-heap Approach
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for(int n : nums) {
            minHeap.add(n);
            if(minHeap.size()>k) minHeap.poll();
        }

        return minHeap.peek();
    }
}

// Using Max-heap approach
// class Solution {
//     public int findKthLargest(int[] nums, int k) {
//         PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

//         for(int n : nums) {
//             maxHeap.add(n);
//         }

//         // remove k-1 largest elements
//         for(int i = 1; i < k; i++) {
//             maxHeap.poll();
//         }

//         return maxHeap.peek();
//     }
// }
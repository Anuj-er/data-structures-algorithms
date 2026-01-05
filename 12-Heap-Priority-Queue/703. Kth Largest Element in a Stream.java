// https://leetcode.com/problems/kth-largest-element-in-a-stream/description/

class KthLargest {

    private PriorityQueue<Integer> mh;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        mh = new PriorityQueue<>();
        for(int n: nums){
            mh.add(n);
            if(mh.size()>k) mh.poll();
        }
    }
    
    public int add(int val) {
        mh.add(val);
        if(mh.size()>k) mh.poll();
        return mh.peek();
    }
}

// Max-Heap approach takes way much time.
// class KthLargest {

//     private PriorityQueue<Integer> mh;
//     private int k;

//     public KthLargest(int k, int[] nums) {
//         this.k = k;
//         mh = new PriorityQueue<>(Collections.reverseOrder());
//         for(int n : nums){
//             mh.add(n);
//         }
//     }
    
//     public int add(int val) {
//         mh.add(val);
//         PriorityQueue<Integer> temp = new PriorityQueue<>(mh);
//         for(int i = 1; i < k; i++){
//             temp.poll();
//         }
//         return temp.peek();
//     }
// }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
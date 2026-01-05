// https://leetcode.com/problems/last-stone-weight/description/

class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> mh = new PriorityQueue<>(Collections.reverseOrder());
        for(int n: stones){
            mh.add(n);
        }
        while(mh.size()>1){
            // First Largest
            int x = mh.poll();
            // Second Largest
            int y = mh.poll();

            if(y!=x){
                mh.add(x-y);
            }
        }
        return mh.isEmpty() ? 0 : mh.peek();
    }
}
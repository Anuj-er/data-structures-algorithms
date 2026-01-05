// https://leetcode.com/problems/find-median-from-data-stream/description/

class MedianFinder {
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(maxHeap.isEmpty() || num <= maxHeap.peek()) maxHeap.add(num);
        else minHeap.add(num);

        if(maxHeap.size() > minHeap.size()+1) minHeap.add(maxHeap.poll());
        else if(minHeap.size() > maxHeap.size()) maxHeap.add(minHeap.poll());
     }

    public double findMedian() {
        if(maxHeap.size() == minHeap.size()){
            double ans = (minHeap.peek()+maxHeap.peek()) / 2.0;
            return ans;
        }else{
            return maxHeap.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
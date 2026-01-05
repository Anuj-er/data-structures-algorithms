// https://leetcode.com/problems/number-of-recent-calls/description/

import java.util.LinkedList;
import java.util.Queue;

class RecentCounter {
    Queue<Integer> myqueue;

    public RecentCounter() {
        myqueue = new LinkedList<>();
    }
    
    public int ping(int t) {
        myqueue.add(t);
        while(!myqueue.isEmpty() && t - 3000 > myqueue.peek()){
            myqueue.poll();
        }
        return myqueue.size();
    }
}

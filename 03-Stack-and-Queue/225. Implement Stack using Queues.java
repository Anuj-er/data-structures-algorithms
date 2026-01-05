// https://leetcode.com/problems/implement-stack-using-queues/

import java.util.*;



// Using 2 queues
class MyStack {
    
    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();

    }
    
    // q2 will be our stack 
    // we first completely empty queue2 by moving all elements to queue1, then add the new element, and finally move back all the previously emptied elements
    public void push(int x) {
        while(!q2.isEmpty()){ 
            q1.add(q2.poll());
        }
        q2.add(x);
        while(!q1.isEmpty()){
            q2.add(q1.poll());
        }
    }
    
    public int pop() {
        return q2.poll();
    }
    
    public int top() {
        return q2.peek();
    }
    
    public boolean empty() {
        return q2.isEmpty();
    }
}

// Follow Up
// Can you implement the same stack using a single queue?

class MyStack2 {

    Queue<Integer> q1;

    public MyStack2() {
        q1 = new LinkedList<>();

    }

    // like if our queue is 3 --> 2 --> 1
    // we going to push 4
    // our queue will become 3 --> 2 --> 1 --> 4
    // we calculated size before so it was 3
    // we will add 4 to the end and then move the first 3 elements to the end
    public void push(int x) {
        int size = q1.size();
        q1.add(x);
        while(size > 0){
            q1.add(q1.poll());
            size--;
        }
    }
    
    public int pop() {
        return q1.poll();
    }
    
    public int top() {
        return q1.peek();
    }
    
    public boolean empty() {
        return q1.isEmpty();
    }
}
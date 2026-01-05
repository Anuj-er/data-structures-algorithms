// https://leetcode.com/problems/design-circular-deque/description/


class MyCircularDeque {

    private int[] deq;     // array to store deque elements
    private int front;     // index of the front element
    private int rear;      // index of the rear element
    private int current;   // current number of elements in deque
    private int k;         // maximum capacity of deque

    public MyCircularDeque(int size) {
        k = size;
        front = 0;         // initially front at index 0
        rear = k - 1;      // rear placed at last index (will move when insert happens)
        current = 0;       // initially deque is empty
        deq = new int[k];  // create array of size k
    }
    
    // Insert element at the front
    public boolean insertFront(int value) {
        if (current < k) {                         // check if space is available
            front = (front - 1 + k) % k;           // move front pointer one step backward (circular)
            deq[front] = value;                    // place value at new front
            current++;                             // increase element count
            return true;
        } else {
            return false;                          // if deque is full
        }
    }
    
    // Insert element at the rear
    public boolean insertLast(int value) {
        if (current < k) {                         // check if space is available
            rear = (rear + 1) % k;                 // move rear pointer one step forward (circular)
            deq[rear] = value;                     // place value at new rear
            current++;                             // increase element count
            return true;
        } else {
            return false;                          // if deque is full
        }
    }
    
    // Delete element from the front
    public boolean deleteFront() {
        if (current != 0) {                        // check if deque is not empty
            front = (front + 1) % k;               // move front pointer forward (skip old front)
            current--;                             // decrease element count
            return true;
        } else {
            return false;                          // if deque is empty
        }
    }
    
    // Delete element from the rear
    public boolean deleteLast() {
        if (current != 0) {                        // check if deque is not empty
            rear = (rear - 1 + k) % k;             // move rear pointer backward (skip old rear)
            current--;                             // decrease element count
            return true;
        } else {
            return false;                          // if deque is empty
        }
    }
    
    // Get front element
    public int getFront() {
        if (current != 0) {                        // if deque not empty
            return deq[front];                     // return element at front index
        } else {
            return -1;                             // if empty, return -1
        }
    }
    
    // Get rear element
    public int getRear() {
        if (current != 0) {                        // if deque not empty
            return deq[rear];                      // return element at rear index
        } else {
            return -1;                             // if empty, return -1
        }
    }
    
    // Check if deque is empty
    public boolean isEmpty() {
        if (current == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    // Check if deque is full
    public boolean isFull() {
        if (current == k) {
            return true;
        } else {
            return false;
        }
    }
}
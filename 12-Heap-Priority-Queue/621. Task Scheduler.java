// https://leetcode.com/problems/task-scheduler/description/

class Task{
    int time;
    int count;

    public Task(int time,int count){
        this.time = time;
        this.count = count;
    }
}


class Solution {
    public int leastInterval(char[] tasks, int n) {
        int time = 0;
        int[] freq = new int[26];

        PriorityQueue<Task> pq = new PriorityQueue<>(
            (a,b)-> Integer.compare(b.count,a.count)
        );

        Queue<Task> q = new LinkedList<>();

        for(char c : tasks){
            freq[c - 'A']++;
        }

        for(int i=0;i<26;i++){
            if(freq[i]>0) pq.add(new Task(0,freq[i]));
        }

        while(!pq.isEmpty() || !q.isEmpty()){
            if(!q.isEmpty() && time - q.peek().time > n){
                pq.add(q.remove());
            }

            if(!pq.isEmpty()){
                Task t = pq.poll();
                t.count--;
                t.time = time;
                if(t.count>0) q.add(t);
            }
            time++;
        }
        return time;
    }
}
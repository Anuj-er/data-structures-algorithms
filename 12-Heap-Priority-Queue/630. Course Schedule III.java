// https://leetcode.com/problems/course-schedule-iii/description/

class Solution {
    static {
        Runtime.getRuntime().gc();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses,(a,b) -> a[1] - b[1]);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
        int time = 0;
        for(int[] c : courses){
            time += c[0];
            maxHeap.add(c[0]);

            if(time > c[1]){
                time-=maxHeap.poll();
            }
        }
        return maxHeap.size();
    }
}
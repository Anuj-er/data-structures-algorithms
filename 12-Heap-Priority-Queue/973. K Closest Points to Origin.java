// https://leetcode.com/problems/k-closest-points-to-origin/description/

class Pair {
    int x, y;
    double distance;

    Pair(int x, int y) {
        this.x = x;
        this.y = y;
        /*
        The correct approach is to compute the distance using sqrt, but it is not required.
        Without using sqrt, the logic works the same because we are only comparing distances.
        For example:
        sqrt(4) vs sqrt(12) gives the same comparison result as 4 vs 12.
        After submission, the runtime was:
        - ~270 ms when using sqrt
        - ~36 ms without using sqrt
        Therefore, using squared distance is more efficient.
        */
        // this.distance = Math.sqrt(x * x + y * y);
        this.distance = x * x + y * y;
    }
}

class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (a, b) -> Double.compare(b.distance, a.distance));
        for (int[] p : points) {
            int x = p[0];
            int y = p[1];
            pq.add(new Pair(x, y));
            if (pq.size() > k)
                pq.poll();
        }

        int[][] ans = new int[k][2];
        int i = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            ans[i][0] = p.x;
            ans[i][1] = p.y;
            i++;
        }
        return ans;
    }
}
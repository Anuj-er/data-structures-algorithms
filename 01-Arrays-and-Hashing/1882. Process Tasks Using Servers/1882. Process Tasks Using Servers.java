1class Solution {
2    record Server(long at, int weight, int index) {  // at: the second it frees up; 0 while free, so free servers sort by weight, then index
3        Server freeAt(long second) { return new Server(second, weight, index); }
4    }
5
6    public int[] assignTasks(int[] servers, int[] tasks) {
7        Comparator<Server> order = Comparator.comparingLong(Server::at).thenComparingInt(Server::weight).thenComparingInt(Server::index);
8        PriorityQueue<Server> free = new PriorityQueue<>(order), busy = new PriorityQueue<>(order);
9        for (int i = 0; i < servers.length; i++) free.add(new Server(0, servers[i], i));
10
11        int[] ans = new int[tasks.length];
12        for (int s = 0; s < tasks.length; s++) {
13            while (!busy.isEmpty() && busy.peek().at() <= s)  // done by second s
14                free.add(busy.poll().freeAt(0));
15            Server server = (free.isEmpty() ? busy : free).poll();  // none free: the first to finish takes it
16            busy.add(server.freeAt(Math.max(server.at(), s) + tasks[s]));
17            ans[s] = server.index();
18        }
19
20        return ans;
21    }
22}
// https://leetcode.cn/problems/all-paths-from-source-to-target/description/

class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    private void dfs(int curr, int target, int[][] graph, List<Integer> path){

        if(curr == target){
            ans.add(new ArrayList<>(path));
            return;
        }

        for(int next : graph[curr]){
            path.add(next);
            dfs(next, target, graph, path);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        List<Integer> path = new ArrayList<>();
        path.add(0);

        dfs(0, graph.length - 1, graph, path);
        return ans;
    }
}

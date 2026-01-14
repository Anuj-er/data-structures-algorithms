// https://www.geeksforgeeks.org/problems/alien-dictionary/

class Solution {
    List<List<Integer>> adj;
    
    private void topoSort(int[] indeg, List<Integer> ans,boolean[] present){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        for(int i=0;i<26;i++){
            if(indeg[i] == 0 && present[i]){
                q.add(i);
            }
        }
        
        while(!q.isEmpty()){
            int curr = q.poll();
            ans.add(curr);
            for(int next : adj.get(curr)){
                if(--indeg[next] == 0 && present[next]){
                    q.add(next);
                }
            }
        }
    }
    
    // Adding elements in the Adjancy List from here;
    private boolean compare(String a,String b){
        int len = Math.min(a.length(),b.length());
        for(int i=0;i<len;i++){
            if(a.charAt(i) != b.charAt(i)){
                int u = a.charAt(i) - 'a';
                int v = b.charAt(i) - 'a';
                adj.get(u).add(v);
                return true;
            }
        }
        if(a.length() > b.length()) return false;
        return true;
    }
    
    public String findOrder(String[] words) {
        
        // finding total unique words first;
        boolean[] present = new boolean[26];
        int valid = 0;
        for(String word : words){
            for(char c : word.toCharArray()){
                if(!present[c - 'a']){
                    present[c - 'a'] = true;
                    valid++;
                }
            }
        }
        
        // Creating an Adjancy List
        adj = new ArrayList<>();
        for(int i = 0;i<26;i++) adj.add(new ArrayList<>());
        for(int i=0;i<words.length-1;i++){
            if(!compare(words[i],words[i+1])) return "";
        }
        
        
        // Creating Indegree Array;
        int[] indeg = new int[26];
        for(int i=0;i<26;i++){
            for(int v : adj.get(i)){
                indeg[v]++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        topoSort(indeg,ans,present);
        
        if(ans.size()!= valid) return "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<ans.size();i++){
            sb.append((char)(ans.get(i) + 'a'));
        }
        return sb.toString();
    }
}


// class Solution {
//     List<List<Integer>> adj;
    
//     private void topoSort(int[] indeg, List<Integer> ans,Set<Character> st){
//         ArrayDeque<Integer> q = new ArrayDeque<>();
        
//         for(int i=0;i<26;i++){
//             if(indeg[i] == 0 && st.contains( (char) (i + 'a'))){
//                 q.add(i);
//             }
//         }
        
//         while(!q.isEmpty()){
//             int curr = q.poll();
//             ans.add(curr);
//             for(int next : adj.get(curr)){
//                 if(--indeg[next] == 0 && st.contains( (char) (next + 'a'))){
//                     q.add(next);
//                 }
//             }
//         }
//     }
    
//     // Adding elements in the Adjancy List from here;
//     private boolean compare(String a,String b){
//         int len = Math.min(a.length(),b.length());
//         for(int i=0;i<len;i++){
//             if(a.charAt(i) != b.charAt(i)){
//                 int u = a.charAt(i) - 'a';
//                 int v = b.charAt(i) - 'a';
//                 adj.get(u).add(v);
//                 return true;
//             }
//         }
//         if(a.length() > b.length()) return false;
//         return true;
//     }
    
//     public String findOrder(String[] words) {
        
//         // finding total unique words first;
//         Set<Character> st = new HashSet<>();
//         for(String word : words){
//             for(char c : word.toCharArray()){
//                 st.add(c);
//             }
//         }
//         int valid = st.size();
        
//         // Creating an Adjancy List
//         adj = new ArrayList<>();
//         for(int i = 0;i<26;i++) adj.add(new ArrayList<>());
//         int len = words.length;
//         for(int i=0;i<len-1;i++){
//             if(!compare(words[i],words[i+1])) return "";
//         }
        
        
//         // Creating Indegree Array;
//         int[] indeg = new int[26];
//         for(int i=0;i<26;i++){
//             for(int v : adj.get(i)){
//                 indeg[v]++;
//             }
//         }
//         List<Integer> ans = new ArrayList<>();
//         topoSort(indeg,ans,st);
        
//         if(ans.size()!= valid) return "";
//         StringBuilder sb = new StringBuilder();
//         for(int i = 0;i<ans.size();i++){
//             sb.append((char)(ans.get(i) + 'a'));
//         }
//         return sb.toString();
//     }
// }











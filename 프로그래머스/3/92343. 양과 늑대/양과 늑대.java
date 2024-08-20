import java.util.*;

class Solution {
    static List<Integer>[] graph;
    static int[] info;
    static boolean[] visited;
    static int[][] edges;
    static int result = 0;
    
    static void dfs(int sheep, int wolf){
        if(sheep > wolf){
            result = Math.max(result, sheep);
        }
        else{
            return;
        }
        
        for(int[] edge : edges){
            int v = edge[0];
            int e = edge[1];
            if(!visited[e] && visited[v]){
                visited[e] = true;
                if(info[e] == 0){
                    dfs(sheep + 1, wolf);
                }
                else{
                    dfs(sheep, wolf + 1);
                }
                visited[e] = false;
            }
        }
    }
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.edges = edges;
        visited = new boolean[info.length];
        
        visited[0] = true;
        
        dfs(1, 0);
        
        return result;
    }
}
import java.util.*;
import java.io.*;

class Solution {
    static int n;
    static List<Integer>[] graph;
    static int[] before;
    static int[] after;
    static int[] visited;
    static Queue<Integer> queue = new LinkedList();
    
    static void bfs(){
        while(!queue.isEmpty()){
            int current = queue.poll();
            
            for(int next : graph[current]){
                if(visited[next] == 0){
                    if(visited[before[next]] == 2){
                        visited[next] = 2;
                        queue.offer(next);
                        if(visited[after[next]] == 1){
                            visited[after[next]] = 2;
                            queue.offer(after[next]);
                        }
                    }
                    else{
                        visited[next] = 1;
                    }
                }
            } 
        }
    }
    
    public boolean solution(int n, int[][] path, int[][] order) {
        
        this.n = n;
        graph = new ArrayList[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList();
        }
        
        for(int[] p : path){
            graph[p[0]].add(p[1]);
            graph[p[1]].add(p[0]);
        }
        
        before = new int[n];
        after = new int[n];
        
        for(int[] o : order){
            if(o[1] == 0) return false;
            before[o[1]] = o[0];
            after[o[0]] = o[1];
        }
        
        visited = new int[n];
        visited[0] = 2;      
        queue.offer(0);
        bfs();
        
        boolean answer = true;

        for(int i = 0; i < n; i++){
            if(visited[i] != 2){
                answer = false;
                break;
            } 
        }
        
        return answer;
    }
}



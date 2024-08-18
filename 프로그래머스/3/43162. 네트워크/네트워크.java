import java.util.*;

class Solution {
    static boolean[] visited;
    static int[][] matrix;
    static int n;
    
    static void bfs(int start){
        Queue<Integer> queue = new LinkedList();
        visited[start] = true;
        queue.offer(start);
        
        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int next = 0; next < n; next++){
                if(matrix[current][next] == 1 && !visited[next]){
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }
    
    public int solution(int n, int[][] computers) {
        this.n = n;
        matrix = computers;
        visited = new boolean[n];
        int answer = 0;
        
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                bfs(i);
                answer++;
            }
        }
        
        return answer;
    }
}
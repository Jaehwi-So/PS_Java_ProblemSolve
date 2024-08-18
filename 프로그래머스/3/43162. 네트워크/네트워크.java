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
    
    
    // Union-find
    static int[] parent;
    
    static void union(int n1, int n2){
        int k1 = find(n1);
        int k2 = find(n2);
        if(k1 > k2){
            parent[k1] = k2;
        }
        else if(k2 > k1){
            parent[k2] = k1;
        }
    }
    
    static int find(int n1){
        if(parent[n1] != n1){
            parent[n1] = find(parent[n1]);
        }
        return parent[n1];
        
    }
    
    public int solution(int n, int[][] computers) {
        this.n = n;
        
        // 1. BFS
        matrix = computers;
        visited = new boolean[n];
        int answer = 0;
        
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                bfs(i);
                answer++;
            }
        }
        
        // 2. Union-find
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j && computers[i][j] == 1){
                    union(i, j);
                }
            }
        }
        
        Set<Integer> set = new HashSet();
        for(int i = 0; i < n; i++){
            set.add(find(i));
        }
        answer = set.size();
        
        return answer;
    }
}
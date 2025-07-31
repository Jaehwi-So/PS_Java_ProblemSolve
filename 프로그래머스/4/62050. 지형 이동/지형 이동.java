import java.util.*;

class Solution {
    static int n;
    static int[][] matrix;
    static int[][] cost;
    static int h;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    
    static int bfs(int[] start){
        boolean[][] visited = new boolean[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                return a1[2] - a2[2];
            }
        });
        pq.offer(new int[]{start[0], start[1], 0});
        
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            
            if(visited[current[0]][current[1]]) continue;
            visited[current[0]][current[1]] = true;
            cost[current[0]][current[1]] = current[2];
            
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= n) continue;
                
                int value = 0;
                int diff = Math.abs(matrix[current[0]][current[1]] - matrix[row][col]);
                if(diff > h) value = diff;
                
                if(!visited[row][col]){
                    pq.offer(new int[]{row, col, value});
                }
            }
        }
        
        int result = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result += cost[i][j];
            }
        }
        
        // for(int[] line : cost){
        //     System.out.println(Arrays.toString(line));
        // }
        
        return result;
    }
    
    // 90000
    
    public int solution(int[][] land, int height) {
        matrix = land;
        n = land.length;
        h = height;
        cost = new int[n][n];
        
        for(int[] line : cost){
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        
        int min = Integer.MAX_VALUE;
        int[] start = new int[2];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(min > land[i][j]){
                    start[0] = i;
                    start[1] = j;
                    min = land[i][j];
                }
            }
        }
        
        int answer = bfs(start);
        return answer;
    }
}


/**

import java.util.*;

class Solution {
    static int n;
    static int[][] matrix;
    static int[][] cost;
    static int h;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    
    static int bfs(int[] start){
        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                return a1[2] - a2[2];
            }
        });
        pq.offer(new int[]{start[0], start[1], 0});
        cost[start[0]][start[1]] = 0;
        
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            if(cost[current[0]][current[1]] < current[2]) continue;
            
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= n) continue;
                
                int value = 0;
                int diff = Math.abs(matrix[current[0]][current[1]] - matrix[row][col]);
                if(diff > h) value = diff;
                
                if(cost[row][col] > current[2] + value){
                    cost[row][col] = current[2] + value;
                    pq.offer(new int[]{row, col, current[2] + value});
                }
            }
        }
        
        int result = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result = Math.max(result, cost[i][j]);
            }
        }
        
        for(int[] line : cost){
            System.out.println(Arrays.toString(line));
        }
        
        return result;
    }
    
    // 90000
    
    public int solution(int[][] land, int height) {
        matrix = land;
        n = land.length;
        h = height;
        cost = new int[n][n];
        
        for(int[] line : cost){
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        
        int min = Integer.MAX_VALUE;
        int[] start = new int[2];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(min > land[i][j]){
                    start[0] = i;
                    start[1] = j;
                    min = land[i][j];
                }
            }
        }
        
        int answer = bfs(start);
        return answer;
    }
}





**/
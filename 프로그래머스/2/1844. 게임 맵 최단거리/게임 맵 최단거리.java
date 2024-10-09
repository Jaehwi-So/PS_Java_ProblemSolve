import java.util.*;

class Solution {
    static int n;
    static int m;
    static boolean[][] visited;
    static int[] dx = {0 ,0 ,-1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int bfs(int[][] maps){
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(current[0] == n-1 && current[1] == m-1){
                return current[2];
            }
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || row >= n || col < 0 || col >= m){
                    continue;
                }
                if(maps[row][col] == 1 && !visited[row][col]){
                    visited[row][col] = true;
                    queue.offer(new int[]{row, col, current[2] + 1});
                }
            }
        }
        return -1;
    }
    public int solution(int[][] maps) {
        this.n = maps.length;
        this.m = maps[0].length;
        visited = new boolean[n][m];
        int answer = bfs(maps);
        return answer;
    }
}
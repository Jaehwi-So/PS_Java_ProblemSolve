import java.util.*;

class Solution {
    
    static int[][] matrix;
    static long[][][] dp;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static int n, m, s;
    
    static void bfs(){
        PriorityQueue<long[]> pq = new PriorityQueue(new Comparator<long[]>(){ //row, col, dist, cost
            public int compare(long[] a1, long[] a2){
                return Long.compare(a1[3], a2[3]);
            }
        });
        
        dp[0][0][0] = 0;
        pq.offer(new long[]{0, 0, 0, 0});
        
        while(!pq.isEmpty()){
            long[] current = pq.poll();
            int r = (int)current[0];
            int c = (int)current[1];
            int d = (int)current[2];
            if(dp[r][c][d] < current[3]) continue;
            
            for(int i = 0; i < 4; i++){
                int row = r + dy[i];
                int col = c + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m) continue;
                
                long time = current[3] + matrix[row][col];
                int dist = d + 1;
                if(matrix[row][col] == -1 || time > s) continue;
                if(dist > (n+1)*(m+1)) continue;
                
                if(dp[row][col][dist] > time){
                    dp[row][col][dist] = time;
                    pq.offer(new long[]{row, col, dist, time});
                }
            }
        }
    }
    
    public int[] solution(int m, int n, int s, int[][] time_map) {
        
        this.m = n;
        this.n = m;
        this.matrix = time_map;
        this.s = s;
        this.dp = new long[m][n][(n+1)*(m+1)+1];
        for(long[][] mat : dp){
            for(long[] line : mat){
                Arrays.fill(line, Long.MAX_VALUE);
            }
        }
        
        bfs();
        
        int moveDistance = 0;
        int sumOfTalkTime = 0;
        for(int i = 0; i < n*m; i++){
            if(dp[m-1][n-1][i] < Long.MAX_VALUE){
                moveDistance = i;
                sumOfTalkTime = (int)dp[m-1][n-1][i];
                break;
            }
        }
    
        int[] answer = new int[2];
        answer[0] = moveDistance;
        answer[1] = sumOfTalkTime;
      
        return answer;
    }
}
import java.util.*;

class Solution {
    static int n;
    static int m;
    static int dp[][][][]; //빨간 수레와 파란 수레의 해당 좌표일 때 최소 이동 필요 거리수
    static final int MAX = 10000000;
    
    static int[] mdry = {1, -1, 0, 0};
    static int[] mdrx = {0, 0, 1, -1};
    static int[] mdby = {1, -1, 0, 0};
    static int[] mdbx = {0, 0, 1, -1};

    static int[][] maze;
    static int[] start;
    static int[] end;
    static boolean[][][] v;
    static int result = MAX;

    
    static void operate(int[] pos, int count){   
        
        if(pos[0] == end[0] && pos[1] == end[1] && pos[2] == end[2] && pos[3] == end[3]){ 
            result = Math.min(result, count);
            return;
        }
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                int[] npos = Arrays.copyOf(pos, 4);

                boolean rend = true;
                boolean bend = true;
                
                if(end[0] != pos[0] || end[1] != pos[1]){
                    npos[0] = pos[0] + mdry[i];
                    npos[1] = pos[1] + mdrx[i];
                    rend = false;
                }
                if(end[2] != pos[2] || end[3] != pos[3]){
                    npos[2] = pos[2] + mdby[j];
                    npos[3] = pos[3] + mdbx[j];
                    bend = false;
                }

                if(npos[0] < 0 || npos[0] >= n || npos[1] < 0 || npos[1] >= m ||
                  npos[2] < 0 || npos[2] >= n || npos[3] < 0 || npos[3] >= m){
                    continue;
                }
                if(maze[npos[0]][npos[1]] == 5 || maze[npos[2]][npos[3]] == 5 ||
                   (npos[0] == npos[2] && npos[1] == npos[3])){ //벽이거나 같은 위치로 동시 이동
                    continue;
                }
                if(npos[0] == pos[2] && npos[1] == pos[3] && npos[2] == pos[0] && npos[3] == pos[1]){ //서로 위치 바꾸기
                    continue;
                }

                if((v[npos[0]][npos[1]][0] && !rend) || (v[npos[2]][npos[3]][1] && !bend)){ //이미 방문한 곳으로는 이동불가(도착점 제외)
                    continue;
                }

                v[pos[0]][pos[1]][0] = true;
                v[pos[2]][pos[3]][1] = true;

                operate(npos, count + 1); 
                
                v[pos[0]][pos[1]][0] = false;
                v[pos[2]][pos[3]][1] = false;

            }
        }
    }
    
    public int solution(int[][] maze) {
        
        this.n = maze.length;
        this.m = maze[0].length;
        this.dp = new int[n][m][n][m];
        this.maze = new int[n][m];
        this.v = new boolean[n][m][2];
        
        start = new int[4];
        end = new int[4];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(maze[i][j] == 1){
                    start[0] = i;
                    start[1] = j;
                }
                else if(maze[i][j] == 2){
                    start[2] = i;
                    start[3] = j;
                }
                else if(maze[i][j] == 3){
                    end[0] = i;
                    end[1] = j;
                }
                else if(maze[i][j] == 4){
                    end[2] = i;
                    end[3] = j;
                }
                if(maze[i][j] == 5){
                    this.maze[i][j] = 5;
                }
            }
        }
        
        for(int[][][] a : dp){
            for(int[][] b : a){
                for(int[] c : b){
                    Arrays.fill(c, -1);
                }
            }
        }
        
        boolean[][][] visited = new boolean[n][m][2];
        dp[end[0]][end[1]][end[2]][end[3]] = 0;
   
        operate(start, 0);
        if(result >= MAX || result == -1) result = 0;
        return result;
    }
}
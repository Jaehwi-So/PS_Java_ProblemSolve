import java.util.*;

class Solution {
    static int n = 0;
    static int[][] matrix;
    static boolean[] visited;
    static int[][][] position;
    static int result = Integer.MAX_VALUE;
    static boolean[][] cntVisited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int minDist = Integer.MAX_VALUE;
    
    static int getMoveCnt(int r1, int c1, int r2, int c2){
        minDist = Integer.MAX_VALUE;
        cntVisited = new boolean[4][4];
        dfs(r1, c1, r2, c2, 0);
        return minDist;
    }
    
    static void dfs(int r1, int c1, int r2, int c2, int cnt){
        if(cnt >= minDist){
            return;
        }
        if(r1 == r2 && c1 == c2){
            minDist = Math.min(minDist, cnt);
        }
        
        for(int i = 0; i < 4; i++){
            int r = r1 + dy[i];
            int c = c1 + dx[i];
            if(r < 0 || c < 0 || r >= 4 || c >= 4) continue;
            if(!cntVisited[r][c]){
                cntVisited[r][c] = true;
                dfs(r, c, r2, c2, cnt + 1);
                cntVisited[r][c] = false;
            }
        }
        
        
        for(int i = 0; i < 4; i++){
            int r = r1;
            int c = c1;
            if(i == 0){
                while(r != 0){
                    r += dy[i];
                    if(matrix[r][c] != 0) break;
                }
            }
            else if(i == 1){
                while(r != 3){
                    r += dy[i];
                    if(matrix[r][c] != 0) break;
                }
            }
            else if(i == 2){
                while(c != 0){
                    c += dx[i];
                    if(matrix[r][c] != 0) break;
                }
            }
            else if(i == 3){
                while(c != 3){
                    c += dx[i];
                    if(matrix[r][c] != 0) break;
                }
            }
           
            if(!cntVisited[r][c]){
                cntVisited[r][c] = true;
                dfs(r, c, r2, c2, cnt + 1);
                cntVisited[r][c] = false;
            }
        }
        
    
    }
    
    static void calc(int nowR, int nowC, int count, int step){
        if(count > result){
            return;
        }
        if(step == n){
            result = Math.min(result, count);
        }
        else{
            for(int i = 1; i <= n; i++){
                if(!visited[i]){
                    visited[i] = true;
                    int move1 = getMoveCnt(nowR, nowC, position[i][0][0], position[i][0][1]) + 
                        getMoveCnt(position[i][0][0], position[i][0][1], position[i][1][0], position[i][1][1]);
                    int move2 = getMoveCnt(nowR, nowC, position[i][1][0], position[i][1][1]) +
                        getMoveCnt(position[i][1][0], position[i][1][1], position[i][0][0], position[i][0][1]);
                    
                                        
                    matrix[position[i][0][0]][position[i][0][1]] = 0;
                    matrix[position[i][1][0]][position[i][1][1]] = 0;
                    

                    calc(position[i][0][0], position[i][0][1], count + move2 + 2, step + 1);
                    calc(position[i][1][0], position[i][1][1], count + move1 + 2, step + 1);

                    visited[i] = false;
                    matrix[position[i][0][0]][position[i][0][1]] = i;
                    matrix[position[i][1][0]][position[i][1][1]] = i;
                }
            }
        }
        
    }
    public int solution(int[][] board, int r, int c) {
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                n = Math.max(n, board[i][j]);
            }
        }
        
        visited = new boolean[n+1];
        cntVisited = new boolean[4][4];
        position = new int[n+1][2][2];
        matrix = board;
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                int index = board[i][j];
                if(index != 0){
                    if(!visited[index]){
                        position[index][0][0] = i;
                        position[index][0][1] = j;
                        visited[index] = true;
                    }
                    else{
                        position[index][1][0] = i;
                        position[index][1][1] = j;
                        visited[index] = false;
                    }
                    
                }
            }
        }
        
        
        // System.out.println(getMoveCnt(1, 0, 1, 0));
        // dfs(2, 0, 2, 3, 0);
        
        // System.out.println(getMoveCnt(1, 0, 2, 3));
        // System.out.println(getMoveCnt(2, 3, 0, 3));
        // System.out.println(getMoveCnt(0, 3, 3, 0));
        calc(r, c, 0, 0);
        
    
        return result;
    }
}
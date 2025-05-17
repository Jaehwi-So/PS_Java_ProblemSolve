import java.util.*;

class Solution {
    static int n;
    static int m;
    static int[][] matrix;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    
    static boolean impossible(int nrow, int ncol){
        boolean b = true;
        for(int i = 0; i < 4; i++){
            int row = nrow + dy[i];
            int col = ncol + dx[i];
            if(row < 0 || col < 0 || row >= n || col >= m){
                continue;
            }
            if(matrix[row][col] == 0){
                continue;
            }
            b = false;
        }
        return b;
        
    }
    
    static int[] dfs(int arow, int acol, int brow, int bcol){
        if(impossible(arow, acol)){
            return new int[]{0, 0};
        }
        
        if(arow == brow && acol == bcol){
            return new int[]{1, 1};
        }
        
        boolean win = false;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < 4; i++){
            int row = arow + dy[i];
            int col = acol + dx[i];
            if(row < 0 || col < 0 || row >= n || col >= m){
                continue;
            }
            if(matrix[row][col] == 0){
                continue;
            }
            matrix[arow][acol] = 0;
            int[] result = dfs(brow, bcol, row, col);
            matrix[arow][acol] = 1;
            if(result[0] == 0){   // 이길 수 있는 경우
                win = true;
                min = Math.min(min, result[1] + 1);
            } 
            else{ // 이길 수 없는 경우
                max = Math.max(max, result[1] + 1);
            }
            
        }
        if(win){ // 이길 수 있는 경우의 수가 1개라도 존재할 경우
            return new int[]{1, min};
        }
        else{
            return new int[]{0, max};
        }
        
    }
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.matrix = board;
        this.n = this.matrix.length;
        this.m = this.matrix[0].length;
        
        int[] answer = dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
        return answer[1];
    }
}


/**
1 3 5
0 1 3
1 3 5

6 4 2
4 2 0
6 4 2
**/
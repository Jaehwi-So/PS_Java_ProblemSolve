
import java.util.*;

class Solution {
    static int n;
    static int m;
    static int[][] matrix;
    static int[][][] dp;
    static int MOD = 20170805;
    static int[] dy = {0, -1};
    static int[] dx = {-1, 0};
    
    static int dfs(int row, int col, int dir){
        
        if(row < 0 || row >= n || col < 0 || col >= m || matrix[row][col] == 1){
            return 0;
        }
        

        int nrow = row + dy[dir];
        int ncol = col + dx[dir];
        
        if(dp[dir][row][col] == -1){
            dp[dir][row][col] = 0;
            
            if(matrix[nrow][ncol] == 2){
                dp[dir][row][col] += dfs(nrow, ncol, dir);   
            }
            
            else{
                dp[dir][row][col] += dfs(nrow, ncol, 0);
                dp[dir][row][col] %= MOD;
                dp[dir][row][col] += dfs(nrow, ncol, 1);
            }
                       
        }
        
        dp[dir][row][col] %= MOD;
        return dp[dir][row][col];
    }
    
    public int solution(int m, int n, int[][] cityMap) {
        this.n = m;
        this.m = n;
        n = this.n;
        m = this.m;
        
        this.matrix = cityMap;
        

        
        this.dp = new int[2][n][m];
        
        for(int[][] mat : dp){
            for(int[] line : mat){
                Arrays.fill(line, -1);
            }
        }
        
        int num = 1;
        for(int i = 0; i < n; i++){
            if(matrix[i][0] == 1){
                num = 0;
            }
            dp[1][i][0] = num;
            dp[0][i][0] = 0;
        }
        
        num = 1;
        for(int j = 0; j < m; j++){
            if(matrix[0][j] == 1){
                num = 0;
            }
            dp[0][0][j] = num;
            dp[1][0][j] = 0;
        }
        

        int answer = (dfs(n-1, m-1, 0) + dfs(n-1, m-1, 1)) % MOD;
        
//         for(int i = 0; i < 2; i++){
//             for(int[] line : dp[i]){
//                 System.out.println(Arrays.toString(line));
//             }
//             System.out.println();
//         }
        
        // int answer = 0;

        return answer;
    }
}

/**
[0, 2, 0, 0, 0, 2], 
[0, 0, 2, 0, 1, 0], 
[1, 0, 0, 2, 2, 0]



[0, 1, 0, 0, 0], 
[0, 1, 0, 1, 0], 
[0, 0, 0, 1, 0]
**/
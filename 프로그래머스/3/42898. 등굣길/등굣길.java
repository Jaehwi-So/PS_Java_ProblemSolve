import java.util.*;

class Solution {
    static int[][] matrix;
    static int[][] dp;
    static final int MOD = 1000000007;
    
    public int solution(int m, int n, int[][] puddles) {
        matrix = new int[n][m];
        dp = new int[n][m];
        
        for(int[] point : puddles){
            matrix[point[1]-1][point[0]-1] = 1;
        }
        
        for(int i = 0; i < m; i++){
            if(matrix[0][i] == 1) break;
            dp[0][i] = 1;
        }
        for(int i = 0; i < n; i++){
            if(matrix[i][0] == 1) break;
            dp[i][0] = 1;
        }
        
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(matrix[i][j] != 1){
                     dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
                } 
            }
        }
        
        // for(int[] line : dp){
        //     System.out.println(Arrays.toString(line));
        // }
        
        int answer = dp[n-1][m-1];
        return answer;
    }
}
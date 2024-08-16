import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int m = triangle[triangle.length-1].length;
        int[][] matrix = new int[n][m+1];
        int[][] dp = new int[n][m+1];
        for(int i = 0; i < n; i++){
            for(int j = 1; j <= triangle[i].length; j++){
                matrix[i][j] = triangle[i][j-1];
            }
        }
        
        dp[0][1] = matrix[0][1];
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= triangle[i].length; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + matrix[i][j];
            }
        }
        
        int result = 0;
        for(int j = 1; j <= m; j++){
            result = Math.max(result, dp[n-1][j]);
        }
        
        return result;
    }
}


/**
7
3 8
8 1 0
**/
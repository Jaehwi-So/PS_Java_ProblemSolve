import java.util.*;

class Solution {
    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;
        int[][] dp = new int[n][n];
        
        for(int[] line : dp){
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        
        for(int i = 0; i < n; i++){
            dp[i][i] = 0;
        }
        
        for(int l = 1; l < n; l++){
            for(int i = 0; i < n - l; i++){
                int j = i + l;
                for(int k = i; k < j; k++){
                    int r = matrix_sizes[i][0] * matrix_sizes[k][1] * matrix_sizes[j][1];
                    // System.out.println(i + " " + k + ", " + (k+1) + " " + j);
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + r);
                }
            }
        }
        
        // for(int[] line : dp){
        //     System.out.println(Arrays.toString(line));
        // }
        
        int answer = dp[0][n-1];
        return answer;
    }
}
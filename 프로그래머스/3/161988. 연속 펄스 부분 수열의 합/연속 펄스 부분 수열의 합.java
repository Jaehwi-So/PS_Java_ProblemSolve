import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        int[] seq1 = new int[n];
        int[] seq2 = new int[n];
        for(int i = 0; i < n; i++){
            if(i % 2 == 0){
                seq1[i] = sequence[i];
                seq2[i] = sequence[i] * -1;
            }
            else{
                seq1[i] = sequence[i] * -1;
                seq2[i] = sequence[i];
            }
        }
        
        long[][] dp = new long[2][n];
        long max = 0;
        
        dp[0][0] = seq1[0];
        dp[1][0] = seq2[0];
        
        max = Math.max(dp[0][0], dp[1][0]);
    
        for(int i = 1; i < n; i++){
            dp[0][i] = Math.max(dp[0][i-1] + seq1[i], seq1[i]);
            dp[1][i] = Math.max(dp[1][i-1] + seq2[i], seq2[i]);
            max = Math.max(max, dp[0][i]);
            max = Math.max(max, dp[1][i]);
        }
        
        return max;
    }
}
import java.util.*;

class Solution {
    public int solution(int n, int[] tops) {
        int mod = 10007;
        int[][] dp = new int[n+1][4];
        dp[0][0] = 1;
        for(int i = 1; i <= n; i++){
            int before = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2] + dp[i-1][3]) % mod;
            dp[i][0] = before;
            if(tops[i-1] == 1) dp[i][1] = before;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % mod;
            dp[i][3] = before;
        }
        
        
        int result = (dp[n][0] + dp[n][1] + dp[n][2] + dp[n][3]) % mod;
        
        return result;
        
        // 중 상 좌 우
        //
        // 1 1 1 1
        // 4 4 3 4
        // 14 0 11 11
        
        // 1 0 0 0
        // 1 0 1 1
        // 3 3 2 3
    }
}
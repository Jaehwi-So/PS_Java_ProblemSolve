import java.util.*;

class Solution {
    static final int MAX = Integer.MAX_VALUE;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        temperature += 10;
        t1 += 10;
        t2 += 10;
        if(temperature >= t1 && temperature <= t2){
            return 0;
        }
        boolean summer = temperature >= t2 ? true : false;
        
        int[][] dp = new int[onboard.length + 1][51];
        for(int[] line : dp){
            Arrays.fill(line, MAX);
        }
        dp[0][temperature] = 0;
        
        for(int i = 1; i <= onboard.length; i++){
            for(int j = 0; j <= 50; j++){
                if(dp[i-1][j] != MAX){
                    if(summer){
                        if(i < onboard.length && onboard[i] == 1){
                            if(j-1 >= t1 && j-1 <= t2) dp[i][j-1] = Math.min(dp[i][j-1], dp[i-1][j] + a);
                            if(j >= t1 && j <= t2) dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + b);
                            if(j+1 >= t1 && j+1 <= t2) dp[i][j+1] = Math.min(dp[i][j+1], dp[i-1][j]);
                        }
                        else{
                            if(j-1 >= 0) dp[i][j-1] = Math.min(dp[i][j-1], dp[i-1][j] + a);
                            if(j == temperature) dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
                            else dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + b);
                            if(j+1 <= temperature) dp[i][j+1] = Math.min(dp[i][j+1], dp[i-1][j]);
                        }

                    }
                    else{
                        if(i < onboard.length && onboard[i] == 1){
                            if(j-1 >= t1 && j-1 <= t2) dp[i][j-1] = Math.min(dp[i][j-1], dp[i-1][j]);
                            if(j >= t1 && j <= t2) dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + b);
                            if(j+1 >= t1 && j+1 <= t2) dp[i][j+1] = Math.min(dp[i][j+1], dp[i-1][j] + a);   
                        }
                        else{
                            if(j-1 >= temperature) dp[i][j-1] = Math.min(dp[i][j-1], dp[i-1][j]);
                            if(j == temperature) dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
                            else dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + b);
                            if(j+1 <= 50) dp[i][j+1] = Math.min(dp[i][j+1], dp[i-1][j] + a);    
                        }

                    }
                }
            }
        }
        
        int result = MAX;
        for(int i = 0; i <= 50; i++){
            result = Math.min(result, dp[onboard.length][i]);
        }
        
        
        return result;
    }
}


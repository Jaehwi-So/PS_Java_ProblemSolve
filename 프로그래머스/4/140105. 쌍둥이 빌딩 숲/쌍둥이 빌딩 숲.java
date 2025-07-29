import java.util.*;

class Solution {
    static final int MOD = 1000000007;
    static int[][] dp;
    
    static int operate(int index, int cnt){
        if(dp[index][cnt] == -1){
            long current = 0;
            
            long before = (index - 1) * 2;
            if(cnt > 1){
                current += operate(index - 1, cnt - 1); //왼쪽에 추가하는 경우
                current %= MOD;
            } 
            
            current += before * operate(index - 1, cnt); //왼쪽에 추가하지 않는 경우
            current %= MOD;
            
            dp[index][cnt] = (int)current;
        }
        return dp[index][cnt];
    }
    public int solution(int n, int count) {
        
        dp = new int[n+1][n+1];
        for(int[] line : dp){
            Arrays.fill(line, -1);
        }
        
        Arrays.fill(dp[1], 0);
        dp[1][1] = 1;
        
        operate(n, count);
        
        // System.out.println(dp[2][1]);
        // System.out.println(dp[3][1]);
        
        int answer = dp[n][count];
        return answer;
    }
}
import java.util.*;

class Solution {
    public int solution(int[] money) {
        int n = money.length + 1;
        int[] dp = new int[n];
        
        int result = Integer.MIN_VALUE;
        
        for(int k = 0; k < 2; k++){
            
            dp[1] = 0;
            if(k == 1) dp[1] = money[0];
            
            for(int i = 2; i < n; i++){
                dp[i] = Math.max(dp[i-1], dp[i-2] + money[i-1]);
                
                if(i == n-1){
                    if(k == 1) result = Math.max(result, dp[i-1]);
                    else result = Math.max(result, dp[i]);
                }
            }            
        }
        
        return result;
    }
}

//   O X
// A 1 2
// B 3 4 
// C 5 6
// 5 = max(4, 1)
// 6 = max(3, 4)
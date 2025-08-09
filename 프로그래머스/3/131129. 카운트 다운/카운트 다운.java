import java.util.*;

class Solution {
    static int[][] dp;
    static final int MAX = 1000000;
    

    static int[] operate(int index){
        if(dp[index][0] == MAX){
            for(int i = 1; i <= 20; i++){               
                if(index - i >= 0){
                    int[] cur = operate(index - i);
                    if(cur[0] + 1 < dp[index][0]){
                        dp[index][0] = cur[0] + 1;
                        dp[index][1] = cur[1] + 1;
                    }
                    else if(cur[0] + 1 == dp[index][0]){
                        dp[index][1] = Math.max(dp[index][1], cur[1] + 1);
                    }
                }
            
                for(int j = 2; j <= 3; j++){
                    int k = i*j;
                    if(index - k >= 0){
                        int[] cur = operate(index - k);
                        if(cur[0] + 1 < dp[index][0]){
                            dp[index][0] = cur[0] + 1;
                            dp[index][1] = cur[1];
                        }
                        else if(cur[0] + 1 == dp[index][0]){
                            dp[index][1] = Math.max(dp[index][1], cur[1]);
                        }
                    }
                }
                
                if(index - 50 >= 0){
                    int[] cur = operate(index - 50);
                    if(cur[0] + 1 < dp[index][0]){
                        dp[index][0] = cur[0] + 1;
                        dp[index][1] = cur[1] + 1;
                    }
                    else if(cur[0] + 1 == dp[index][0]){
                        dp[index][1] = Math.max(dp[index][1], cur[1] + 1);
                    }
                }
            }
        }
        return dp[index];
    }
    
    public int[] solution(int target) {
        
        dp = new int[target + 1][2];
        for(int i = 1; i <= target; i++){
            dp[i][0] = MAX;
            dp[i][1] = 0;
        }
    
        int[] answer = operate(target);
        return answer;
    }
}
import java.util.*;

class Solution {
    static int n;
    static int[] numbers;
    static int[][] dp;
    
    static int operate(int index, int first){
        if(index >= n) return 0;
        
        if(dp[index][first] == -1){
            dp[index][first] = 0;
            if(index == n - 1){
                if(first != 1) dp[index][first] = numbers[index];
            }
            else{
                if(index == 0){
                    dp[index][first] = Math.max(numbers[index] + operate(index + 2, 1), dp[index][first]);
                } 
                else{
                    dp[index][first] = Math.max(numbers[index] + operate(index + 2, first), dp[index][first]);
                }
                dp[index][first] = Math.max(operate(index + 1, first), dp[index][first]);
            }
            
        }
        return dp[index][first];
        
    }
    
    public int solution(int sticker[]) {
        numbers = sticker;
        n = sticker.length;
        dp = new int[n+1][2];
        for(int[] line : dp){
            Arrays.fill(line, -1);
        }
        
    
        int answer = operate(0, 0);

        return answer;
    }
}
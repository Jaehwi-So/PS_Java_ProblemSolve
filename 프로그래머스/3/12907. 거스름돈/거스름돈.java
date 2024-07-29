import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int[][] dp = new int[money.length][n+1];
        for(int i = 1; i <= n; i++){
            if(i % money[0] == 0){
                dp[0][i] = 1;
            }
        }
        
        for(int i = 1; i < money.length; i++){
            for(int j = 1; j <= n; j++){
                int value = money[i];
                dp[i][j] = dp[i-1][j];
                if(j == value){
                    dp[i][j] = dp[i-1][j] + 1;
                }
                else if(j > value){
                    dp[i][j] += dp[i][j-value];
                }
                
            }
        }
        
        // for(int[] line : dp){
        //     System.out.println(Arrays.toString(line));
        // }
        
        int answer = dp[money.length - 1][n];
        return answer;
    }
}

/**
  1 2 3 4 5 6 7 8 9 10 11 (i)
1 1 1 1 1 1 1 1 1 1 1 1
2 1 2 2 3 3 4 4 5 5 6 6
5 1 2 2 3 4 5 6 7 8 10


5 5
5 1 1 1 1 1
5 1 1 1 2
5 1 2 2



**/
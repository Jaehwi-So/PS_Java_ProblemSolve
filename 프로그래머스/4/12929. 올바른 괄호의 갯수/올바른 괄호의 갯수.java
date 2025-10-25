import java.util.*;

class Solution {
    static int n;
    static int[][][] dp;
    
    static int operate(int seq, int left, int right){
        if(dp[seq][left][right] == -1){
            dp[seq][left][right] = 0;
            if(seq == n * 2){
                if(left == right) dp[seq][left][right] = 1;
            }
            else{
                if(left > right){
                    dp[seq][left][right] += operate(seq + 1, left, right + 1);
                }
                if(left < n){
                    dp[seq][left][right] += operate(seq + 1, left + 1, right);
                }
            }
        }
        return dp[seq][left][right];
    }
    public int solution(int n) {
        this.n = n;
        this.dp = new int[(n*2)+1][n+1][n+1];
        
        for(int[][] mat : dp){
            for(int[] line : mat){
                Arrays.fill(line, -1);
            }
        }
        
        int answer = operate(0, 0, 0);
        return answer;
    }
}
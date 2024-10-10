import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
                
        int maxA = 0;
        int maxC = 0;
        
        int maxRateA = 0;
        int maxRateC = 0;
        
        for(int[] p : problems){      
            int condA = p[0];
            int condC = p[1];
            maxA = Math.max(maxA, condA);
            maxRateA = Math.max(maxRateA, p[2]);
            maxC = Math.max(maxC, condC);     
            maxRateC = Math.max(maxRateA, p[3]);
        }
        
        int aLength = 500;
        int cLength = 500;
        
        
        
        int[][] dp = new int[aLength+1][cLength+1];
        for(int i = 0; i <= aLength; i++){
            for(int j = 0; j <= cLength; j++){
                int a = (i - alp);
                int c = (j - cop);
                if(a < 0) a = 0;
                if(c < 0) c = 0;
                dp[i][j] = a + c;
                
                for(int[] p : problems){
                    int condA = p[0];
                    int condC = p[1];
                    int rateA = p[2];
                    int rateC = p[3];
                    int durate = p[4];
                    if(i >= condA + rateA && j >= condC + rateC){
                         dp[i][j] = Math.min(dp[i-rateA][j-rateC] + durate, dp[i][j]);
                    }
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for(int i = maxA; i <= aLength; i++){
            for(int j = maxC; j <= cLength; j++){
                answer = Math.min(dp[i][j], answer);
            }
        }
           
        return answer;
    }
}


import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int n = want.length;
        Map<String, Integer> map = new HashMap();
        for(int i = 0; i < n; i++){
            map.put(want[i], i);
        }
        
        int len = discount.length;
        int[][] dp = new int[len+1][n];
        
        for(int i = 1; i <= len; i++){
            for(int j = 0; j < n; j++){
                dp[i][j] = dp[i-1][j];
            }
            if(map.containsKey(discount[i-1])){
                 dp[i][map.get(discount[i-1])]++;
            }          
        }
        
        int answer = 0;
        for(int i = 10; i <= len; i++){
            boolean success = true;
            for(int j = 0; j < n; j++){
                if(dp[i][j] - dp[i-10][j] < number[j]){
                    success = false;
                    break;
                }
            }
            if(success){
                answer++;
            }
        }

        return answer;
    }
}
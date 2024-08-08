import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int length = sequence.length + 1;
        int[] dp = new int[length];
        
        dp[0] = 0;
        for(int i = 1; i < length; i++){
            dp[i] = dp[i-1] + sequence[i-1];
        }
        

        int next = 0;
        int[] answer = new int[2];
        int size = Integer.MAX_VALUE;
        int start = 0;
        int end = 1;
        while(start < end && end < length){
            if(dp[end] - dp[start] == k){
                if(end - start + 1 < size){
                    size = end - start + 1;
                    answer[0] = start;
                    answer[1] = end - 1;
                }
                start++;
            }
            else if(dp[end] - dp[start] < k){
                end++;
            }
            else{
                start++;
            }
        }
       
        
        return answer;
    }
}
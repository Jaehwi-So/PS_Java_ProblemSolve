import java.util.*;

class Solution {
    static Set<Integer>[] dp;
    static int n;
    static int INF = 100000000;
    
    
    public int solution(int N, int number) {
        dp = new HashSet[9];
        n = N;
        
        int before = 0;
        int answer = -1;
        
        for(int i = 1; i < 9; i++){
            dp[i] = new HashSet<Integer>();
            int current = (before * 10) + n;
            if(current == number){
                return i;
            }
            dp[i].add(current);
            before = current;
        }
        

        for(int i = 2; i <= 8; i++){
            for(int j = 1; j < i; j++){
                for(int k : dp[j]){
                    for(int l : dp[i - j]){
                        dp[i].add(k + l);
                        dp[i].add(k - l);
                        dp[i].add(k * l);
                        if(k == 0 || l == 0){
                            continue;
                        }
                        dp[i].add(k / l);
                    }
                }
            }
            if(dp[i].contains(number)){
                answer = i;
                break;
            }
        }

        
        return answer;
    }
}
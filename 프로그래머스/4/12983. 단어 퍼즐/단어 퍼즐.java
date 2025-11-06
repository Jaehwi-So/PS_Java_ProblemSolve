import java.util.*;

class Solution {
    static int n;
    static Set<String> set;
    static int[] dp;
    static String str;
    static final int MAX = 20001;
    
    static int operate(int index){
        if(index == n) return 0;
        if(dp[index] == -1){
            dp[index] = 0;
            int min = MAX;
            for(int i = 1; i <= 5; i++){
                if(index + i > n) break;
                String s = str.substring(index, index + i);
                if(set.contains(s)){
                    min = Math.min(min, 1 + operate(index + i));
                }
            }
            dp[index] = min;
        }
        return dp[index];
    }
    
    public int solution(String[] strs, String t) {
        this.n = t.length();
        this.set = new HashSet();
        this.dp = new int[n];
        this.str = t;
        
        for(String s : strs){
            set.add(s);
        }

        Arrays.fill(dp, -1);
        
        int answer = operate(0);
        if(answer == MAX) answer = -1;

        return answer;
    }
}
import java.util.*;

class Solution {
    static int result = 0;

    static int dfs(int n, int plus, int multi){
        if(n < 1 || multi * 2 < plus) return 0;
        else if(n == 1 && plus == 1 && multi == 0) return 1;
        else if(n == 2 && plus == 2 && multi == 0) return 1;
        else if(n == 3 && plus == 0 && multi == 1) return 1;
        else if(n == 4 && plus == 1 && multi == 1) return 1;
        else if(n == 5 && plus == 2 && multi == 1) return 1;
        else{
            int count = 0;
            for(int i = 0; i <= plus; i++){
                if(((n-i) > 0) && ((n-i) % 3 == 0)){
                    count += dfs((n-i) / 3, plus - i, multi - 1);
                }
            }
            return count;
        } 
    }
    
    public int solution(int n) {
        int multi = (int)(Math.log(n) / Math.log(3));
        int plus = multi * 2;
        int answer = dfs(n - 2, plus - 2, multi);
        return answer;
    }
}
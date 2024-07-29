import java.util.*;

class Solution {
    
    public int[] solution(int n, int s) {
        int[] result;
        
        if(n > s){
            result = new int[]{-1};
        }
        else{
            result = new int[n];
            int quot = s / n;
            int mod = s % n;
            
            Arrays.fill(result, quot);
            
            for(int i = n - 1; i >= 0; i--){
                if(mod <= 0){
                    break;
                }
                result[i]++;
                mod--;
            }
        }
        
        return result;
    }
}
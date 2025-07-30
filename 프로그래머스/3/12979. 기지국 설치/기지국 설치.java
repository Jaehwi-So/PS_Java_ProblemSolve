import java.util.*;

class Solution {
    static int n, w;
    static int[] stations;
    
    static int getResult(){
        int start = 1;
        int end = -1;
        int number = 0;
        for(int i = 0; i < stations.length; i++){
            int s = stations[i];
            if(s > w + 1){
                end = s - w - 1;
                int range = (end - start) + 1;
                int div = w+w+1;
                int calc = (range + div - 1) / div;
                number += calc;
            }
            start = s + w + 1;
        }
        
        if(start <= n){
            int range = (n - start) + 1;
            int calc = (int)Math.ceil((double)range / (w+w+1));
            number += calc;
        }
        
        return number;
    }
    
  
    
    public int solution(int n, int[] stations, int w) {
        this.n = n;
        this.w = w;
        this.stations = stations;
        
        int answer = getResult();

        return answer;
    }
}
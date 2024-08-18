import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        boolean[] R = new boolean[n+1];
        Arrays.sort(lost);
        for(int i : reserve){
            R[i] = true;
        }
        
        for(int i = 0; i < lost.length; i++){
            if(R[lost[i]]){
                R[lost[i]] = false;
                lost[i] = -1;
            }
        }
        
        int result = n;
        for(int k : lost){
            if(k == -1){
                continue;
            }
            
            if(k-1 > 0 && R[k-1]){
                R[k-1] = false;
            }
            else if(k+1 <= n && R[k+1]){
                R[k+1] = false;
            }
            else{
                result--;
            }
        }
        return result;
    }
}
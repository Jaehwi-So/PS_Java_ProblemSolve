import java.util.*;

class Solution {
    public int solution(int[] cookie) {
        int n = cookie.length;
        
        int max = 0;
        
        for(int pivot = 0; pivot < n - 1; pivot++){
            int left = pivot;
            int right = pivot + 1;
            
            int lsum = cookie[left];
            int rsum = cookie[right];
            while(true){
                if(lsum == rsum){
                    max = Math.max(lsum, max);
                }
                if(lsum > rsum){
                    if(right + 1 >= n) break;
                    else{
                        right++;
                        rsum += cookie[right];
                    }                    
                }
                
                else{
                    if(left - 1 < 0) break;
                    else{
                        left--;
                        lsum += cookie[left];
                    }                    
                }
                
            }
        }
        
        // System.out.println(max);
        return max;
        
    }
}

/**
1 [1 2] 3
**/
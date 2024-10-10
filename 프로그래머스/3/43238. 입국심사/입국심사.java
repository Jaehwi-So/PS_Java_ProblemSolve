import java.util.*;


class Solution {
    
    static long calc(long t, int[] times){
        long sum = 0;
        for(int i : times){
            sum += t / i;
        }
        return sum; //최대 N
    }
    
    static long binarySearch(long n, int[] times){
        long left = 0;
        long right = n * times[times.length - 1];
        
        while(left < right){
            long mid = (left / 2) + (right / 2);
            if(calc(mid, times) < n){
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        
        return right;
    }
    
    public long solution(int n, int[] times) {
        long answer = binarySearch(n, times);
        return answer;
    }
}


// 7 10 14 20 21
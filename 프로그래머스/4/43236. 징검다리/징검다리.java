import java.util.*;

class Solution {
    
    static int len;
    static int[] rocks;
    static int max;

    // 0 2 11 14 17 21 25
    static int calc(int distance){
        int result = 0;
        int start = 0;
        for(int i = 0; i < len; i++){
            if(rocks[i] - start < distance) result++;
            else start = rocks[i];
        }
        
        if(max - start < distance) result++;
        return result;
    }
    
    static int binarySearch(int n){        
        // n = 5인거 중 최대 distance
        int left = 0;
        int right = max + 1;
        // int right = 5;
        
        // 5 5 5 6 7
        while(left < right){
            int mid = (left + right) / 2;
            int count = calc(mid);
            // int count = array[mid];
            if(count > n){
                right = mid;
            }
            else{
                left = mid + 1;
            }
        }
        return right - 1;
    }
    
    public int solution(int distance, int[] rocks, int n) {
        
        Arrays.sort(rocks);
        
        this.rocks = rocks;
        this.len = rocks.length;
        this.max = distance;

        int result = binarySearch(n);

    
        return result;
        
        
    }
}

//  0  2 11 14 17 21  25
//    2 9 3 3 4 4
//      11 3 3 4 4


/**

    2  7 11 14 17 23  25
    11-14
    14 del    2 7 17 23 
    11 del.   2 7 14 17
    
**/ 
    
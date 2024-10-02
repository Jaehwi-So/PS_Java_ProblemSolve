import java.util.*;


class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        long distance = 0;
        int dBag = 0;
        int pBag = 0;
        for(int i = n-1; i >= 0; i--){
            dBag += deliveries[i];
            pBag += pickups[i];
            while(dBag > 0 || pBag > 0){
                dBag -= cap;
                pBag -= cap;
                distance += (i + 1) * 2;
            }
        }
        
        return distance;
    }
}
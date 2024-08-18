import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, new Comparator<int[]>(){
            public int compare(int[] r1, int[] r2){
                return r1[0] - r2[0];
            }
        });
        
        int min = Integer.MAX_VALUE;
        int result = 1;
        for(int i = 0; i < routes.length; i++){
            int[] route = routes[i];
            min = Math.min(route[1], min);
            if(i < routes.length - 1 && min < routes[i+1][0]){
                result++;
                min = Integer.MAX_VALUE;
            }
        }
            
            
        return result;
    }
}
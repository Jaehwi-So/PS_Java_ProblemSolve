import java.util.*;
class Solution {
    public int solution(int[] heights) {
        int n = heights.length;
        Arrays.sort(heights);
    
        // 0 1 2 3
        
        int answer = 0;
        List<Integer> list = new ArrayList();
        for(int i = 0; i < n/2; i++){
            int a = heights[i];
            int b = heights[(n/2) + i];
            list.add(b-a);
        }
        
        // b1 a1 b2 a2
        
        // 11 4 11 6
        
        
        if(n % 2 == 1){
            int k = heights[n-1];
            list.add(k - heights[(n/2)]);
            Collections.sort(list);
            answer = list.get(1);
        }
        else{
            Collections.sort(list);
            answer = list.get(0);
        }
        

        return answer;
    }
}
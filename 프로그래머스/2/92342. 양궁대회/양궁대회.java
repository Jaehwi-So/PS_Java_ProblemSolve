import java.util.*;

class Solution {
    static int[] info;
    static int[] visited;
    static int[] answer;
    static int max = 0;
    static void calc(int arrow, int lion, int apeach){
        if(arrow == 0){
            if(max < lion - apeach){
                max = lion - apeach; 
                answer = Arrays.copyOf(visited, answer.length);
            }
        }
        
        
        else{
            
            visited[info.length - 1]++;
            calc((arrow - 1), lion, apeach);
            visited[info.length - 1]--;
            
            for(int i = info.length - 2; i >= 0; i--){
                if(visited[i] == 0 && arrow >= info[i] + 1){
                    visited[i] = info[i] + 1;
                    if(info[i] == 0){
                        calc(arrow - (info[i] + 1), lion + (10 - i), apeach);
                    }
                    else{
                        calc(arrow - (info[i] + 1), lion + (10 - i), apeach - (10 - i));
                    }
                    visited[i] = 0;
                }
            }
        }
        
        
        

        
    }
    public int[] solution(int n, int[] info) {
        this.info = info;
        this.visited = new int[info.length];
        this.answer = new int[info.length];
        int apeach = 0;
        for(int i = 0; i < info.length; i++){
            if(info[i] > 0){
                apeach += (10 - i);
            }
        }
        calc(n, 0, apeach);
        if(max == 0){
            return new int[]{-1};
        }
        return answer;
    }
}
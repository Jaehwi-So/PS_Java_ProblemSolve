import java.util.*;

class Solution {
    static int[] next;
    static boolean[] visited;
    
    static int set(int num){
        if(!visited[num]){
            visited[num] = true;
            return set(next[num]) + 1;
        }
        else{
            return 0;
        }
    }
    public int solution(int[] cards) {
        int n = cards.length;
        next = new int[n+1];
        visited = new boolean[n+1];
        for(int i = 0; i < n; i++){
            next[i+1] = cards[i];
        }
        
        List<Integer> result = new ArrayList();
        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                result.add(set(i));
            }
        }
        
        Collections.sort(result, Collections.reverseOrder());
        int answer = 0;
        if(result.size() > 1){
            answer = result.get(0) * result.get(1);
        }
        
        return answer;
    }
}


// 1 2 3 4 5 6 7 8
// 1 4 7 8 2 5 6 3
import java.util.*;

class Solution {
    
    static Queue<int[]> queue = new LinkedList();
    
    static void move(int n, int start, int dest, int tmp){
        if(n == 1){
            queue.offer(new int[]{start, dest});
            return;
        }
        
        move(n-1, start, tmp, dest);
        queue.offer(new int[]{start, dest});
        move(n-1, tmp, dest, start);
        
    }
    public int[][] solution(int n) {

        move(n, 1, 3, 2);
        int[][] answer = new int[queue.size()][2];
        for(int i = 0; i < answer.length; i++){
            answer[i] = queue.poll();
        }
        return answer;
    }
}
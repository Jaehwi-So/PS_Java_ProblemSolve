import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for(int i = 0; i < works.length; i++){
            pq.offer(works[i]);
        }
        
        for(int i = 0; i < n; i++){
            if(pq.peek() == 0) break;
            pq.offer(pq.poll() - 1);
        }
        
        long answer = 0;
        while(!pq.isEmpty()){
            int cur = pq.poll();
            answer += (long)Math.pow(cur, 2);
        }
        

        return answer;
    }
}


// 1 4 9 16 25
// 3 5 7 9 

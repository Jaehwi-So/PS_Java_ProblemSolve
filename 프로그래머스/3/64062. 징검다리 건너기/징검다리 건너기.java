import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int n = stones.length;
        
        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                return a2[0] - a1[0];
            }
        });
        
        for(int i = 0; i < k - 1; i++){
            pq.offer(new int[]{stones[i], i});
        }

        int min = Integer.MAX_VALUE;  //k개 윈도우의 최댓값들의 -> 최소
        
        for(int i = k - 1; i < n; i++){
            pq.offer(new int[]{stones[i], i});
            while(pq.peek()[1] <= i - k){
                pq.poll();
            }
            min = Math.min(pq.peek()[0], min);
            
            // System.out.println(pq.peek()[0]);
        }
        
        
        int answer = min;
        return answer;
    }
}

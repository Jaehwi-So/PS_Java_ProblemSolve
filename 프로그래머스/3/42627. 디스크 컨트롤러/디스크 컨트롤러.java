import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                return a1[0] - a2[0];
            }
        });
        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                return a1[1] - a2[1];
            }
        });
        int i = 0;
        int t = 1000001;
        int idx = 0;
        int result = 0;
        while(i < t){
            if(idx == jobs.length && pq.isEmpty()){
                break;
            }
            while(idx < jobs.length && jobs[idx][0] <= i){
                pq.offer(jobs[idx]);
                idx++;
            }
            if(!pq.isEmpty()){
                int[] current = pq.poll();
                result += i - current[0] + current[1];
                i += current[1];
            }
            else{
                i++;
            }
        }
        int answer = result / jobs.length;
        return answer;
    }
}
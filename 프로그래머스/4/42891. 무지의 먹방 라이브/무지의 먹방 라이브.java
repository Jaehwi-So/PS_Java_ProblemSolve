import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        
        PriorityQueue<long[]> pq = new PriorityQueue(new Comparator<long[]>(){
            public int compare(long[] a1, long[] a2){
                return Long.compare(a1[0], a2[0]);
            }
        });
        
        long total = 0;
        
        for(int i = 0; i < food_times.length; i++){
            long[] times = new long[2];
            times[0] = food_times[i];
            times[1] = i+1;
            total += times[0];
            pq.offer(times);
        }
        
        if(total <= k){
            return -1;
        }
        
    
        long current = k;
        long amount = 0;
        
        while(!pq.isEmpty()){
            long left = pq.peek()[0] - amount;
            if(left <= 0){
                pq.poll();
            }             
            else if(left * pq.size() <= current){
                current -= left * pq.size();
                amount += left;
                // System.out.println(current);
                pq.poll();
            }
            else{
                break;
            }
        }
        
        
        List<long[]> list = new ArrayList(pq);
        Collections.sort(list, new Comparator<long[]>(){
            public int compare(long[] a1, long[] a2){
                return Long.compare(a1[1], a2[1]);
            }
        });
        
        long[] result = list.get((int)(current % list.size()));
        // System.out.println(Arrays.toString(result));
        
        
        int answer = (int)result[1];
        return answer;
    }
}

// 10000000000000 -> 10조
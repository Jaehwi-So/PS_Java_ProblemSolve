import java.util.*;

// 마지막 등장시기 저장, 제일 처음 등장 PQ, 종류 일치하면 제거
class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap();
        int n = gems.length;
        int len = 0;
        for(int i = 0; i < n; i++){
            if(!map.containsKey(gems[i])){
                map.put(gems[i], len);
                len++;
            }
        }
        
        int count = 0;
        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                return a1[1] - a2[1];
            }
        });
        
        int[] last = new int[len];
        Arrays.fill(last, -1);
        
        Set<Integer> set = new HashSet();
        
        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        
        for(int i = 0; i < n; i++){
            int type = map.get(gems[i]);
            set.add(type);
            
            int sequence = i + 1;
            
            last[type] = sequence;
            pq.offer(new int[]{type, sequence});
            
            // System.out.println("add " + index + " " + sequence);
            
            while(!pq.isEmpty()){
                int[] current = pq.peek();
                if(last[current[0]] > current[1]){
                    pq.poll();
                    // System.out.println("poll " + current[0] + " " + current[1]);
                }
                else{
                    break;
                }
            }
            
            if(set.size() == len){
                int length = (sequence - pq.peek()[1]) + 1;
                if(min > length){
                    min = length;
                    start = pq.peek()[1];
                    end = sequence;
                }
                // System.out.println("calc" + sequence + "-" + pq.peek()[1] + " : " + length);
            }
        }
        
        // System.out.println(map);
        // System.out.println(Arrays.toString(last));
        
        int[] answer = new int[]{start, end};
        return answer;
        
    }
}
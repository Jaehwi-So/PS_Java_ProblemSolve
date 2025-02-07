import java.util.*;


class Consult implements Comparable<Consult>{
    int start;
    int durate;
    int type;
    public Consult(int start, int durate, int type){
        this.start = start;
        this.durate = durate;
        this.type = type;
    }
    public int compareTo(Consult c){
        if(this.start == c.start) return this.durate - c.durate;
        return this.start - c.start;
    }
}
class Solution {
    static List<Consult>[] consults;
    
    static List<int[]> calc(int n, int k, int idx){
        List<int[]> result = new ArrayList();
        if(k == idx){
            int[] arr = new int[k+1];
            arr[idx] = n;
            result.add(arr);
        }
        else{
            for(int i = 1; i <= n - (k - idx); i++){
                List<int[]> next = calc(n-i, k, idx+1);
                for(int[] nxt : next){
                    nxt[idx] = i;
                    result.add(nxt);
                }
            }
        }
        return result;
    }
    
    static int operate(int mentor, List<Consult> consults){
        PriorityQueue<Integer> pq = new PriorityQueue();
        for(int i = 0; i < mentor; i++){
            pq.offer(0);
        }
        
        int time = 0;
        
        for(Consult c : consults){
            Integer next = pq.poll();
            if(next <= c.start){
                pq.offer(c.start + c.durate);
            }
            else{
                time += next - c.start;
                pq.offer(next + c.durate);
            }
            
        }
        
        
        return time;
    }
    public int solution(int k, int n, int[][] reqs) {
        
        List<int[]> cases = calc(n, k, 1);
        
        consults = new ArrayList[n+1];
        for(int i = 1; i <= k; i++){
            consults[i] = new ArrayList();
        }
        
        for(int[] r : reqs){
            consults[r[2]].add(new Consult(r[0], r[1], r[2]));
        }
        
        for(int i = 1; i <= k; i++){
            Collections.sort(consults[i]);
        }
        
        int result = Integer.MAX_VALUE;
        
        
        for(int[] c : cases){
            int sum = 0;
            for(int i = 1; i <= k; i++){
                int num = operate(c[i], consults[i]);
                sum += num;
                // System.out.print(num + " ");
            }
            // System.out.println(sum);
            result = Math.min(sum, result);
        }

        
        int answer = result;
        return answer;
    }
}

//대기 시간

/**
도착 시작 종료  대기
5   5   60    0
10  60  150   50
20  150 190   130
50  190 235   140
100 235 285   135

5   5   60    0
20  60  100   40
50 100  150   50


10 10   100   0
100 100 150   0


총 대기 = 455

**/
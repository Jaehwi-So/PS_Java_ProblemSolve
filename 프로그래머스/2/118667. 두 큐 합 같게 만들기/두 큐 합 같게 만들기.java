import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        
        Deque<int[]> q1 = new LinkedList();
        Deque<int[]> q2 = new LinkedList();
        long sum1 = 0;
        long sum2 = 0;
        
        int idx = 0;
        int[] mem = new int[4];

        for(int ele : queue1){
            q1.offer(new int[]{idx++, ele});
            sum1 += ele;
        }
        for(int ele : queue2){
            q2.offer(new int[]{idx++, ele});
            sum2 += ele;
        }
        
        if((sum1 + sum2) % 2 != 0){
            return -1;
        }
        
        mem[0] = q1.peek()[0];
        mem[1] = q1.peekLast()[0];
        mem[2] = q2.peek()[0];
        mem[3] = q2.peekLast()[0];
        
        int answer = 0;
        int k = queue1.length;
        int max = (k*4);
        while(sum1 != sum2){
            if(sum1 > sum2){
                int[] p = q1.poll();
                int num = p[1];
                q2.offer(p);
                sum1 -= num;
                sum2 += num;
            }
            else{
                int[] p = q2.poll();
                int num = p[1];
                q1.offer(p);
                sum1 += num;
                sum2 -= num;
            }
            answer++;
            if(answer > max){
                return -1;
            }
            if(!q1.isEmpty() && !q2.isEmpty()){
                if(mem[0] == q1.peek()[0] && mem[1] == q1.peekLast()[0] &&
                    mem[2] == q2.peek()[0] && mem[3] == q2.peekLast()[0]){
                    return -1;
                }                
            } 
            else{
                return -1;
            }         
        }
        

        return answer;
    }
}


/**
  4 6 5 1 3 2 7 2  + 4 (k + (k*2) - 1 + (k-1)) * k

4 6 5 1 3 2 7   2  + 7

1 3 2 7         2 4 6 5  +3
**/
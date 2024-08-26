import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        
        int[] D = new int[n+1];
        List<Integer>[] graph = new ArrayList[n+1];
        boolean[] light = new boolean[n+1];

        for(int i = 0; i <= n; i++){
            graph[i] = new ArrayList();
        }
        
        for(int[] e : lighthouse){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
            D[e[0]]++;
            D[e[1]]++;
        }
        

        Queue<Integer> queue = new LinkedList();
        for(int i = 1; i <= n; i++){
            if(D[i] == 1){
                queue.offer(i);
            }
        }
        
        int count = 0;
        while(!queue.isEmpty()){
            int k = queue.poll();
            D[k] = 0;
            for(int next : graph[k]){
                if(!light[k] && !light[next]){
                    light[next] = true;
                    count++;
                }                
                if(D[next] > 1){
                    D[next]--;

                    if(D[next] == 1){
                        queue.offer(next);
                    }
                } 
            }
        }
        
        
        
        return count;
    }
}
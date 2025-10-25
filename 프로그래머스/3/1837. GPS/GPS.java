import java.util.*;

class Solution {
    static int n, k;
    static List<Integer>[] graph;
    static int[] path;
    static int[][] dp;
    
    static void bfs(){
        dp = new int[n+1][k];
        for(int[] line : dp) Arrays.fill(line, Integer.MAX_VALUE);
        
        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                return a1[1] - a2[1];
            }
        });
        
        dp[path[0]][0] = 0;
        pq.offer(new int[]{path[0], 0, 0});
        
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            if(current[1] > dp[current[0]][current[2]]) continue;
            
            int t = current[2] + 1;
            if(t == k) continue;
            
            for(int next : graph[current[0]]){

                int modify = current[1];
                if(next != path[t]) modify++;
                
                if(dp[next][t] > modify){
                    dp[next][t] = modify;
                    pq.offer(new int[]{next, modify, t});
                }
            }
        }
    }
    
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        
        this.n = n;
        this.k = k;
        this.path = gps_log;
        
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList();
            graph[i].add(i);
        } 
        
        for(int i = 0; i < m; i++){
            int s = edge_list[i][0];
            int e = edge_list[i][1];
            graph[s].add(e);
            graph[e].add(s);
        }
        
        
        bfs();
        
        // for(int[] line : dp){
        //     System.out.println(Arrays.toString(line));
        // }
        
        if(dp[path[k-1]][k-1] == Integer.MAX_VALUE) dp[path[k-1]][k-1] = -1;
        int answer = dp[path[k-1]][k-1];
        return answer;
    }
}
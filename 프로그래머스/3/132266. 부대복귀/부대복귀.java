import java.util.*;

class Solution {
    static List<Integer>[] graph;
    
    static int[] cost;
    
    static void bfs(int start){
        Queue<Integer> queue = new LinkedList();
        queue.offer(start);
        cost[start] = 0;
        
        while(!queue.isEmpty()){
            int current = queue.poll();
            for(int next : graph[current]){
                if(cost[next] == -1){
                    cost[next] = cost[current] + 1;
                    queue.add(next);
                }
            }
        }
    }
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graph = new LinkedList[n+1];
        cost = new int[n+1];
        Arrays.fill(cost, -1);
        for(int i = 0; i <= n; i++){
            graph[i] = new LinkedList();
        }
        
        for(int i = 0; i < roads.length; i++){
            graph[roads[i][0]].add(roads[i][1]);
            graph[roads[i][1]].add(roads[i][0]);
        }
        
        bfs(destination);
        
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            answer[i] = cost[sources[i]];
        }

        return answer;
    }
}
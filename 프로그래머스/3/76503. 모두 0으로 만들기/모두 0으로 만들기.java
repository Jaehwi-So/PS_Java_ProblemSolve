import java.util.*;


class Solution {
    
    static long[] weight;
    static List<LinkedList<Integer>> graph = new ArrayList();
    static boolean[] visited;
    static int[] edgeCnt;
    
    public long topology(){
        Queue<Integer> queue = new LinkedList();
        
        for(int i = 0; i < edgeCnt.length; i++) {
            if(edgeCnt[i] == 1){
                queue.offer(i);
            } 
        } 
        
        long count = 0;
        
        while(!queue.isEmpty()) {
            int current = queue.poll();
            visited[current] = true;
            
            for(int i : graph.get(current)) {
                if(!visited[i]) {

                    count += Math.abs(weight[current]); 
                    
                    weight[i] += weight[current];
                    weight[current] = 0; 
                    
                    edgeCnt[i]--;
                    
                    if(edgeCnt[i] == 1){
                        queue.offer(i); 
                    } 
                }
            }
        }

        return count;
    }
    
    public long solution(int[] a, int[][] edges) {
        
        long weightSum = 0;
        
        weight = new long[a.length];
        
        for(int i = 0; i < a.length; i++){
            graph.add(new LinkedList());
            weight[i] = a[i];
            weightSum += weight[i];
        }
        
        if(weightSum != 0){
            return -1;
        }
        
        edgeCnt = new int[a.length];
        for(int i = 0; i < edges.length; i++){
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
            edgeCnt[edges[i][0]]++;
            edgeCnt[edges[i][1]]++;
        }
        

        visited = new boolean[a.length];
        long cnt = topology();
        return cnt;
    }
}
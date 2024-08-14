import java.util.*;

class Edge implements Comparable<Edge>{
    int index;
    int weight;
    public Edge(int index, int weight){
        this.index = index;
        this.weight = weight;
    }
    
    public int compareTo(Edge e){
        return this.weight - e.weight;
    }
}

class Solution {
    static List<Edge>[] graph;
    static int[] distance;
    static int[] gateList;
    static boolean[] isSummit;

    static void dijakstra(){
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        for(int i = 0; i < gateList.length; i++){
            distance[gateList[i]] = 0;
            queue.offer(new Edge(gateList[i], 0));
        }
       
        while(!queue.isEmpty()){
            Edge current = queue.poll();
            if(current.weight > distance[current.index] || isSummit[current.index]){
                continue;
            }
            for(Edge next : graph[current.index]){
                int weight = Math.max(distance[current.index], next.weight);
                if(distance[next.index] > weight){
                    distance[next.index] = weight;
                    queue.offer(new Edge(next.index, weight));
                }
            }
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        graph = new LinkedList[n+1];
        distance = new int[n+1];
        isSummit = new boolean[n+1];
        Arrays.sort(summits);
        for(int k : summits){
            isSummit[k] = true;
        }
        gateList = gates;
        for(int i = 1; i <= n; i++){
            graph[i] = new LinkedList<Edge>();
        }
        
        for(int i = 0; i < paths.length; i++){
            graph[paths[i][0]].add(new Edge(paths[i][1], paths[i][2]));
            graph[paths[i][1]].add(new Edge(paths[i][0], paths[i][2]));
        }
        
        dijakstra();
        
        int minWeight = Integer.MAX_VALUE;
        int minIndex = 0;
        for(int k : summits){
            if(distance[k] < minWeight){
                minIndex = k;
                minWeight = distance[k];
            }
        }
        
        
        // System.out.println(Arrays.toString(distance));
        
        int[] answer = {minIndex, minWeight};
        return answer;
    }
}
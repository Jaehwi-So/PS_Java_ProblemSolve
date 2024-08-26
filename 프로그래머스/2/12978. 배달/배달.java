import java.util.*;

class Edge{
    int idx;
    int weight;
    public Edge(int idx, int weight){
        this.idx = idx;
        this.weight = weight;
    }
}

class Solution {
    static final int INF = 100000000;
    public int solution(int N, int[][] road, int K) {
        List<Edge>[] graph = new ArrayList[N+1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList();
        }
        for(int[] r : road){
            boolean exist = false;
            for(Edge e : graph[r[0]]){
                if(e.idx == r[1]){
                    if(e.weight > r[2]){
                        graph[r[0]].remove(e);
                        exist = false;
                    }
                    else{
                        exist = true;
                    }                    
                    break;
                }
            }
            if(!exist){
                graph[r[0]].add(new Edge(r[1], r[2]));
                graph[r[1]].add(new Edge(r[0], r[2]));
            }
        }
        
        int[] distance = new int[N+1];
        boolean[] visited = new boolean[N+1];
        Arrays.fill(distance, INF);
        
        Edge start = new Edge(1, 0);
        PriorityQueue<Edge> queue = new PriorityQueue(new Comparator<Edge>(){
            public int compare(Edge e1, Edge e2){
                return e1.weight - e2.weight;
            }
        });
        queue.offer(start);
        distance[1] = 0;
        
        while(!queue.isEmpty()){
            Edge current = queue.poll();
            
            if(!visited[current.idx]){
                visited[current.idx] = true;
            
                for(Edge next : graph[current.idx]){
                    if(!visited[next.idx] && distance[next.idx] > distance[current.idx] + next.weight){
                        distance[next.idx] = distance[current.idx] + next.weight;
                        queue.offer(new Edge(next.idx, distance[next.idx]));
                    }
                }
            }
        }
        
                                           
        int answer = 0;
        for(int i : distance){
            if(i <= K){
                answer++;
            }
        }

        return answer;
    }
}
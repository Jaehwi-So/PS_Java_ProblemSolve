import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge>{
    int index;
    int weight;
    public Edge(int index, int weight){
        this.index = index;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e){
        return this.weight - e.weight;
    }
}

public class Main {
    static int V;
    static int K;
    static int E;
    static List<Edge>[] graph;
    static int[] distance;
    static final int INF = 1000000;
    static void dijkstra(int start){
        boolean[] visited = new boolean[V+1];
        Arrays.fill(distance, INF);
        distance[start] = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(start, 0));

        while(!queue.isEmpty()){
            Edge edge = queue.poll();
            int current = edge.index;
            if(!visited[current]){
                visited[current] = true;
                for(Edge e : graph[current]){
                    distance[e.index] = Math.min(distance[current] + e.weight, distance[e.index]);
                    queue.add(new Edge(e.index, distance[e.index]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        graph = new LinkedList[V + 1];
        distance = new int[V + 1];
        for(int i = 1; i <= V; i++){
            graph[i] = new LinkedList<Edge>();
        }
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Edge(v, w));
        }

        dijkstra(K);

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= V; i++){
            if(distance[i] == INF){
                sb.append("INF").append("\n");
            }
            else{
                sb.append(distance[i]).append("\n");
            }
        }

       System.out.println(sb);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node>{
    int index;
    int weight;
    public Node(int index, int weight){
        this.index = index;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node n){
        return this.weight - n.weight;
    }
}

public class Main {
    static int V;
    static int K;
    static int E;
    static List<Node>[] graph;
    static int[] distance;
    static final int INF = 1000000;
    static void dijkstra(int start){
        boolean[] visited = new boolean[V+1];
        Arrays.fill(distance, INF);
        distance[start] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));

        while(!queue.isEmpty()){
            Node edge = queue.poll();
            int u = edge.index;
            if(!visited[u]){
                visited[u] = true;
                for(Node n : graph[u]){
                    int v = n.index;
                    int weight = n.weight;
                    if (!visited[v] && distance[u] + weight < distance[v]) {
                        distance[v] = distance[u] + weight;
                        queue.add(new Node(v, distance[v]));
                    }
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
            graph[i] = new LinkedList<Node>();
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
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

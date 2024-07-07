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
    static int E;
    static List<Node>[] graph;
    static int[] distance;
    static final int INF = 200000000;
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
            graph[v].add(new Node(u, w));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        dijkstra(1);
        int distStoV1 = distance[v1];
        int distStoV2 = distance[v2];

        dijkstra(v1);
        int distV1toV2 = distance[v2];
        int distV1toEnd = distance[V];

        dijkstra(v2);
        int distV2toEnd = distance[V];

        int result = Math.min((distStoV1 + distV1toV2 + distV2toEnd), (distStoV2 + distV1toV2 + distV1toEnd));
        if(result >= INF){
            result = -1;
        }
        System.out.println(result);

    }
}

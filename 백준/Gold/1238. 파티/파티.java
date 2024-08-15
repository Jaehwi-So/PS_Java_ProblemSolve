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

    public int compareTo(Edge e){
        return this.weight - e.weight;
    }
}
public class Main {
    static int n;
    static int dest;

    static int[] dijakstra(List<Edge>[] graph){
        int[] distance = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[dest] = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(dest, 0));
        while(!queue.isEmpty()){
            Edge current = queue.poll();
            if(!visited[current.index]){
                visited[current.index] = true;
                for(Edge next : graph[current.index]){
                    if(!visited[next.index] && distance[next.index] > distance[current.index] + next.weight){
                        queue.offer(new Edge(next.index, distance[current.index] + next.weight));
                        distance[next.index] = distance[current.index] + next.weight;
                    }
                }
            }
        }

        return distance;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        List<Edge>[] inGraph = new LinkedList[n+1];
        List<Edge>[] outGraph = new LinkedList[n+1];
        for(int i = 1; i <= n; i++){
            inGraph[i] = new LinkedList<>();
            outGraph[i] = new LinkedList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            inGraph[a].add(new Edge(b, w));
            outGraph[b].add(new Edge(a, w));
        }

        int[] inResult = dijakstra(inGraph);
        int[] outResult = dijakstra(outGraph);

        int result = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            result = Math.max(result, inResult[i] + outResult[i]);
        }
        System.out.println(result);
        
    }
}

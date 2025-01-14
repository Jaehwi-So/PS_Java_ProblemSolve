import java.io.*;
import java.util.*;

class Edge{
    int index;
    int weight;
    int m;
    int total;
    public Edge(int index, int weight, int m, int total){
        this.index = index;
        this.weight = weight;
        this.m = m;
        this.total = total;
    }
}
public class Main {
    static int n;
    static int m;
    static int k;
    static int[][] visited;
    static List<Edge>[] edges;

    static void bfs(){

        Queue<Edge> queue = new LinkedList<>();
        queue.offer(new Edge(1, 0, 1, 0));
        visited[1][1] = 0;

        while(!queue.isEmpty()){
            Edge current = queue.poll();
            if(visited[current.index][current.m] > current.total) continue;
            for(Edge next : edges[current.index]){
                if(current.m+1 <= m && visited[next.index][current.m+1] < current.total + next.weight){
                    visited[next.index][current.m+1] = current.total + next.weight;
                    queue.offer(new Edge(next.index, next.weight, current.m+1, current.total + next.weight));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        edges = new ArrayList[n+1];
        visited = new int[n+1][m+1];
        for(int i = 1; i <= n; i++){
            edges[i] = new ArrayList<>();
        }
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if(start < end){
                edges[start].add(new Edge(end, weight, 0, 0));
            }
        }
        bfs();
        int result = 0;
        for(int i = 0; i <= m; i++){
            result = Math.max(result, visited[n][i]);
        }
        System.out.println(result);
    }
}
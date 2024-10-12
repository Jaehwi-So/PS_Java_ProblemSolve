import java.io.*;
import java.util.*;

class Edge{
    int index;
    long weight;

    public Edge(int index, long weight){
        this.index = index;
        this.weight = weight;
    }
}
public class Main {
    static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[end].add(new Edge(start, weight));
        }


        long[] D = new long[n+1];
        Arrays.fill(D, INF);
        boolean[] visited = new boolean[n+1];

        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Long.compare(o1.weight, o2.weight);
            }
        });

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            int start = Integer.parseInt(st.nextToken());
            D[start] = 0;
            pq.offer(new Edge(start, 0));
        }

        while(!pq.isEmpty()){
            Edge current = pq.poll();
            if(visited[current.index]) continue;
            visited[current.index] = true;
            for(Edge next : graph[current.index]){
                if(D[next.index] > D[current.index] + next.weight){
                    D[next.index] = D[current.index] + next.weight;
                    pq.offer(new Edge(next.index, D[current.index] + next.weight));
                }
            }
        }



        int max = 0;
        long maxDist = 0;
        for(int i = 1; i <= n; i++){
            if(D[i] > maxDist){
                maxDist = D[i];
                max = i;
            }
        }

        System.out.println(max);
        System.out.println(maxDist);

    }
}

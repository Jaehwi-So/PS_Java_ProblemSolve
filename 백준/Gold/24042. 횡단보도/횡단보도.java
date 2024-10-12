import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
    int sequence;
    int index;
    long weight;

    public Edge(int sequence, int index, long weight){
        this.sequence = sequence;
        this.index = index;
        this.weight = weight;
    }

    public int compareTo(Edge e){
        return Long.compare(this.weight, e.weight);
    }
}
public class Main {
    static long INF = 100000000000L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(i, e, 1L));
            graph[e].add(new Edge(i, s, 1L));
        }

        long[] distance = new long[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(distance, INF);
        distance[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(0, 1, 0));

        while(!pq.isEmpty()){
            Edge current = pq.poll();
            if(visited[current.index]){
                continue;
            }
            visited[current.index] = true;

            long currentTime = current.weight;
            for(Edge next : graph[current.index]){
                long currentPeriod = currentTime % (m);
                int nextSeq = next.sequence;
                long minute = 0;
                if(nextSeq < currentPeriod){
                    minute = m - currentPeriod + nextSeq;
                }
                else{
                    minute = nextSeq - currentPeriod;
                }
                if(distance[next.index] > distance[current.index] + minute + 1){
                    distance[next.index] = distance[current.index] + minute + 1;
                    pq.offer(new Edge(0, next.index, distance[next.index]));
                }

            }
        }

//        System.out.println(Arrays.toString(distance));
        System.out.println(distance[n]);

    }
}
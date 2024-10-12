import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge>{
    int index;
    long weight;
    long maxWeight;
    public Edge(int index, long weight, long maxWeight){
        this.index = index;
        this.weight = weight;
        this.maxWeight = maxWeight;
    }

    public int compareTo(Edge e){
        if(this.maxWeight == e.maxWeight){
            return Long.compare(this.weight, e.weight);
        }
        return Long.compare(this.maxWeight, e.maxWeight);
    }
}
public class Main {
    static long INF;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); //정점 개수
        int m = Integer.parseInt(st.nextToken()); //간선 개수
        int a = Integer.parseInt(st.nextToken()); //시작점
        int b = Integer.parseInt(st.nextToken()); //끝점
        long c = Long.parseLong(st.nextToken()); //가진 돈

        List<Edge>[] graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());
            graph[start].add(new Edge(end, cost, 0L));
            graph[end].add(new Edge(start, cost, 0L));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(a, 0L, 0L));
        long[] maxW = new long[n+1];
        INF = c + 1;
        Arrays.fill(maxW, INF);


        long result = -1L;
        while(!pq.isEmpty()){
            Edge current = pq.poll();
            int cidx = current.index;
            if(cidx == b){
                result = current.maxWeight;
                break;
            }
            if(maxW[cidx] <= current.maxWeight){
                continue;
            }
            maxW[cidx] = current.maxWeight;

            for(Edge next : graph[cidx]){
                long nextMaxWeight = Math.max(next.weight, current.maxWeight);
                if(maxW[next.index] <= nextMaxWeight || current.weight + next.weight > c){
                    continue;
                }
                pq.offer(new Edge(next.index, current.weight + next.weight, nextMaxWeight));
            }
        }
        System.out.println(result);
    }
}

import java.io.*;
import java.util.*;

class Edge{
    int start;
    int end;
    int weight;
    public Edge(int start, int end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
public class Main {
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int test = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < test; t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int e = (m * 2) + w;

            int[] distance = new int[n+1];
            Edge[] edges = new Edge[e];
            for(int i = 0; i < m * 2; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(a, b, d);
                edges[++i] = new Edge(b, a, d);

            }
            for(int i = m * 2; i < e; i++){
                st = new StringTokenizer(br.readLine());
                edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) * -1);
            }
            Arrays.fill(distance, INF);

            for(int k = 1; k <= n; k++){
                if(distance[k] == INF){
                    distance[k] = 0;
                    for(int i = 1; i <= n-1; i++){
                        for(int j = 0; j < e; j++){
                            if(distance[edges[j].start] != INF){
                                distance[edges[j].end] = Math.min(distance[edges[j].end], distance[edges[j].start] + edges[j].weight);
                            }
                        }
                    }
                }
            }

//            System.out.println(Arrays.toString(distance));

            String result = "NO";
            for(int j = 0; j < e; j++){
                if(distance[edges[j].start] != INF && distance[edges[j].end] > distance[edges[j].start] + edges[j].weight){
                    result = "YES";
                    break;
                }
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }
}

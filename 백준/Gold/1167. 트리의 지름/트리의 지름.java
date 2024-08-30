import java.io.*;
import java.util.*;

class Edge{
    int index;
    int weight;
    public Edge(int index, int weight){
        this.index = index;
        this.weight = weight;
    }
}
public class Main {
    static int n;
    static boolean[] visited;
    static List<Edge>[] graph;
    static int max = 0;
    static int maxIdx = 0;

    static void dfs(int index, int w){
        if(w > max){
            max = w;
            maxIdx = index;
        }
        for(Edge next : graph[index]){
            if(!visited[next.index]){
                visited[next.index] = true;
                dfs(next.index, w + next.weight);
                visited[next.index] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];
        graph = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            graph[k] = new ArrayList<>();
            int m;
            while((m = Integer.parseInt(st.nextToken())) != -1){
                int d = Integer.parseInt(st.nextToken());
                graph[k].add(new Edge(m, d));
            }
        }

        visited[1] = true;
        dfs(1, 0);
        visited[1] = false;

        visited[maxIdx] = true;
        dfs(maxIdx, 0);
        visited[maxIdx] = false;

        System.out.println(max);

    }
}
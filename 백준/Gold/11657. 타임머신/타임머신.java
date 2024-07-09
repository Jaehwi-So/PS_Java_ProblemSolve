import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] graph;
    static long[] distance;
    static final int INF = 2000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[m][3];
        distance = new long[n+1];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
            graph[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.fill(distance, INF);
        distance[1] = 0;
        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < m; j++){
                if(distance[graph[j][0]] != INF){
                    distance[graph[j][1]] = Math.min(distance[graph[j][1]], distance[graph[j][0]] + graph[j][2]);
                }
            }
        }

        boolean isCycle = false;
        for(int j = 0; j < m; j++){
            if(distance[graph[j][0]] != INF && distance[graph[j][1]] > distance[graph[j][0]] + graph[j][2]){
                isCycle = true;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        if(isCycle){
            sb.append(-1);
        }
        else{
            for(int i = 2; i <= n; i++){
                long result = distance[i] == INF ? -1 : distance[i];
                sb.append(result).append("\n");
            }
        }

//        System.out.println(Arrays.toString(distance));
//        System.out.println(Arrays.toString(temp));
        System.out.println(sb);
        
    }
}
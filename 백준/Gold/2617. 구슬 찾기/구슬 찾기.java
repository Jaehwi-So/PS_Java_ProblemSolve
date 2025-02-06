import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 10000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n+1][n+1];
        for(int[] line : graph) Arrays.fill(line, INF);

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[b][a] = 1;
        }


        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        int[] inbound = new int[n+1];
        int[] outbound = new int[n+1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(graph[i][j] != INF){
                    outbound[i]++;
                    inbound[j]++;
                }
            }
        }

//        System.out.println(Arrays.toString(inbound));
//        System.out.println(Arrays.toString(outbound));

        int result = 0;
        int mid = (n+1) / 2;
        for(int i = 1; i <= n; i++){
            if(inbound[i] >= mid || outbound[i] >= mid){
                result++;
            }
        }
        System.out.println(result);
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static long[][] distance;
    static final long INF = 10000000000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        distance = new long[n+1][n+1];
        for(long[] line : distance){
            Arrays.fill(line, INF);
        }

        for(int i = 1; i <= n; i++){
            distance[i][i] = 0;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            distance[start][end] = Math.min(distance[start][end], weight);
        }

        for(int k = 1; k <= n; k++){
            for(int s = 1; s <= n; s++){
                for(int e = 1; e <= n; e++){
                    distance[s][e] = Math.min(distance[s][e], distance[s][k] + distance[k][e]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(distance[i][j] == INF){
                    sb.append(0).append(" ");
                }
                else{
                    sb.append(distance[i][j]).append(" ");
                }

            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

import java.io.*;
import java.util.*;


public class Main {
    static final int INF = 100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new LinkedList[n+1];
        for(int i = 1; i <= n; i++){
            graph[i] = new LinkedList<>();
        }

        int[][] D = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(D[i], INF);
            D[i][i] = 0;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            D[a][b] = 1;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(D[a][b] > D[b][a]){
                sb.append("1");
            }
            else if(D[b][a] > D[a][b]){
                sb.append("-1");
            }
            else{
                sb.append("0");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
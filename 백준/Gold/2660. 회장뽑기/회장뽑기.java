import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 10000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] graph = new int[n+1][n+1];
        for(int[] line : graph){
            Arrays.fill(line, INF);
        }
        for(int i = 1; i <= n; i++){
            graph[i][i] = 1;
        }

        while(true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == -1 && b == -1) break;
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int min = INF;
        int[] score = new int[n+1];
        for(int i = 1; i <= n; i++){
            int level = 0;
            for(int j = 1; j <= n; j++){
                level = Math.max(level, graph[i][j]);
            }
            score[i] = level;
            min = Math.min(level, min);
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= n; i++){
            if(score[i] == min){
                cnt++;
                sb.append(i).append(" ");
            }
        }

        System.out.println(min + " " + cnt);
        System.out.println(sb);
        
    }
}
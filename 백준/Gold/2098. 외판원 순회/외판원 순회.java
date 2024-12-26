import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] matrix;
    static int MAX = 100000000;
    static int[][] dp;

    static int parseBit(boolean[] visited){
        StringBuilder sb = new StringBuilder();
        for(boolean b : visited){
            int k = b ? 1 : 0;
            sb.append(k);
        }
        return Integer.parseInt(sb.toString(), 2);
    }
    static int tsp(int current, boolean[] visited){
        int bit = parseBit(visited);
        if(dp[current][bit] == -1){
            int min = MAX;
            boolean isLast = true;
            for(int i = 0; i < n; i++){
                if(visited[i] == false){
                    visited[i] = true;
                    min = Math.min(min, matrix[current][i] + tsp(i, visited));
                    visited[i] = false;
                    isLast = false;
                }
            }
            if(isLast){
                dp[current][bit] = matrix[current][0];
            }
            else{
                dp[current][bit] = min;
            }
        }
        return dp[current][bit];
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];
        dp = new int[n][(int)Math.pow(2, n)];
        for(int[] line : dp){
            Arrays.fill(line, -1);
        }
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int dist = Integer.parseInt(st.nextToken());
                if(dist == 0) dist = MAX;
                matrix[i][j] = dist;
            }
        }
        boolean[] visited = new boolean[n];
        visited[0] = true;
        System.out.println(tsp(0, visited));
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] array;
    static int[][][] dp;
    static int[][][] trace;
    static int calc(int start, int end, int k){
        if(dp[start][end][k] == -1){
            if(k == 1){
                dp[start][end][k] = 0;
                for(int i = start; i <= end; i++){
                    dp[start][end][k] += array[i];
                }
                trace[start][end][k] = end + 1;
            }
            else{
                int min = Integer.MAX_VALUE;
                for(int i = start; i <= end - 1; i++){
                    int c = Math.max(calc(start, i, 1), calc(i+1, end, k-1));
                    if(min > c){
                      min = c;
                      trace[start][end][k] = i + 1;
                    }
                }
                dp[start][end][k] = min;
            }
        }
        return dp[start][end][k];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n][n][m+1];
        trace = new int[n][n][m+1];
        for(int[][] mat : dp){
            for(int[] line : mat){
                Arrays.fill(line, -1);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(calc(0, n-1, m)).append("\n");
        int start = 0;
        int k = m;
        while(start < n){
            sb.append(trace[start][n-1][k] - start).append(" ");
            start = trace[start][n-1][k];
            k--;
        }
        System.out.println(sb);
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static int MAX = 100000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n+3][200];

        for(int[] line : dp){
            Arrays.fill(line, MAX);
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[k], -1);
        }

        dp[1][1] = 0;

        if(dp[2][1] != -1){
            dp[2][1] = 1;
        }
        for(int i = 2; i <= n; i++){
            for(int j = 1; j < 200; j++){
                if(dp[i][j] != MAX && dp[i][j] != -1){
                    if(i+j <= n && j < 200){
                        dp[i+j][j] = Math.min(dp[i+j][j], dp[i][j] + 1);
                    }
                    if(i+j+1 <= n && j+1 < 200){
                        dp[i+j+1][j+1] = Math.min(dp[i+j+1][j+1], dp[i][j] + 1);
                    }
                    if(i+j-1 <= n && j-1 > 0){
                        dp[i+j-1][j-1] = Math.min(dp[i+j-1][j-1], dp[i][j] + 1);
                    }
                }
            }
        }

        int result = MAX;
        for(int i = 1; i < 200; i++){
            result = Math.min(result, dp[n][i]);
        }

        if(result == MAX){
            result = -1;
        }
        System.out.println(result);
    }
}
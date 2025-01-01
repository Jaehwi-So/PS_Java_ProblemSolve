import java.io.*;
import java.util.*;

public class Main {
    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] array = new int[n+1];
        sum = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            array[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + array[i];
        }
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n+1][4];
        for(int i = m; i <= n; i++){
            dp[i][1] = Math.max(dp[i-1][1], sum[i] - sum[i-m]);
            dp[i][2] = Math.max(dp[i-1][2], dp[i-m][1] + sum[i] - sum[i-m]);
            dp[i][3] = Math.max(dp[i-1][3], dp[i-m][2] + sum[i] - sum[i-m]);
        }

        System.out.println(dp[n][3]);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        StringBuffer sb = new StringBuffer();
        for(int l = 0; l < t; l++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] array = new int[n+2][2];
            int[][] dp = new int[n+2][2];

            st = new StringTokenizer(br.readLine());
            for(int i = 2; i <= n+1; i++){
                array[i][0] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 2; i <= n+1; i++){
                array[i][1] = Integer.parseInt(st.nextToken());
            }

            for(int i = 2; i <= n+1; i++){
                dp[i][0] = Math.max(dp[i-1][1], Math.max(dp[i-2][0], dp[i-2][1])) + array[i][0];
                dp[i][1] = Math.max(dp[i-1][0], Math.max(dp[i-2][0], dp[i-2][1])) + array[i][1];
            }

            sb.append(Math.max(dp[n+1][0], dp[n+1][1])).append("\n");
        }
        System.out.println(sb);
    }
}

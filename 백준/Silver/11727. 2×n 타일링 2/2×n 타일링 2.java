import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int mod = 10007;
        int[][] dp = new int[n+1][3];
        dp[0][0] = 1;
        dp[1][0] = 1;
        for(int i = 2; i <= n; i++){
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % mod; // 세로
            dp[i][1] = (dp[i-2][0] + dp[i-2][1] + dp[i-2][2]) % mod; // 가로
            dp[i][2] = (dp[i-2][0] + dp[i-2][1] + dp[i-2][2]) % mod; // 사각형
        }

        System.out.println((dp[n][0] + dp[n][1] + dp[n][2]) % mod);

    }
}
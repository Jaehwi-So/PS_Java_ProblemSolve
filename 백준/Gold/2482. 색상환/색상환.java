import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        long mod = 1000000003;
        long[][] dp = new long[n+1][k+1];
        for(int i = 1; i <= n; i++){
            dp[i][1] = i;
        }

        for(int i = 4; i <= n; i++){
            for(int j = 2; j <= k; j++){
                dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % mod;
            }
        }

        System.out.println(dp[n][k]);
    }
}


// 7개중에 3개 뽑는 법 :
// (새로운 칸을 포함시키지 않는 경우) 6개중에 3개 뽑기 +
// (새로운 칸을 포함시킬 경우) 5개중에 2개 뽑기
// == (새로운 칸을 칠할 경우) 4개중에 2개 뽑기 + (새로운 칸의 인접 두 개를 칠할 경우) 3개중에 1개 뽑기

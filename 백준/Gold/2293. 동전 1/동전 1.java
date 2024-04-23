
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] coins;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        dp = new int[n][k+1];
        coins = new int[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(coins);
        dp[0][coins[0]] = 1;

        for(int i = coins[0] + 1; i <= k; i++){
            dp[0][i] = dp[0][i - coins[0]];
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j <= k; j++){
                dp[i][j] = dp[i-1][j];
                if(j > coins[i]){
                    dp[i][j] += dp[i][j - coins[i]];
                }
                else if(j == coins[i]){
                    dp[i][j] += 1;
                }
            }
        }

//        for(int[] d : dp){
//           System.out.println(Arrays.toString(d));
//        }
        System.out.println(dp[n-1][k]);


    }
}

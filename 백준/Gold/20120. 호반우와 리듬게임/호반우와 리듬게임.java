import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] array = new int[n+2];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[n+3][n+3];

        for(long[] line : dp){
            Arrays.fill(line, Long.MIN_VALUE);
        }

        dp[1][0] = 0;
        dp[1][1] = array[1];
        dp[2][0] = Math.max(dp[1][0], dp[1][1]);
        dp[2][1] = array[2];
        dp[2][2] = dp[1][1] + array[2] * 2;


        for(int i = 3; i <= n; i++){

            for(int j = i - 1; j >= i - 2; j--){
                for(int l = 1; l <= j; l++){
                    dp[i][0] = Math.max(dp[i][0], dp[j][l]);
                }
            }

            for(int k = 1; k <= i; k++){
                dp[i][k] = dp[i-1][k-1] + (array[i] * k);
            }
        }

//        for(long[] line : dp){
//            System.out.println(Arrays.toString(line));
//        }

        long result = 0;
        for(int i = 0; i <= n; i++){
            result = Math.max(result, dp[n][i]);
        }
        System.out.println(result);

    }
}
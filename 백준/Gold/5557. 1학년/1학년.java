import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] sequence = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[n][21];
        dp[0][sequence[0]] = 1;

        for(int i = 1; i < n-1; i++){
            for(int j = 0; j <= 20; j++){
                if(dp[i-1][j] > 0){
                    if(j+sequence[i] <= 20){
                        dp[i][j+sequence[i]] += dp[i-1][j];
                    }
                    if(j-sequence[i] >= 0){
                        dp[i][j-sequence[i]] += dp[i-1][j];
                    }
                }
            }
        }

        long result = dp[n-2][sequence[n-1]];

        System.out.println(result);
//        for(long[] line : dp){
//            System.out.println(Arrays.toString(line));
//        }

    }
}
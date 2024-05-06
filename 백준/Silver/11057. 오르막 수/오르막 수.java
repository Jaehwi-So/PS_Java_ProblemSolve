import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n+1][10];
        for(int i = 0; i < 10; i++){
            dp[1][i] = 1;
        }
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < 10; j++){
                for(int l = j; l >= 0; l--){
                    dp[i][j] = (dp[i][j] + dp[i-1][l]) % 10007;
                }
            }
        }
//        for(int[] line : dp){
//            System.out.println(Arrays.toString(line));
//        }

        int result = 0;
        for(int i = 0; i < 10; i++){
            result = (result + dp[n][i]) % 10007;
        }
        System.out.println(result);

    }
}

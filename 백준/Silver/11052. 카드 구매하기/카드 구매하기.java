
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] values = new int[n + 1];
        for(int i = 1; i <= n; i++){
            values[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                int val = 0;
                if(i >= j){
                    val += dp[i-j][j] + values[j];
                }
                dp[i][j] = Math.max(dp[i][j-1], val);
            }
        }

        System.out.println(dp[n][n]);
    }
}

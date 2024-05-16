
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

        int[] day = new int[n+1];
        int[] value = new int[n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            day[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+51];

        for(int i = n; i >= 1; i--){
            if(i + day[i] - 1 <= n){
                dp[i] = value[i];
            }
            dp[i] += dp[i + day[i]];
            dp[i] = Math.max(dp[i], dp[i+1]);
        }

        System.out.println(dp[1]);
    }
}

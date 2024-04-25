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
        int dp[] = new int[n + 6];
        int day[] = new int[n + 1];
        int value[] = new int[n + 1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            day[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = n; i >= 1; i--){
            int beforeIdx = i + day[i];
            int val = dp[beforeIdx] + value[i];
            if(beforeIdx > n + 1){
                val = 0;
            }
            dp[i] = Math.max(val, dp[i + 1]);
        }

        System.out.println(dp[1]);


    }
}

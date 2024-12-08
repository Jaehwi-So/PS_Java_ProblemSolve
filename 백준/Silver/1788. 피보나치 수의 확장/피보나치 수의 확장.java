import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        boolean isPossitive = n > 0 ? true : false;
        int target = Math.abs(n);
        int mod = 1000000000;
        dp = new int[target + 2];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= target; i++){
            if(isPossitive){
                dp[i] = (dp[i-1] + dp[i-2]) % mod;
            }
            else{
                dp[i] = (dp[i-2] - dp[i-1]) % mod;
            }
        }
        StringBuilder sb = new StringBuilder();
        if(dp[target] == 0) sb.append(0);
        else if(dp[target] > 0) sb.append(1);
        else if(dp[target] < 0) sb.append(-1);
        sb.append("\n");
        sb.append(Math.abs(dp[target]));
        System.out.println(sb);

    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    static long[] dp;
    static final long mod = 1000000009L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int test = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        int[] cases = new int[test];
        int max = 2;
        for(int t = 0; t < test; t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            cases[t] = n;
            max = Math.max(n, max);
        }

        dp = new long[max + 1];
        dp[0] = 1;
        dp[1] = 1; // 1
        dp[2] = 2; // 11  2
        for(int i = 3; i <= max; i++){
            dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % mod;
        }
        for(int t = 0; t < test; t++){
            sb.append(dp[cases[t]]).append("\n");
        }

        System.out.println(sb.toString());
    }
}
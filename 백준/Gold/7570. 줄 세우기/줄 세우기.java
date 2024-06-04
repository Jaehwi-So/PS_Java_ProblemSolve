import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        dp = new int[n+1];

        st = new StringTokenizer(br.readLine());

        int max = 0;
        for(int i = 1; i <= n; i++){
            int number = Integer.parseInt(st.nextToken());
            dp[number] = dp[number - 1] + 1;
            max = Math.max(dp[number], max);
        }

//        System.out.println(n - max);
//        System.out.println(Arrays.toString(dp));
        System.out.println(n - max);
    }
}

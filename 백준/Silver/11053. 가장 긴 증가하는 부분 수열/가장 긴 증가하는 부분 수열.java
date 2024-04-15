import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 0 : 3  X = 1
 * 1 : 5  dp[0] = 2
 * 2 : 7  dp[1] vs dp[0] = 3
 * 3 : 9  dp[0] vs dp[1] vs vs dp[2] = 4
 * 4 : 2  X = 1
 * 5 : 1  X = 1
 * 6 : 4  dp[5] vs dp[4] vs dp[0] = 2
 * 7 : 8  dp[6] vs dp[5] vs dp[4] vs dp[2] vs dp[1] vs dp[0] = 4
 */
public class Main {
    static Integer[] numbers;
    static Integer[] dp;

    static Integer calc(int n){
        if(dp[n] == null){
            dp[n] = 1;
            for(int i = n - 1; i >= 0; i--){
                if(numbers[i] < numbers[n]){
                    dp[n] = Math.max(calc(i) + 1, dp[n]);
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        numbers = new Integer[n];
        dp = new Integer[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < n; i++){
            calc(i);
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            max = Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}

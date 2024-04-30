
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static Integer[] numbers;
    static Integer[] dp;


    static Integer calc(int n){
        if(dp[n] == null){
            dp[n] = 1;
            for(int i = n; i > 0; i--){
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
        numbers = new Integer[n + 1];
        dp = new Integer[n + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; i++){
            calc(i);
        }


        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            max = Math.max(dp[i], max);
        }
        System.out.println(max);
    }
}

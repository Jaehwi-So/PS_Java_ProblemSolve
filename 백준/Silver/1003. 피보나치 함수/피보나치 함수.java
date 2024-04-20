import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static Integer dp[][];
    public static Integer[] fibonacci(int n) {
        if(dp[n][0] == null){
            if (n <= 0) {
                dp[0][0] = 1;
                dp[0][1] = 0;
            }
            else if (n == 1) {
                dp[1][0] = 0;
                dp[1][1] = 1;
            }
            else {
                Integer[] fib1 = fibonacci(n - 1);
                Integer[] fib2 = fibonacci(n - 2);
                dp[n][0] = fib1[0] + fib2[0];
                dp[n][1] = fib1[1] + fib2[1];
            }
        }

        return dp[n];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        dp = new Integer[41][2];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < t; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            Integer[] result = fibonacci(n);
            sb.append(result[0]).append(" ").append(result[1]).append("\n");
        }
        
        System.out.println(sb);

    }
}

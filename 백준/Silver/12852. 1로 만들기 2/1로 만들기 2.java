import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] dp;
    static int[] buf;
    static StringBuilder sb = new StringBuilder();


    static int calc(int number){
        if(dp[number] == Integer.MAX_VALUE){
            dp[number] = calc(number - 1) + 1;
            buf[number] = number - 1;
            if(number % 3 == 0){
                int k = calc(number / 3) + 1;
                if(k < dp[number]){
                    dp[number] = k;
                    buf[number] = number / 3;
                }
            }
            if(number % 2 == 0){
                int k = calc(number / 2) + 1;
                if(k < dp[number]){
                    dp[number] = k;
                    buf[number] = number / 2;
                }
            }

        }
        return dp[number];
    }

    static void print(int n){
        sb.append(n).append(" ");
        if(buf[n] != -1){
            print(buf[n]);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        dp = new int[n+1];
        buf = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(buf, -1);
        dp[1] = 0;
        calc(n);
        System.out.println(dp[n]);
        print(n);
        System.out.println(sb);

    }
}

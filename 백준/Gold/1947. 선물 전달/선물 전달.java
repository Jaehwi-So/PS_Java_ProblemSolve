import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int MOD = 1000000000;
        long[] dp = new long[n+2];
        dp[1] = 0;
        dp[2] = 1;

        // 1. K번째 사람과 누군가가 서로 교환하는 경우 -> 나머지 K-2명의 사람들끼리 교환성사 (* 서로교환대상 k-1명) = k-1 * dp[k-2]
        // 2. K-1명 중 누군가는 K의 선물을 받음 = K를 포함한 나머지 K-1명끼리 선물을 받는 경우 = k-1 * dp[k-1]

        for(int i = 3; i <= n; i++){
            long k = (dp[i-1] + dp[i-2]) % MOD;
            dp[i] = (k * (i-1)) % MOD;
        }
        System.out.println(dp[n]);
    }
}
import java.util.Scanner;

public class Main {
    static long[][] dp;
    static long bi_co(int n, int k){
        if(dp[n][k] == 0){
            if(k == 0 || n == k){
                dp[n][k] = 1;
            }
            else{
                dp[n][k] = (bi_co(n-1, k-1) +  bi_co(n-1, k)) % 10007L;
            }
        }

        return dp[n][k];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        dp = new long[n+1][n+1];
        System.out.println(bi_co(n, k));

    }
}
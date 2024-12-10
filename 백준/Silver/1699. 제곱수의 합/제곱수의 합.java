import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int[] dp;

    static int calculate(int number){
        for(int i = 1; i <= number; i++){
            if(dp[i] == Integer.MAX_VALUE){
                for(int j = 1; j <= number; j++){
                    if(j * j <= i){
                        dp[i] = Math.min(dp[i], dp[j * j] + dp[i - (j * j)]);
                    }
                    else break;
                }
            }
        }
        return dp[number];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= n; i++){
            if(i * i <= n){
                dp[i*i] = 1;
            }
            else break;
        }

        System.out.println(calculate(n));

    }
}

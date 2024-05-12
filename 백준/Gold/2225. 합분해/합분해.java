import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] dp = new int[k+1][n+1];
        for(int i = 0; i <= n; i++){
            dp[1][i] = 1;
        }

        for(int i = 2; i <= k; i++){
            dp[i][0] = 1;
            for(int j = 1; j <= n; j++){
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
            }
        }

//        for(int[] line : dp){
//            System.out.println(Arrays.toString(line));
//        }

        System.out.println(dp[k][n]);


    }
}
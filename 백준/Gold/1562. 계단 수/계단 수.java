import java.util.Scanner;

public class Main {
    static final int MOD = 1000000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][][] dp = new long[n+1][10][1024];
        for(int i = 1; i <= 9; i++){
            dp[1][i][1<<i] = 1;
        }
        for(int i = 2; i <= n; i++){
            for(int j = 0; j <= 9; j++){
                for(int k = 0; k < 1024; k++){
                    int b = k | (1<<j);

                    if(j > 0){
                        dp[i][j][b] = (dp[i][j][b] + dp[i-1][j-1][k]) % MOD;
                    }
                    if(j < 9){
                        dp[i][j][b] = (dp[i][j][b] + dp[i-1][j+1][k]) % MOD;
                    }
                }
            }
        }

        long result = 0;
//        for(int l = 1; l <= n; l++){
//            for(int i = 0; i <= 9; i++){
//                result = (result + dp[l][i][1023]) % MOD;
//            }
//        }
        for(int i = 0; i <= 9; i++){
            result = (result + dp[n][i][1023]) % MOD;
        }


        System.out.println(result);

    }
}
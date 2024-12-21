import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        char[][] matrix = new char[n][n];
        for(int i = 0 ; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                matrix[i][j] = st.nextToken().charAt(0);
            }
        }

        long[][][] dp = new long[n][n][3]; //가로, 세로, 대각
        dp[0][1][0] = 1;
        for(int j = 2; j < n; j++){
            if(matrix[0][j] == '1') break;
            dp[0][j][0] = 1;
        }
        for(int i = 1; i < n; i++){
            for(int j = 1; j < n; j++){
                if(matrix[i][j] != '1'){

                    if(matrix[i-1][j] != '1'){
                        // 세로
                        dp[i][j][1] += dp[i-1][j][1];
                        dp[i][j][1] += dp[i-1][j][2];
                    }

                    if(matrix[i][j-1] != '1'){
                        // 가로
                        dp[i][j][0] += dp[i][j-1][0];
                        dp[i][j][0] += dp[i][j-1][2];
                    }

                    // 대각
                    if(matrix[i-1][j] != '1' && matrix[i][j-1] != '1' && matrix[i-1][j-1] != '1'){
                        dp[i][j][2] += dp[i-1][j-1][0];
                        dp[i][j][2] += dp[i-1][j-1][1];
                        dp[i][j][2] += dp[i-1][j-1][2];
                    }
                }
            }
        }

        System.out.println(dp[n-1][n-1][0] + dp[n-1][n-1][1] + dp[n-1][n-1][2]);
    }
}
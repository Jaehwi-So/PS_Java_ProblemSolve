import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] matrix;
    static int[][][] dp;
    static int MIN = -100000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n+1][m+1];
        dp = new int[n+1][m+2][3];

        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= m+1; j++){
                Arrays.fill(dp[i][j], MIN);
            }
        }

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= n; i++){
            if(i == 1){
                dp[i][1][0] = matrix[i][1];
                for(int j = 2; j <= m; j++){
                    dp[i][j][0] = dp[i][j-1][0] + matrix[i][j];
                }
            }
            else{
                for(int j = 1; j <= m; j++){
                    dp[i][j][1] = Math.max(dp[i-1][j][0], dp[i][j-1][1])+ matrix[i][j];
                }
                for(int j = m; j > 0; j--){
                    dp[i][j][2] = Math.max(dp[i-1][j][0], dp[i][j+1][2])+ matrix[i][j];
                }
                for(int j = 1; j <= m; j++){
                    dp[i][j][0] = Math.max(dp[i][j][1], dp[i][j][2]);
                }
            }
        }

        System.out.println(dp[n][m][0]);
    }
}
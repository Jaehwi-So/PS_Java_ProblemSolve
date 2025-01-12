import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[][][][] dp = new int[n+1][m+1][c+1][c+2];
        int[][] matrix = new int[n+1][m+1];
        int sequence = 1;
        for(int i = 1; i <= c; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            matrix[row][col] = sequence++;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(i == 1 && j == 1){
                    if(matrix[i][j] == 0) dp[i][j][0][0] = 1;
                    else dp[i][j][matrix[i][j]][1] = 1;
                }
                else{
                    int index = matrix[i][j];
                    if(index != 0){
                        for(int k = 0; k < index; k++){
                            for(int l = 0; l <= c; l++){
                                dp[i][j][index][l+1] += dp[i-1][j][k][l];
                                dp[i][j][index][l+1] += dp[i][j-1][k][l];
                                dp[i][j][index][l+1] %= MOD;
                            }
                        }
                    }
                    else{
                        for(int k = 0; k <= c; k++){
                            for(int l = 0; l <= c; l++){
                                dp[i][j][k][l] += dp[i-1][j][k][l];
                                dp[i][j][k][l] += dp[i][j-1][k][l];
                                dp[i][j][k][l] %= MOD;
                            }
                        }
                    }
                }
            }
        }

        int[] result = new int[c+1];
        for(int i = 0; i <= c; i++){
            for(int j = 0; j <= c; j++){
                result[j] += dp[n][m][i][j];
                result[j] %= MOD;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int k : result){
            sb.append(k).append(" ");
        }
        System.out.println(sb);
    }
}
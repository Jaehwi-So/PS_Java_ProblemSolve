import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(st.nextToken());
        for(int l = 0; l < t; l++){

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] money = new int[n+1];
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                money[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int[][] dp = new int[m+1][n+1];

            for(int i = 1; i <= m; i++){
                for(int j = 1; j <= n; j++){
                    dp[i][j] = dp[i][j-1];
                    if(i == money[j]){
                        dp[i][j] += 1;
                    }
                    else if(i > money[j]){
                        dp[i][j] += dp[i - money[j]][j];
                    }
                }
            }
//            for(int[] line : dp){
//                System.out.println(Arrays.toString(line));
//            }

            sb.append(dp[m][n]).append("\n");
        }
        System.out.println(sb);
    }
}

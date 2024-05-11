import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memory = new int[n+1];
        int[] cost = new int[n+1];


        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[10001][n+1];
        for(int[] line : dp){
            Arrays.fill(line, 0);
        }

        for(int i = 0; i <= 10000; i++){
            for(int j = 1; j <= n; j++){
                // 비활성화 비용이 0인 경우
                if(i == 0){
                    dp[i][j] = dp[i][j-1];
                    if(cost[j] == 0){
                        dp[i][j] += memory[j];
                    }
                }
                else{
                    if(i >= cost[j]){
                        dp[i][j] = Math.max(dp[i][j-1], dp[i-cost[j]][j-1] + memory[j]);
                    }
                    else{
                        dp[i][j] = dp[i][j-1];
                    }
                }

            }
            if(dp[i][n] >= m){
                System.out.println(i);
                break;
            }
        }

//        for(int[] line: dp){
//            System.out.println(Arrays.toString(line));
//        }
//        System.out.println(dp[m][n]);
    }
}
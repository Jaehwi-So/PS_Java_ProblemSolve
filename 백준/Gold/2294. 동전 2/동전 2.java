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
        int k = Integer.parseInt(st.nextToken());
        int[] values = new int[n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            values[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n+1][k+1];


        Arrays.sort(values);
        for(int i = 1; i <= k; i++){
            dp[0][i] = 100001;
        }


        for(int i = 1; i <= n; i++){ //3(1, 5, 12)
            for(int j = 1; j <= k; j++){ //15
                if(j-values[i] < 0){
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-values[i]] + 1);
                }

//                dp[i][j] = div + dp[i][mod];
            }
        }

//        for(int[] d : dp){
//           System.out.println(Arrays.toString(d));
//        }
        if(dp[n][k] == 100001){
            System.out.println(-1);
        }
        else{
            System.out.println(dp[n][k]);
        }


    }
}

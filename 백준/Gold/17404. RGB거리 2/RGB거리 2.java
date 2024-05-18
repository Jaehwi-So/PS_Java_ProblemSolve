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
        int[][] house = new int[n][3];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            house[i][0] = Integer.parseInt(st.nextToken());
            house[i][1] = Integer.parseInt(st.nextToken());
            house[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][3];
        int MAX_VALUE = 1000 * 1000;
        int result = MAX_VALUE;

        for(int i = 0; i < 3; i++){ //첫 번째 집의 색

            // 첫번째 집
            for(int j = 0; j < 3; j++){
                if(i == j){
                    dp[0][j] = house[0][j];
                }
                else{
                    dp[0][j] = MAX_VALUE;
                }
            }

            // 나머지 집
            for(int k = 1; k < n; k++){
                dp[k][0] = Math.min(dp[k-1][1], dp[k-1][2]) + house[k][0];
                dp[k][1] = Math.min(dp[k-1][0], dp[k-1][2]) + house[k][1];
                dp[k][2] = Math.min(dp[k-1][0], dp[k-1][1]) + house[k][2];
            }

            // 마지막 집 (첫번째 색이 아닌 경우)
            for(int j = 0; j < 3; j++){
                if(i != j){
                    result = Math.min(result, dp[n-1][j]);
                }
            }
        }

//        for(int[] line : dp){
//            System.out.println(Arrays.toString(line));
//        }

        System.out.println(result);


    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int[] array = new int[n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            array[i] = Integer.parseInt(st.nextToken());

        }
        int[][][] dp = new int[n+1][w+2][3];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w + 1; j++) {
                if(array[i] == 1) {
                    dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][2]) + 1;
                    dp[i][j][2] = Math.max(dp[i-1][j][2], dp[i-1][j-1][1]);
                }
                else if(array[i] == 2) {
                    if(i == 1 && j == 1){
                        continue;
                    }
                    else{
                        dp[i][j][1] = Math.max(dp[i-1][j][1], dp[i-1][j-1][2]);
                        dp[i][j][2] = Math.max(dp[i-1][j][2], dp[i-1][j-1][1]) + 1;
                    }

                }
            }
        }




        int max = Integer.MIN_VALUE;
        for (int j = 1; j <= w + 1; j++) {
            max = Math.max(Math.max(dp[n][j][1], dp[n][j][2]), max);
        }

        System.out.println(max);

//        for(int[][] matrix : dp){
//            for(int[] line : matrix){
//                System.out.print("(" + line[1] + " " + line[2] + ")" );
//            }
//            System.out.println();
//        }

    }
}
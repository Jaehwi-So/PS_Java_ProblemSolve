import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for(int l = 0; l < t; l++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            long[] array = new long[k];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < k; i++){
                array[i] = Long.parseLong(st.nextToken());
            }

            long[][] dp = new long[k][k];
            for(int i = 0; i < k-1; i++){
                dp[i][i+1] = array[i] + array[i+1];
                for(int j = i+2; j < k; j++){
                    dp[i][j] = dp[i][j-1] + array[j];

                }
            }

            for(int step = 2; step < k; step++){
                for(int i = 0; i < k - step; i++){
                    long min = Long.MAX_VALUE;
                    int j = i + step;
                    for(int v = i; v < j; v++){
                        min = Math.min(min, dp[i][v] + dp[v+1][j]);
//                        System.out.println(String.format("(%d, %d), (%d, %d) = %d", 0, v, v+1, j, dp[0][v] + dp[v+1][j]));
                    }
                    dp[i][j] += min;
                }
            }

//            for(long[] line : dp){
//                System.out.println(Arrays.toString(line));
//            }


            sb.append(dp[0][k-1]).append("\n");

        }

        System.out.println(sb);

    }
}

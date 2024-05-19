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
        int[] numbers = new int[t];
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < t; i++){
            st = new StringTokenizer(br.readLine());
            numbers[i] = Integer.parseInt(st.nextToken());
            max = Math.max(numbers[i], max);
        }

        long[][] dp = new long[max+4][4];
        for(long[] line : dp){
            Arrays.fill(line, 1L);
        }

        dp[2][2] = 2L;
        dp[2][3] = 2L;
        dp[3][2] = 2L;
        dp[3][3] = 3L;

        for(int i = 4; i <= max; i++){
            for(int j = 2; j <= 3; j++){
                dp[i][j] = dp[i][j-1] + dp[i-j][j];
            }
        }

//        for(int[] line: dp){
//            System.out.println(Arrays.toString(line));
//        }

        StringBuilder sb = new StringBuilder();
        for(int k : numbers){
            sb.append(dp[k][3]).append("\n");
        }
        System.out.println(sb);


    }
}
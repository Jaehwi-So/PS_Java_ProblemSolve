import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int[][] dp = new int[100][3];
        int[] money = new int[]{1, 10, 25};

        for(int i = 1; i < 100; i++){
            dp[i][0] = i;
            for(int j = 1; j < 3; j++){
                dp[i][j] = dp[i][j-1];
                if(money[j] <= i){
                    dp[i][j] = Math.min(dp[i][j], dp[i-money[j]][j] + 1);
                }
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(st.nextToken());
        for(int i = 0; i < test; i++){
            st = new StringTokenizer(br.readLine());
            long k = Long.parseLong(st.nextToken());
            long result = 0;
            while(k > 0){
                long div = k / 100;
                int mod = (int)(k % 100);
                k = div;
                result += Math.min(dp[mod][0], Math.min(dp[mod][1], dp[mod][2]));
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}
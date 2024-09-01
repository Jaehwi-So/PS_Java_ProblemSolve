import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<Integer> testcase = new ArrayList<>();
        int n = 0;
        while(true){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            if(k == 0) break;
            else{
                testcase.add(k);
                n = Math.max(k, n);
            }
        }

        long[][] dp = new long[n+1][n+1];
        for(int i = 1; i <= n; i++){
            dp[0][i] = 1;
        }

        for(int i = 1; i <= n; i++){
            for(int j = i; j <= n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int k : testcase){
            sb.append(dp[k][k]).append("\n");
        }
        System.out.println(sb);



    }
}
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
        int[] weight = new int[n+1];
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i = 1; i <= n; i++){
            weight[i] = Integer.parseInt(st.nextToken());
            sum += weight[i];
        }
        boolean[][] dp = new boolean[n+1][40001];

        Arrays.fill(dp[0], false);
        dp[0][0] = true;
        for(int i = 1; i <= n; i++){
            dp[i][0] = true;
        }

        for(int i = 1; i <= n; i++){    // (weight) 1, 4
            int w = weight[i];
            for(int j = 1; j <= sum; j++){ // 1, 2, 3, 4, 5
                boolean correct = (w == j) || dp[i-1][j];
                dp[i][j] = (correct || dp[i-1][j+w]);
            }
            for(int j = sum; j >= 1; j--){
                dp[i][j] = (dp[i][j] || dp[i-1][Math.abs(j-w)]);
            }
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            int k = Integer.parseInt(st.nextToken());
            char c = dp[n][k] == true ? 'Y' : 'N';
            sb.append(c).append(" ");
        }

        System.out.println(sb);

//        for(int i = 0; i <= n; i++){
//            for(int j = 0; j <= sum; j++){
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

    }
}

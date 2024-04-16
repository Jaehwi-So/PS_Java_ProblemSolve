import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 순회 -> 100억 -> X
public class Main {
    static long[] numbers;
    static int[][] range;
    static int maxR = Integer.MIN_VALUE;
    static int minR = Integer.MAX_VALUE;

    static long[] dp;

    static void calc(){
        long sum = 0;
        for(int i = minR; i <= maxR; i++){
            sum += numbers[i];
            dp[i] = sum;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        numbers = new long[n + 1];
        range = new int[m + 1][2];
        dp = new long[n + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            numbers[i] = Long.parseLong(st.nextToken());
        }

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            range[i][0] = Integer.parseInt(st.nextToken());
            range[i][1] = Integer.parseInt(st.nextToken());
            minR = Math.min(range[i][0], minR);
            maxR = Math.max(range[i][1], maxR);
        }
        

        calc();


        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= m; i++){
            long result = dp[range[i][1]] - dp[range[i][0]] + numbers[range[i][0]];
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}

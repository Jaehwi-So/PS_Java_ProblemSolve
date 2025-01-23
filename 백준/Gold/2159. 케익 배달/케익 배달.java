import java.io.*;
import java.util.*;

public class Main {
    static int[] dy = {0, 0, -1, 1, 0};
    static int[] dx = {-1, 1, 0, 0, 0};
    static int calc(int x, int y, int nx, int ny){
        return Math.abs(nx-x) + Math.abs(ny-y);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[][] points = new int[n+1][2];
        for(int i = 0; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[n+1][5];
        for(long[] line : dp){
            Arrays.fill(line, Long.MAX_VALUE);
        }
        for(int i = 0; i < 5; i++){
            dp[1][i] = calc(points[0][0], points[0][1], points[1][0] + dx[i], points[1][1] + dy[i]);
        }
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < 5; j++){
                for(int k = 0; k < 5; k++){
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i-1][k] + calc(points[i-1][0] + dx[k], points[i-1][1] + dy[k],
                            points[i][0] + dx[j], points[i][1] + dy[j]));
                }
            }
        }

        long result = Long.MAX_VALUE;
        for(int i = 0; i < 5; i++){
            result = Math.min(result, dp[n][i]);
        }
        System.out.println(result);
    }
}
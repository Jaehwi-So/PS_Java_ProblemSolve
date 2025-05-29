import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] costs;
    static int[][] dp; //i번째 일을 처리할 때, S의 사람들이 처리할 수 있는 최소 비용

    static int dfs(int work, int visited){
        if(work == n + 1){
            return 0;
        }
        if(dp[work][visited] == -1){
            dp[work][visited] = Integer.MAX_VALUE;
            for(int i = 1; i <= n; i++){
                if((visited & (1 << i - 1)) == 0){
                    int next = visited + (int)Math.pow(2, i - 1);
                    dp[work][visited] = Math.min(dp[work][visited], costs[i][work] + dfs(work + 1, next));
                }
            }
        }
        return dp[work][visited];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        costs = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n+1][(int)Math.pow(2, n)];
        for(int[] line : dp){
            Arrays.fill(line, -1);
        }
        System.out.println(dfs(1, 0));


    }
}
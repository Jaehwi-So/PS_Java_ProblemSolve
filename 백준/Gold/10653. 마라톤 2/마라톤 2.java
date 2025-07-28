import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int k;
    static int[][] points;
    static int[][] dp;
    static final int MAX = 100000000;
    
    static int getDist(int a, int b){
        return Math.abs(points[a][0] - points[b][0]) + Math.abs(points[a][1] - points[b][1]);
    }
    static int operate(int start, int cp){
        if(dp[start][cp] == MAX){
            for(int i = 0; i <= cp; i++){
                if(start + i + 1 > n) continue;
                dp[start][cp] = Math.min(dp[start][cp], getDist(start, start + i + 1) + operate(start + i + 1, cp - i));
            }
        }
        return dp[start][cp];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        points = new int[n+1][2];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n+1][k+1];
        for(int[] line : dp) Arrays.fill(line, MAX);

        Arrays.fill(dp[n], 0);

        System.out.println(operate(1, k));

    }
}
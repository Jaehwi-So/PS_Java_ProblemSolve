import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] sequence;
    static int[][][][] dp;

    static int operate(int step, int a, int b){
        if(step == m) return 0;
        int target = sequence[step];
        if(target == a || target == b){
            return operate(step + 1, a, b);
        }
        else{
            if(dp[target][a][b][0] == -1 || dp[target][a][b][0] == -1){
                dp[target][a][b][0] = Math.abs(a - target);
                dp[target][a][b][1] = Math.abs(b - target);
            }
            return Math.min(dp[target][a][b][0] + operate(step + 1, target, b),
                    dp[target][a][b][1] + operate(step + 1, a, target));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        sequence = new int[m];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            sequence[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n+1][n+1][n+1][2];
        for(int[][][] mat : dp){
            for(int[][] ma : mat){
                for(int[] line : ma){
                    Arrays.fill(line, -1);
                }
            }
        }

        System.out.println(operate(0, a, b));


    }
}
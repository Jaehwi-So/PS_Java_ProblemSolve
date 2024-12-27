import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] array;
    static int[] sum;
    static int[][][] dp;


    // 1~10
    // pivot : 1개분할 9, 2개분할 7, 3개분할 5
    static int calc(int start, int end, int m){
        if(dp[start][end][m] == -1){
            if(m == 1){
                int max = Integer.MIN_VALUE;
                for(int i = start; i <= end; i++){
                    for(int j = i; j <= end; j++){
                        max = Math.max(max, sum[j] - sum[i-1]);
                    }
                }
                dp[start][end][m] = max;
            }
            else{
                int max = Integer.MIN_VALUE;
                for(int pivot = start + 1; pivot <= end - (2 * (m-1)) + 1; pivot++){
                    max = Math.max(max, calc(start, pivot - 1, 1) + calc(pivot + 1, end, m-1));
                }
                dp[start][end][m] = max;
            }

        }
        return dp[start][end][m];

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new int[n+1];
        sum = new int[n+1];
        dp = new int[n+1][n+1][m+1];
        for(int[][] mat : dp){
            for(int[] line : mat){
                Arrays.fill(line, -1);
            }
        }
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            array[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i-1] + array[i];
        }

//        System.out.println(Arrays.toString(sum));
        System.out.println(calc(1, n, m));

    }
}
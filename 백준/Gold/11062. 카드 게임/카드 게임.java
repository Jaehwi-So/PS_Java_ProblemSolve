import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] array;
    static int[] prefixSum;
    static int[][] dp;

    static int sum(int s, int e){
        return prefixSum[e] - prefixSum[s-1];
    }
    static int operate(int start, int end){
        if(dp[start][end] == -1){
            if(start == end) dp[start][end] = array[start];
            else{
                int head = sum(start, end) - operate(start + 1, end);
                int tail = sum(start, end) - operate(start, end - 1);
                dp[start][end] = Math.max(head, tail);
            }
        }
        return dp[start][end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int test = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < test; t++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            array = new int[n+1];
            prefixSum = new int[n+1];
            dp = new int[n+1][n+1];

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++){
                array[i] = Integer.parseInt(st.nextToken());
                prefixSum[i] = array[i] + prefixSum[i-1];
            }

            for(int[] line : dp){
                Arrays.fill(line, -1);
            }

            sb.append(operate(1, n)).append("\n");
        }

        System.out.println(sb);

    }
}
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] dp;
    static char[] chs;
    static int operate(int start, int end){
        if(dp[start][end] == -1){
            if(start >= end) dp[start][end] = 0;
            else{
                for(int pivot = start; pivot < end; pivot++){
                    dp[start][end] = Math.max(dp[start][end], operate(start, pivot) + operate(pivot+1, end));
                }
                if((chs[start] == 'a' && chs[end] == 't') || (chs[start] == 'g' && chs[end] == 'c')){
                    dp[start][end] =  Math.max(dp[start][end], operate(start+1, end-1) + 2);
                }
            }
        }
        return dp[start][end];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        chs = st.nextToken().toCharArray();
        n = chs.length;
        dp = new int[n][n];
        for(int[] line : dp){
            Arrays.fill(line, -1);
        }
        System.out.println(operate(0, n-1));
    }
}
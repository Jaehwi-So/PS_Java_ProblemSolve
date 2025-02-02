import java.io.*;
import java.util.*;

public class Main {
    static int INF = Integer.MIN_VALUE;
    static int[] score;
    static char[] str1;
    static char[] str2;
    static int[][] dp;

    static int operate(int a, int b){
        if(dp[a][b] == INF){
            boolean equal = str1[a] == str2[b];
            if(a+1 == str1.length && b+1 == str2.length){
                if(equal) dp[a][b] = score[0];
                else dp[a][b] = score[2];
                dp[a][b] = Math.max(dp[a][b], score[1] * 2);
            }
            else if(a+1 == str1.length){
                dp[a][b] = Math.max(dp[a][b], score[1] + operate(a, b+1));

                int last = (str2.length - 1 - b) * score[1];
                if(equal) dp[a][b] = Math.max(dp[a][b], score[0] + last);
                else dp[a][b] = Math.max(dp[a][b], score[2] + last);
            }
            else if(b+1 == str2.length){
                dp[a][b] = Math.max(dp[a][b], score[1] + operate(a+1, b));

                int last = (str1.length - 1 - a) * score[1];
                if(equal) dp[a][b] = Math.max(dp[a][b], score[0] + last);
                else dp[a][b] = Math.max(dp[a][b], score[2] + last);
            }
            else{
                if(equal) dp[a][b] = Math.max(dp[a][b], score[0] + operate(a+1, b+1));
                else dp[a][b] = Math.max(dp[a][b], score[2] + operate(a+1, b+1));
                dp[a][b] = Math.max(dp[a][b], score[1] + operate(a+1, b));
                dp[a][b] = Math.max(dp[a][b], score[1] + operate(a, b+1));
            }
        }
        return dp[a][b];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        score = new int[3];
        for(int i = 0; i < 3; i++){
            score[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        str1 = st.nextToken().toCharArray();
        st = new StringTokenizer(br.readLine());
        str2 = st.nextToken().toCharArray();
        dp = new int[str1.length][str2.length];
        for(int[] line : dp){
            Arrays.fill(line, INF);
        }
        System.out.println(operate(0, 0));

    }
}
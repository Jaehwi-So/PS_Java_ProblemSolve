import java.io.*;
import java.util.*;

public class Main {
    static final int MIN = -10000000;
    static int n;
    static int aLen = 0;
    static int bLen = 0;
    static int[] A;
    static int[] B;
    static int[][][] dp;

    static int calc(int a, int b, int index){
        if(a == aLen || b == bLen){
            return 0;
        }

        if(dp[a][b][index] == MIN){
            dp[a][b][index] = Math.max(dp[a][b][index], (A[a] * B[b]) + calc(a+1, b+1, index+1));
            //남은 수의 개수  < 남은 칸의 개수
            if(aLen - a <= n - (index + 1)) dp[a][b][index] = Math.max(dp[a][b][index], calc(a, b+1, index+1));  // 8 0 -> 7 + 0 < 8
            if(bLen - b <= n - (index + 1)) dp[a][b][index] = Math.max(dp[a][b][index], calc(a+1, b, index+1));
        }
        return dp[a][b][index];
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] temp = new int[n];
        for(int i = 0; i < n; i++){
            int k = Integer.parseInt(st.nextToken());
            if(k != 0){
                temp[aLen] = k;
                aLen++;
            }
        }
        A = new int[aLen];
        for(int i = 0; i < aLen; i++){
            A[i] = temp[i];
        }

        st = new StringTokenizer(br.readLine());
        temp = new int[n];
        for(int i = 0; i < n; i++){
            int k = Integer.parseInt(st.nextToken());
            if(k != 0){
                temp[bLen] = k;
                bLen++;
            }
        }
        B = new int[bLen];
        for(int i = 0; i < bLen; i++){
            B[i] = temp[i];
        }

        dp = new int[aLen][bLen][n];
        for(int[][] mat : dp){
            for(int[] line : mat){
                Arrays.fill(line, MIN);
            }
        }

//        System.out.println(Arrays.toString(A));
//        System.out.println(Arrays.toString(B));
//        System.out.println(aLen + " " + bLen);

        System.out.println(calc(0, 0, 0));

    }
}
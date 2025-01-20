import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] array;
    static int[][] dp;
    static int[][] temp;
    static int operate(int a, int b){
        if(dp[a][b] == -1){
            dp[a][b] = temp[a][b];
            for(int i = a; i < b; i++){
                dp[a][b] = Math.min(dp[a][b], operate(a, i) + operate(i+1, b));
            }
        }
        return dp[a][b];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new int[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            array[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[n][n];
        temp = new int[n][n];

        for(int[] line : dp){
            Arrays.fill(line, -1);
        }

        for(int a = 0; a < n; a++){
            int sum = 0;
            int result = 0;
            for(int b = a; b < n; b++){
                if(m < sum + array[b]){
                    result += Math.pow((m - sum + 1), 2);
                    sum = 0;
                }
                sum += array[b] + 1; //8 5 3 4

                temp[a][b] = result;
                if(b != n-1) temp[a][b] += Math.pow((m - sum + 1), 2);
            }
        }

        System.out.println(operate(0, n-1));
    }
}
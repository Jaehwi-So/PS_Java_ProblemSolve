import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = Integer.MAX_VALUE;
    static int[] A;
    static int[] B;
    static int[][] dp;

    static int operate(int a, int b){
        if(a >= A.length || b >= B.length){
            return 0;
        }
        if(dp[a][b] == MAX){
            dp[a][b] = Math.abs(A[a] - B[b]) + operate(a+1, b+1);
            if(A.length - a > B.length - b){
                dp[a][b] = Math.min(dp[a][b], operate(a+1, b));
            }
        }
        return dp[a][b];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] nArr = new int[n];
        int[] mArr = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            nArr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            mArr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nArr);
        Arrays.sort(mArr);

        if(n >= m){
            A = nArr;
            B = mArr;
        }
        else{
            B = nArr;
            A = mArr;
        }

        dp = new int[A.length][B.length];
        for(int[] line : dp){
            Arrays.fill(line, MAX);
        }

        System.out.println(operate(0, 0));

    }
}
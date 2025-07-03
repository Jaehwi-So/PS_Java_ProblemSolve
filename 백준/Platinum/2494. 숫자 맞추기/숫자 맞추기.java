import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] source;
    static int[] dest;
    static int[][] dp;
    static StringBuilder sb = new StringBuilder();

    static void trace(int index, int leftRotate){
        int current = (source[index] + leftRotate) % 10;
        int left = ((dest[index] + 10) - current) % 10;
        int right = ((current + 10) - dest[index]) % 10;

        if(index == n){
            if(left <= right) sb.append(index).append(" ").append(left);
            else sb.append(index).append(" ").append((right * -1)).append("\n");
            return;
        }

        int next = (leftRotate + left) % 10;

        // 다음 것 중 최소값
        if(left + dp[index + 1][next] <= right + dp[index + 1][leftRotate]){
            sb.append(index).append(" ").append(left).append("\n");
            trace(index + 1, next);
        }
        else{
            sb.append(index).append(" ").append((right * -1)).append("\n");
            trace(index + 1, leftRotate);
        }
    }

    static int operate(int index, int leftRotate){
        if(index > n) return 0;
        if(dp[index][leftRotate] == Integer.MAX_VALUE){
            int current = (source[index] + leftRotate) % 10;

            int left = ((dest[index] + 10) - current) % 10;
            int right = ((current + 10) - dest[index]) % 10;

            int next = (leftRotate + left) % 10;
            dp[index][leftRotate] = Math.min(
                    left + operate(index + 1, next),
                    right + operate(index + 1, leftRotate)
            );
        }
        return dp[index][leftRotate];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        source = new int[n+1];
        dest = new int[n+1];
        st = new StringTokenizer(br.readLine());
        char[] s = st.nextToken().toCharArray();
        st = new StringTokenizer(br.readLine());
        char[] d = st.nextToken().toCharArray();

        for(int i = 1; i <= n; i++){
            source[i] = Character.getNumericValue(s[i-1]);
            dest[i] = Character.getNumericValue(d[i-1]);
        }

        dp = new int[n+1][10]; //0 ~ 9
        for(int[] line : dp){
            Arrays.fill(line, Integer.MAX_VALUE);
        }

        System.out.println(operate(1, 0));

        trace(1, 0);

        System.out.println(sb);


    }
}
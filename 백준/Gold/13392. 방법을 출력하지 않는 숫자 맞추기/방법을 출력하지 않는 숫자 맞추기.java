import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] current;
    static int[] target;
    static int[][] dp;

    static int operate(int index, int rotate){
        if(index == n + 1) return 0;
        if(dp[index][rotate] == -1){
            dp[index][rotate] = Integer.MAX_VALUE;
            int number = (current[index] + rotate) % 10;

            int left = ((target[index] + 10) - number) % 10;
            int right = ((number + 10) - target[index]) % 10; // 3 5      8 5

//            System.out.println("num : " + current[index] + " cur : " + number);
//            System.out.println(left + " " + right);
            dp[index][rotate] = Math.min(left + operate(index + 1, (rotate + left) % 10), right + operate(index + 1, rotate));
        }
        return dp[index][rotate];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        current = new int[n+1];
        target = new int[n+1];

        st = new StringTokenizer(br.readLine());
        char[] chs = st.nextToken().toCharArray();
        for(int i = 1; i <= n; i++){
            current[i] = Character.getNumericValue(chs[i-1]);
        }
        st = new StringTokenizer(br.readLine());
        chs = st.nextToken().toCharArray();
        for(int i = 1; i <= n; i++){
            target[i] = Character.getNumericValue(chs[i-1]);
        }

        // 10000 *
        dp = new int[n+1][10];
        for(int[] line : dp){
            Arrays.fill(line, -1);
        }

        System.out.println(operate(1, 0));

    }
}
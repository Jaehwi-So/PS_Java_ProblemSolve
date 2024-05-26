import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[] array = new int[m+1];
        int[] diff = new int[m+2];

        array[0] = 0;
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            array[i] = Integer.parseInt(st.nextToken());
            diff[i] = array[i] - array[i-1] - 1;
            max = Math.max(diff[i], max);
        }

        diff[m+1] = n - array[m];
        max = Math.max(diff[m+1], max);

        int[] dp = new int[max + 3];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= max; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        long result = 1;
        for(int i = 1; i < diff.length; i++){
            if(diff[i] == 0){
                continue;
            }
            result *= dp[diff[i]];
        }
        System.out.println(result);

    }
}

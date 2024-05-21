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
        int[] array = new int[n+1];
        int[] dp = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            dp[i] = array[i];
            for(int j = i - 1; j >= 1;  j--){
                if(array[j] < array[i]){
                    dp[i] = Math.max(dp[i], array[i] + dp[j]);
                }
            }
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}

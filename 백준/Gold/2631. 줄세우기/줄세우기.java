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

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            array[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+1];
        int max = Integer.MIN_VALUE;

        for(int i = 1; i <= n; i++){
            dp[i] = 1;
            for(int j = i - 1; j >= 0; j--){
                if(array[j] < array[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(n - max);




    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char[] array = st.nextToken().toCharArray();
        int n = array.length;
        int[][] isPelindrom = new int[n][n];
        for(int i = 0; i < n; i++){
            isPelindrom[i][i] = 1;
        }
        for(int k = 1; k < n; k++){
            for(int i = 0; i < n - k; i++){
                if(array[i] == array[i+k] && (k == 1 || isPelindrom[i+1][i+k-1] == 1)){
                    isPelindrom[i][i+k] = 1;
                }
                else{
                    isPelindrom[i][i+k] = 0;
                }
            }
        }

        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= i; j++){
                if(isPelindrom[j-1][i-1] == 1){
                    dp[i] = Math.min(dp[j-1] + 1, dp[i]);
                }
            }
        }
        System.out.println(dp[n]);


//        System.out.println(Arrays.toString(dp));
//
//        for(int line[] : isPelindrom){
//            System.out.println(Arrays.toString(line));
//        }

    }
}
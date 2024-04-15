import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] sequence;

    static int[][] dp;

    static int lis(int x){
        if(dp[x][0] == 0){
            dp[x][0] = 1;
            for(int i = x-1; i >= 0; i--){
                if(sequence[i] < sequence[x]){
                    dp[x][0] = Math.max(dp[x][0], dp[i][0] + 1);
                }
            }
        }
        return dp[x][0];
    }

    static int lds(int x){
        if(dp[x][1] == 0){
            dp[x][1] = 1;
            for(int i = x + 1; i < n; i++){
                if(sequence[i] < sequence[x]){
                    dp[x][1] = Math.max(dp[x][1], dp[i][1] + 1);
                }
            }
        }
        return dp[x][1];
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sequence = new int[n];
        for(int i = 0; i < n; i++){
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n][2];
        for(int i = 0; i < n; i++){
            lis(i);
        }

        for(int i = n - 1; i >= 0; i--){
            lds(i);
        }

        for(int i = 0; i < n; i++){
            lds(i);
        }

//        for(int i = 0; i < n; i++){
//            System.out.println(dp[i][0] + " " + dp[i][1]);
//        }
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            max = Math.max(max, (dp[i][0] + dp[i][1] - 1));
        }
        System.out.println(max);

    }
}
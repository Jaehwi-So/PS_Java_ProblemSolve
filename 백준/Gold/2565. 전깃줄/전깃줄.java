import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 1 8
 * 2 2   *
 * 3 9
 * 4 1
 * 6 4   *
 * 7 6   *
 * 9 7   *
 * 10 10  *
 */

public class Main {
    static int n;
    static int data[][];
    static int dp[];

    static int connect(int k){
        if(dp[k] == 0){
            dp[k] = 1;
            for(int i = k - 1; i >= 0; i--){
                if(data[k][1] > data[i][1]){
                    dp[k] = Math.max(dp[i] + 1, dp[k]);
                }
            }
        }
        return dp[k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        data = new int[n][2];
        dp = new int[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(data, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for(int i = 0; i < n; i++){
            connect(i);
        }

//        for(int i = 0; i < n; i++){
//            System.out.print(data[i][1] + " ");
//        }
//        System.out.println();
//        for(int i = 0; i < n; i++){
//            System.out.print(dp[i] + " ");
//        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            max = Math.max(max, dp[i]);
        }

        System.out.println(n - max);
        
    }
}
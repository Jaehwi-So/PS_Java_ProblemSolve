import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] costs = new int[n+1][2];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            costs[i][0] = Integer.parseInt(st.nextToken());
            costs[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[][] dp = new int[100001][n+1];

        int result = 0;
        for(int i = 1; i <= 100000; i++){
            boolean exit = false;
            for(int j = 1; j <= n; j++){
                dp[i][j] = dp[i][j-1];
                if(i >= costs[j][0]){
                    dp[i][j] = Math.max(dp[i][j], dp[i-costs[j][0]][j] + costs[j][1]);
                }
                if(dp[i][j] >= k){
                    result = i;
                    exit = true;
                    break;
                }
            }
            if(exit){
                break;
            }
        }

        System.out.println(result);

//        for(int i = 1; i <= result; i++){
//            System.out.println(Arrays.toString(dp[i]));
//        }

    }
}

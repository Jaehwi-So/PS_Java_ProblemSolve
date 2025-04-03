import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < 3; t++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] money = new int[n][2];
            int sum = 0;
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                money[i][0] = Integer.parseInt(st.nextToken());
                money[i][1] = Integer.parseInt(st.nextToken());
                sum += (money[i][0] * money[i][1]);

            }
            Arrays.sort(money, new Comparator<int[]>(){
                public int compare(int[] a1, int[] a2){
                    return a1[0] - a2[0];
                }
            });

            int result = 0;
            if(sum % 2 == 0){
                sum /= 2;
                boolean[][] dp = new boolean[sum+1][n];
                for(int i = 1; i <= money[0][1]; i++){
                    if(money[0][0] * i > sum) break;
                    dp[money[0][0] * i][0] = true;
                }

                for(int i = 1; i <= sum; i++){
                    for(int j = 1; j < n; j++){
                        dp[i][j] = dp[i][j-1];
                        for(int k = 1; k <= money[j][1]; k++){
                            int cost = money[j][0] * k;
                            if(cost <= i){
                                dp[i][j] = dp[i][j] || dp[i-cost][j-1];
                            }
                            else break;
                        }
                    }
                }

                result = dp[sum][n-1] ? 1 : 0;
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[][] sequence = new int[n+1][3];
        Arrays.fill(sequence[0], 0);

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            sequence[i][0] = Integer.parseInt(st.nextToken());
            sequence[i][1] = Integer.parseInt(st.nextToken());
            sequence[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sequence, new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2){
                return a1[2] - a2[2];
            }
        });

        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 0; j < i; j++){
                if(dp[j] == -1) continue;
                double dist = Math.sqrt(Math.pow((sequence[i][0] - sequence[j][0]), 2) + Math.pow((sequence[i][1] - sequence[j][1]), 2)) / s;
                if(dist <= sequence[i][2] - sequence[j][2]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int result = 0;
        for(int i = 0; i <= n; i++){
            result = Math.max(result, dp[i]);
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(result);
    }
}
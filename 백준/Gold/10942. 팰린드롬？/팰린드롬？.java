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
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], 2);
            dp[i][i] = 1;
        }
        for(int k = 1; k < n; k++){
            for(int i = 0; i < n - k; i++){
                if(array[i] == array[i+k] && dp[i+1][i+k-1] != 0){
                    dp[i][i+k] = 1;
                }
                else{
                    dp[i][i+k] = 0;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int[][] points = new int[m][2];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            points[i][0] = s;
            points[i][1] = e;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++){
            int start = points[i][0];
            int end = points[i][1];
            sb.append(dp[start][end]).append("\n");
        }
        System.out.println(sb);
    }
}
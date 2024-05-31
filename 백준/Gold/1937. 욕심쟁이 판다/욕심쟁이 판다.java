import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] array;
    static int[][] dp;
    static int[] dx = {0 , 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static int dfs(int srow, int scol){
        if(dp[srow][scol] == -1){
            int max = 1;
            for(int i = 0; i < 4; i++){
                int row = srow + dy[i];
                int col = scol + dx[i];
                if(row >= 0 && row < n && col >= 0 && col < n){
                    if(array[row][col] > array[srow][scol]){
                        max = Math.max(max, dfs(row, col) + 1);
                    }
                }
            }
            dp[srow][scol] = max;
        }
        return dp[srow][scol];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        array = new int[n][n];
        dp = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }


        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                dfs(i, j);
            }
        }

        int max = 1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(max, dp[i][j]);
            }
        }
//        for(int[] line : dp){
//            System.out.println(Arrays.toString(line));
//        }

        System.out.println(max);

    }
}
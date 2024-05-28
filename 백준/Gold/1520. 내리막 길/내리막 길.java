import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][] dp;
    static int n;
    static int m;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0 ,0};

    static int dfs(int r, int c){
        if(r == 0 && c == 0){
            return 1;
        }
        if(dp[r][c] == -1){
            dp[r][c] = 0;
            for(int i = 0; i < 4; i++){
                int row = r + dy[i];
                int col = c + dx[i];
                if(row < 0 || row >= n || col < 0 || col >= m){
                    continue;
                }
                if(map[row][col] > map[r][c]){
                    dp[r][c] += dfs(row, col);
                }
            }
        }
        return dp[r][c];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dp = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(n-1, m-1));

//        for(int[] line : dp){
//            System.out.println(Arrays.toString(line));
//        }
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] board;
    static boolean[][] visited;
    static int[][] dp;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int INF = 1000000000;
    static int get(int row, int col){
        if(dp[row][col] == -1){
            dp[row][col] = 1;
            for(int i = 0; i < 4; i++){
                int nrow = row + (dy[i] * board[row][col]);
                int ncol = col + (dx[i] * board[row][col]);
                if(nrow < 0 || nrow >= n || ncol < 0 || ncol >= m){
                    continue;
                }
                if(visited[nrow][ncol]){
                    return INF;
                }
                visited[row][col] = true;
                dp[row][col] = Math.max(dp[row][col], get(nrow, ncol) + 1);
                visited[row][col] = false;
            }
        }
        return dp[row][col];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        dp = new int[n][m];
        visited = new boolean[n][m];
        for(int[] line : dp){
            Arrays.fill(line, -1);
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            char[] chs = st.nextToken().toCharArray();
            for(int j = 0; j < m; j++){
                if(chs[j] == 'H'){
                    board[i][j] = 0;
                    dp[i][j] = 0;
                }
                else board[i][j] = Character.getNumericValue(chs[j]);
            }
        }

        int result = get(0, 0);
        if(result >= INF){
            result = -1;
        }
        System.out.println(result);
//        for(int[] line : dp){
//            System.out.println(Arrays.toString(line));
//        }
    }
}
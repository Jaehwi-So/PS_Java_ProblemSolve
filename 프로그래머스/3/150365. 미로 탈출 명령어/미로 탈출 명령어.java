//dlru
import java.util.*;

class Point{
    int row;
    int col;
    public Point(int row, int col){
        this.row = row;
        this.col = col;
    }
}
class Solution {
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int m;
    static int k;
    static Point start;
    static Point end;
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {1, 0, 0, -1};
    static String[] command = {"d", "l", "r", "u"};
    static String[][][] dp;
    static String dfs(int step, int row, int col){
        if(step > k){
            return "X";
        }
        if(dp[step][row][col] == null){
            dp[step][row][col] = "X";
            for(int i = 0; i < 4; i++){
                int nrow = row + dy[i];
                int ncol = col + dx[i];
                if(nrow <= 0 || ncol <= 0 || nrow > n || ncol > m){
                    continue;
                }
                String next = dfs(step + 1, nrow, ncol);
                if(!next.equals("X")){
                    dp[step][row][col] = command[i] + next;
                    break;
                }
            }
        }
        return dp[step][row][col];
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.start = new Point(x, y);
        this.end = new Point(r, c);
        this.dp = new String[k+1][n+1][m+1];
        dp[k][r][c] = "";
        String result = dfs(0, x, y);
        if(result.equals("X")){
            result = "impossible";
        }
        return result;
    }
}
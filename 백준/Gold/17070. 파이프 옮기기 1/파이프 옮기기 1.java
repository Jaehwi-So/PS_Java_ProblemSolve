import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] array;
    static int[][][] dp;
    static int[] dx = {-1, 0, -1};
    static int[] dy = {0, -1, -1};

    static int dfs(int row, int col, int state){
        if(row == 0 && col == 1){
            if(state == 0){
                dp[row][col][state] = 1;
            }
            else{
                dp[row][col][state] = 0;
            }

        }
        else if(dp[row][col][state] == -1){
            dp[row][col][state] = 0;

            int r = row + dy[state];
            int c = col + dx[state];
            if(r < 0 || c < 0 || r >= n || c >= n){

            }
            else if(array[row][col] == 1 || array[r][c] == 1 || array[row][c] == 1 || array[r][col] == 1){

            }
            else{
                if(state == 0){ // 가로
                    dp[row][col][state] += dfs(r, c, 0); //가로
                    dp[row][col][state] += dfs(r, c, 2); //대각
                }
                else if(state == 1){ // 세로
                    dp[row][col][state] += dfs(r, c, 1); //세로
                    dp[row][col][state] += dfs(r, c, 2);
                }
                else{ // 대각
                    dp[row][col][state] += dfs(r, c, 0);
                    dp[row][col][state] += dfs(r, c, 1);
                    dp[row][col][state] += dfs(r, c, 2); //대각
                }
            }




        }
        return dp[row][col][state];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        array = new int[n][n];
        dp = new int[n][n][3];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
                dp[i][j][2] = -1;
            }
        }

        int result = 0;
        for(int i = 0; i < 3; i++){
            result += dfs(n-1, n-1, i);
        }

        System.out.println(result);

//        for(int i = 0; i < 3; i++){
//            for(int[][] matrix : dp){
//                for(int[] line : matrix){
//                    System.out.print(line[i] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }
    }
}

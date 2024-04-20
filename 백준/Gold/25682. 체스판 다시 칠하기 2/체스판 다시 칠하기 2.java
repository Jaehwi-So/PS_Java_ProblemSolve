import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int k;
    static int board[][][];

    static boolean check(int i, int j, char c){
        boolean isBlack;
        if(i % 2 == 0){
            if(j % 2 == 0){
                if(c == 'B'){
                    isBlack = true;
                }
                else{
                    isBlack = false;
                }
            }
            else{
                if(c == 'B'){
                    isBlack = false;
                }
                else{
                    isBlack = true;
                }
            }
        }
        else{
            if(j % 2 == 0){
                if(c == 'B'){
                    isBlack = false;
                }
                else{
                    isBlack = true;
                }
            }
            else{
                if(c == 'B'){
                    isBlack = true;
                }
                else{
                    isBlack = false;
                }
            }
        }
        return isBlack;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[2][n+1][m+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            char[] chs = st.nextToken().toCharArray();
            for(int j = 1; j <= m; j++){

                if(check(i, j, chs[j-1])){    //Black이 들어올 자리가 맞을 경우
                    board[0][i][j] = 0;
                    board[1][i][j] = 1;
                }
                else{  //White가 들어올 자리가 맞을 경우
                    board[0][i][j] = 1;
                    board[1][i][j] = 0;
                }
                board[0][i][j] += (board[0][i-1][j] + board[0][i][j-1] - board[0][i-1][j-1]);
                board[1][i][j] += (board[1][i-1][j] + board[1][i][j-1] - board[1][i-1][j-1]);
            }
        }

//        for(int[][] bbb : board){
//            System.out.println("=======");
//            for(int[] bb : bbb){
//                System.out.println(Arrays.toString(bb));
//            }
//        }

        int min = Integer.MAX_VALUE;
        for(int i = k; i <= n; i++){
            for(int j = k; j <= m; j++){
                int blackResult = board[0][i][j] - board[0][i][j-k] - board[0][i-k][j] + board[0][i-k][j-k];
                int whiteResult = board[1][i][j] - board[1][i][j-k] - board[1][i-k][j] + board[1][i-k][j-k];
                min = Math.min(Math.min(blackResult, whiteResult), min);
            }
        }
        System.out.println(min);



    }
}

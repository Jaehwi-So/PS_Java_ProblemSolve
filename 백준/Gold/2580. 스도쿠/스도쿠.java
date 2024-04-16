import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[9][9];

    static boolean isPromising(int row, int col, int k){

        //같은 줄에 같은 수가 있는 지 판단
        for(int i = 0; i < 9; i++){
            if(board[row][i] == k){
                return false;
            }
        }

        //같은 열에 같은 수가 있는 지 판단
        for(int i = 0; i < 9; i++){
            if(board[i][col] == k){
                return false;
            }
        }

        //3*3 내부에 같은 수가 있는 지 판단
        //0, 1, 2 -> 0, 2
        //3, 4, 5 -> 3, 5
        int startRow = (row / 3) * 3;
        int endRow = ((row / 3) * 3) + 2;
        int startCol = (col / 3) * 3;
        int endCol = (col / 3) * 3 + 2;

        for(int i = startRow; i <= endRow; i++){
            for(int j = startCol; j <= endCol; j++){
                if(board[i][j] == k){
                    return false;
                }
            }
        }


        return true;
    }
    static boolean solve(int row, int col){

        if(col == 9){
            return solve(row + 1, 0);
        }

        if(row == 9){
            print();
            return true;
        }

        if(board[row][col] == 0){
            for(int k = 1; k <= 9; k++){
                if(isPromising(row, col, k)){
                    board[row][col] = k;
                    boolean isSolve = solve(row, col + 1);
                    if(isSolve){
                        return true;
                    }
                    board[row][col] = 0;
                }
            }
            return false;
        }
        else{
            return solve(row, col + 1);
        }

    }

    static void print(){
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < 9; k++){
            for(int i = 0; i < 9; i++){
                sb.append(board[k][i]).append(" ");
            }
           sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < 9; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve(0, 0);
    }
}
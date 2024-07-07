import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] array = new int[9][9];

    static boolean isPromising(int row, int col, int k){
        //k가 같으면 원래 있던 스도쿠 번호
        if(array[row][col] == k){
            return true;
        }

        //같은 줄에 같은 수가 있는 지 판단
        for(int i = 0; i < 9; i++){
            if(array[row][i] == k){
                return false;
            }
        }

        //같은 열에 같은 수가 있는 지 판단
        for(int i = 0; i < 9; i++){
            if(array[i][col] == k){
                return false;
            }
        }

        //3*3 내부에 같은 수가 있는 지 판단
        int startRow = (row / 3) * 3;
        int endRow = ((row / 3) * 3) + 2;
        int startCol = (col / 3) * 3;
        int endCol = (col / 3) * 3 + 2;

        for(int i = startRow; i <= endRow; i++){
            for(int j = startCol; j <= endCol; j++){
                if(array[i][j] == k){
                    return false;
                }
            }
        }
        return true;
    }

    static boolean solve(int row, int col){
        if(row == 9){
            print();
            return true;
        }
        
        if(array[row][col] != 0){
            boolean isSolve;
            if(col == 8){
                isSolve = solve(row + 1, 0);
            }
            else{
                isSolve = solve(row, col + 1);
            }

            if(isSolve){
                return true;
            }
        }

        else{
            for(int k = 1; k <= 9; k++){
                if(isPromising(row, col, k)){
                    array[row][col] = k;
                    boolean isSolve;
                    if(col == 8){
                        isSolve = solve(row + 1, 0);
                    }
                    else{
                        isSolve = solve(row, col + 1);
                    }

                    if(isSolve){
                        return true;
                    }
                    array[row][col] = 0;
                }
            }
        }
        return false;
    }

    static void print(){
        StringBuilder sb = new StringBuilder();
        for(int k = 0; k < 9; k++){
            for(int i = 0; i < 9; i++){
                sb.append(array[k][i]);
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
            char[] chs = st.nextToken().toCharArray();
            for(int j = 0; j < 9; j++){
                array[i][j] = Character.getNumericValue(chs[j]);
            }
        }
        solve(0, 0);


    }
}

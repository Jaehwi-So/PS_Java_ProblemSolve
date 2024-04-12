import java.util.Arrays;
import java.util.Scanner;

/**
 * 가로, 세로, 대각선 방향에 Queen이 없어야 한다.
 */
public class Main {
    static int[][] array;
    static int n;

    static int count = 0;

    static boolean isPromising(int row, int col){
        //같은 열 확인
        for(int i = 0; i < row; i++){
            if(array[i][col] == 1){
                return false;
            }
        }
        // 왼쪽 위 대각선 확인
        int i = row;
        int j = col;
        while(i >= 0 && j >= 0){
            if(array[i][j] == 1){
                return false;
            }
            i--;
            j--;
        }

        // 오른쪽 위 대각선 확인
        i = row;
        j = col;
        while(i >= 0 && j < n){
            if(array[i][j] == 1){
                return false;
            }
            i--;
            j++;
        }
        return true;
    }
    static void nQueen(int step){
        if(step == n){
//            print();
            count++;
        }

        for(int i = 0; i < n; i++){
            if(isPromising(step, i)){
                array[step][i] = 1;
                nQueen(step + 1);
                array[step][i] = 0;
            }
        }
    }

    static void print(){
        System.out.println("Solve");
        for(int[] row : array){
            for(int col : row){
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        array = new int[n][n];
        for(int[] row : array){
            Arrays.fill(row, 0);
        }
        nQueen(0);
        System.out.println(count);

    }

}

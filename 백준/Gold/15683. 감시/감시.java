import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] array;
    static List<int[]> points;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;

    static void setArray(int step, int row, int col, int d){ //왼, 오, 위, 아래
        int number = step + 10;
        row += dy[d];
        col += dx[d];
        while(row >= 0 && row < n && col >= 0 && col < m){
            if(array[row][col] == 6){
                break;
            }
            if(array[row][col] == 0){
                array[row][col] = number;
            }
            row += dy[d];
            col += dx[d];
        }
    }

    static void rollbackArray(int step){
        int number = step + 10;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(array[i][j] == number){
                    array[i][j] = 0;
                }
            }
        }
    }

    static int calcSpace(){
        int result = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(array[i][j] == 0){
                    result++;
                }
            }
        }
        return result;
    }

    static void backtrack(int step){
        if(step == points.size()){
            min = Math.min(min, calcSpace());
        }
        else{
            int row = points.get(step)[0];
            int col = points.get(step)[1];
            int type = array[row][col];
            if(type == 1){
                setArray(step, row, col, 0);
                backtrack(step + 1);
                rollbackArray(step);
                setArray(step, row, col, 1);
                backtrack(step + 1);
                rollbackArray(step);
                setArray(step, row, col, 2);
                backtrack(step + 1);
                rollbackArray(step);
                setArray(step, row, col, 3);
                backtrack(step + 1);
                rollbackArray(step);
            }
            else if(type == 2){
                setArray(step, row, col, 0);
                setArray(step, row, col, 1);
                backtrack(step + 1);
                rollbackArray(step);

                setArray(step, row, col, 2);
                setArray(step, row, col, 3);
                backtrack(step + 1);
                rollbackArray(step);

            }
            else if(type == 3){
                setArray(step, row, col, 2);
                setArray(step, row, col, 1);
                backtrack(step + 1);
                rollbackArray(step);

                setArray(step, row, col, 1);
                setArray(step, row, col, 3);
                backtrack(step + 1);
                rollbackArray(step);

                setArray(step, row, col, 3);
                setArray(step, row, col, 0);
                backtrack(step + 1);
                rollbackArray(step);

                setArray(step, row, col, 0);
                setArray(step, row, col, 2);
                backtrack(step + 1);
                rollbackArray(step);

            }
            else if(type == 4){
                setArray(step, row, col, 1);
                setArray(step, row, col, 2);
                setArray(step, row, col, 3);
                backtrack(step + 1);
                rollbackArray(step);

                setArray(step, row, col, 0);
                setArray(step, row, col, 2);
                setArray(step, row, col, 3);
                backtrack(step + 1);
                rollbackArray(step);

                setArray(step, row, col, 0);
                setArray(step, row, col, 1);
                setArray(step, row, col, 3);
                backtrack(step + 1);
                rollbackArray(step);

                setArray(step, row, col, 0);
                setArray(step, row, col, 1);
                setArray(step, row, col, 2);
                backtrack(step + 1);
                rollbackArray(step);

            }
            else if(type == 5){
                setArray(step, row, col, 0);
                setArray(step, row, col, 1);
                setArray(step, row, col, 2);
                setArray(step, row, col, 3);
                backtrack(step + 1);
                rollbackArray(step);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new int[n][m];
        points = new ArrayList<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
                if(array[i][j] > 0 && array[i][j] < 6){
                    points.add(new int[]{i, j});
                }
            }
        }

        backtrack(0);

        System.out.println(min);

//        setArray(0, 0, 0, 1);
//
//        for(int[] line: array){
//            System.out.println(Arrays.toString(line));
//        }
    }
}

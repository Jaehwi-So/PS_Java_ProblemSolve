import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n; //세로선(사람수)
    static int m; //이미 놓인 가로선
    static int h; //높이(가로선 높을 수 있는 영역)
    static int[][] array;
    static int seq = 1;
    static int min = 4;

    static boolean getResult(){
        for(int i = 0; i < n; i++){
            int pos = i;
            for(int j = 0; j < h; j++){
                if(array[j][pos] != 0){
                    if(pos+1 < n && array[j][pos+1] == array[j][pos]){
                        pos = pos+1;
                    }
                    else if(pos-1 >= 0 && array[j][pos-1] == array[j][pos]){
                        pos = pos-1;
                    }
                }
            }
            if(pos != i){
                return false;
            }
        }
        return true;
    }

    static void backtrack(int step, int row, int col){
        if(step > min || step > 3){
            return;
        }
        if(col == n){
            row++;
            col = 0;
        }
        if(getResult()){
            min = Math.min(min, step);
        }
        if(row != h){
            for(int j = 0; j < n - 1; j++){
                if(array[row][j] == 0 && array[row][j+1] == 0){
                    seq++;
                    array[row][j] = seq;
                    array[row][j+1] = seq;
                    backtrack(step+1, row, j+2);
                    seq--;
                    array[row][j] = 0;
                    array[row][j+1] = 0;
                }
            }
            for(int i = row + 1; i < h; i++){
                for(int j = 0; j < n - 1; j++){
                    if(array[i][j] == 0 && array[i][j+1] == 0){
                        seq++;
                        array[i][j] = seq;
                        array[i][j+1] = seq;
                        backtrack(step+1, i, j+2);
                        seq--;
                        array[i][j] = 0;
                        array[i][j+1] = 0;
                    }
                }
            }
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        array = new int[h][n];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            array[a][b] = seq;
            array[a][b+1] = seq++;
        }

//        for(int[] line : array){
//            System.out.println(Arrays.toString(line));
//        }

        backtrack(0, 0, 0);
        if(min > 3){
            min = -1;
        }
        System.out.println(min);
    }
}
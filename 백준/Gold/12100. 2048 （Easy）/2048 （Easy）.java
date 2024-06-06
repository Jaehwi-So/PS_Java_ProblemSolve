import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] array;
    static int result = Integer.MIN_VALUE;

    static void print(){
        for(int[] line : array){
            System.out.println(Arrays.toString(line));
        }
        System.out.println();
    }

    static void move(int direct){ //위,아래,왼,오른
        // 위
        if(direct == 0){
            for(int j = 0; j < n; j++){
                int index = 0;
                int mergeIndex = -1;
                int beforeIndex = 0;
                for(int i = 0; i < n; i++){
                    if(array[i][j] != 0){
                        int number = array[i][j];
                        array[i][j] = 0;
                        if(index > 0 && array[beforeIndex][j] == number && (mergeIndex != beforeIndex)){
                            array[beforeIndex][j] += number;
                            mergeIndex = beforeIndex;
                        }
                        else{
                            array[index][j] = number;
                            beforeIndex = index;
                            index++;
                        }

                    }

                }
            }
        }
        // 아래
        else if(direct == 1){
            for(int j = 0; j < n; j++){
                int index = n - 1;
                int mergeIndex = -1;
                int beforeIndex = 0;
                for(int i = n-1; i >= 0; i--){
                    if(array[i][j] != 0){
                        int number = array[i][j];
                        array[i][j] = 0;
                        if(index < n-1 && array[beforeIndex][j] == number && (mergeIndex != beforeIndex)){
                            array[beforeIndex][j] += number;
                            mergeIndex = beforeIndex;
                        }
                        else{
                            array[index][j] = number;
                            beforeIndex = index;
                            index--;
                        }

                    }
                }
            }
        }

        //왼쪽
        else if(direct == 2){
            for(int i = 0; i < n; i++){
                int index = 0;
                int mergeIndex = -1;
                int beforeIndex = 0;
                for(int j = 0; j < n; j++){
                    if(array[i][j] != 0){
                        int number = array[i][j];
                        array[i][j] = 0;
                        if(index > 0 && array[i][beforeIndex] == number && (mergeIndex != beforeIndex)){
                            array[i][beforeIndex] += number;
                            mergeIndex = beforeIndex;
                        }
                        else{
                            array[i][index] = number;
                            beforeIndex = index;
                            index++;
                        }
                    }
                }
            }
        }
        else{
            for(int i = n - 1; i >= 0; i--){
                int index = n - 1;
                int mergeIndex = -1;
                int beforeIndex = 0;
                for(int j = n-1; j >= 0; j--){
                    if(array[i][j] != 0){
                        int number = array[i][j];
                        array[i][j] = 0;
                        if(index < n-1 && array[i][beforeIndex] == number && (mergeIndex != beforeIndex)){
                            array[i][beforeIndex] += number;
                            mergeIndex = beforeIndex;
                        }
                        else{
                            array[i][index] = number;
                            beforeIndex = index;
                            index--;

                        }
                    }
                }
            }
        }



    }

    static int calc(){
        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(max, array[i][j]);
            }
        }
        return max;
    }

    static void backtrack(int step){
        if(step == 5){
            int r = calc();
            if(r > result){
                result = r;
            }

        }
        else{
            int[][] temp = new int[n][];
            for(int i = 0; i < n; i++) {
                temp[i] = Arrays.copyOf(array[i], n);
            }

            move(0);
            backtrack(step + 1);
            for(int i = 0; i < n; i++) {
                array[i] = Arrays.copyOf(temp[i], n);
            }

            move(1);
            backtrack(step + 1);
            for(int i = 0; i < n; i++) {
                array[i] = Arrays.copyOf(temp[i], n);
            }

            move(2);
            backtrack(step + 1);
            for(int i = 0; i < n; i++) {
                array[i] = Arrays.copyOf(temp[i], n);
            }

            move(3);
            backtrack(step + 1);
            for(int i = 0; i < n; i++) {
                array[i] = Arrays.copyOf(temp[i], n);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        array = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        move(1);
        backtrack(0);
        System.out.println(result);


    }
}
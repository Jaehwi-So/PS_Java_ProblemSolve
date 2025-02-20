import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int k;
    static int[][] matrix;

    // 맨 처음 가장 작은 어항에 물고기를 추가
    static void firstAdd(){
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] != -1){
                    min = Math.min(min, matrix[i][j]);
                }
            }
        }
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == min){
                    matrix[i][j]++;
                }
            }
        }
    }

    static int[] getSize(int[][] array){
        int leftWidth = 0, leftHeight = 0, rightWidth = 0, rightHeight = 1;

        for(int i = array.length - 1; i >= 0; i--){
            if(array[i][0] == -1) break;
            leftHeight++;
        }

        if(leftHeight == 1){
            leftWidth = 1;
            rightWidth = -1;
        }

        int i = array.length - 1;
        for(int j = 0; j < array[i].length; j++){
            if(array[i][j] == -1) break;
            if(array[i-leftHeight+1][j] != -1 && leftHeight != 1){
                leftWidth++;
            }
            else{
                rightWidth++;
            }
        }
        return new int[]{leftWidth, leftHeight, rightWidth, rightHeight};
    }

    static void firstRotate(){
        while(true){
            int[] size = getSize(matrix);
            int leftWidth = size[0];
            int leftHeight = size[1];
            int rightWidth = size[2];
            int rightHeight = size[3];
            if(leftHeight > rightWidth){
//                System.out.println("Cannot");
                break;
            }
//            System.out.println(leftWidth + " " + leftHeight + " " + rightWidth + " " + rightHeight);

            int rightStart = leftWidth;
            int[][] temp = new int[leftWidth + 1][rightWidth];
            for(int[] line : temp){
                Arrays.fill(line, -1);
            }
            for(int i = 0; i < rightWidth; i++){
                temp[leftWidth][i] = matrix[matrix.length - 1][rightStart + i];
            }

//            for(int[] line : temp){
//                System.out.println(Arrays.toString(line));
//            }

            for(int i = 0; i < leftHeight; i++){
                for(int j = 0; j < leftWidth; j++){
                    int lr = matrix.length - 1 - i;
                    int lc = j;

                    int rr = (temp.length - 1) - (leftWidth - j);
                    int rc = i;

                    temp[rr][rc] = matrix[lr][lc];
                }
            }

            matrix = temp;
        }
    }

    // 물고기 수 조절
    static void balancing(){
        int[][] temp = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++){
            temp[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }

        int[] dy = {1, 0};
        int[] dx = {0, 1};
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] != -1){
                    for(int d = 0; d < 2; d++){
                        int row = i + dy[d];
                        int col = j + dx[d];
                        if(row < 0 || col < 0 || row >= matrix.length || col >= matrix[i].length || matrix[row][col] == -1) continue;
                        int D = Math.abs(matrix[i][j] - matrix[row][col]) / 5;
                        if(matrix[i][j] > matrix[row][col]){
                            temp[i][j] -= D;
                            temp[row][col] += D;
                        }
                        else{
                            temp[i][j] += D;
                            temp[row][col] -= D;
                        }
                    }
                }
            }
        }
        matrix = temp;
    }

    static void flatten(){
        List<Integer> list = new ArrayList<>();
        for(int j = 0; j < matrix[0].length; j++){
            for(int i = matrix.length - 1; i >= 0; i--){
                if(matrix[i][j] != -1) list.add(matrix[i][j]);
            }

        }

        int[][] temp = new int[1][list.size()];
        for(int i = 0; i < list.size(); i++){
            temp[0][i] = list.get(i);
        }
        matrix = temp;
    }
    static void secondRotate(){
        for(int a = 0; a < 2; a++){
            int[][] temp = new int[matrix.length * 2][matrix[0].length / 2];

            int pivot = matrix[0].length / 2;

            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < temp[i].length; j++){
                    temp[temp.length - 1 - i][j] = matrix[matrix.length - 1 - i][pivot + j];
                }
            }

            for(int i = 0; i < temp.length / 2; i++){
                for(int j = 0; j < temp[i].length; j++){
                    temp[i][j] = matrix[matrix.length - 1 - i][pivot - 1 - j];
                }
            }
            matrix = temp;
        }
    }

    static boolean valid(){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] != -1){
                    max = Math.max(matrix[i][j], max);
                    min = Math.min(matrix[i][j], min);
                }
            }
        }
        if(max - min <= k) return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[] array = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        matrix = new int[1][n];
        for(int[] line : matrix){
            Arrays.fill(line, -1);
        }
        matrix[0] = array;

        int result = 0;

        while(true){
            if(valid()) break;

            firstAdd();

            firstRotate();

            balancing();

            flatten();

            secondRotate();

            balancing();

            flatten();

            result++;

//            for(int[] line : matrix){
//                System.out.println(Arrays.toString(line));
//            }
//            System.out.println();

        }
        System.out.println(result);
        
    }
}
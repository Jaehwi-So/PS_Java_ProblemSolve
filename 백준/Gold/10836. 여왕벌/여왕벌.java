import java.io.*;
import java.util.*;

public class Main {
    static int n, k;
    static int[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];
        for(int[] line : matrix){
            Arrays.fill(line, 1);
        }

        StringBuilder sb = new StringBuilder();
        int[] col = new int[n];
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(br.readLine());
            int[] numbers = new int[3];
            numbers[0] = Integer.parseInt(st.nextToken());
            numbers[1] = Integer.parseInt(st.nextToken());
            numbers[2] = Integer.parseInt(st.nextToken());

            int index = 0;
            for(int j = n - 1; j > 0; j--){
                while(numbers[index] == 0) index++;
                matrix[j][0] += index;
                numbers[index]--;

            }
            for(int j = 0; j < n; j++){
                while(numbers[index] == 0) index++;
                col[j] += index;
                matrix[0][j] += index;
                numbers[index]--;
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i > 0 && j > 0) matrix[i][j] += col[j];
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}
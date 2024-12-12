import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] array = new int[m][n];
        int[][] sArray = new int[m][n];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
                sArray[i][j] = array[i][j];
            }
            Arrays.sort(sArray[i]);
        }

        int[][] sequence = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                sequence[i][j] = Arrays.binarySearch(sArray[i], array[i][j]);
            }
        }

        int result = 0;
        for(int i = 0; i < m - 1; i++){
            for(int j = i + 1; j < m; j++){
                boolean isAdd = true;
                for(int k = 0; k < n; k++){
                    if(sequence[i][k] != sequence[j][k]){
                        isAdd = false;
                        break;
                    }
                }
                if(isAdd) result++;

            }
        }
        System.out.println(result);

    }
}
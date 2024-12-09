import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] matrix;

    static boolean match(int[][] sticker){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                boolean success = compare(i, j, sticker);
                for(int k = i; (k < n && k < i + sticker.length); k++){
                    for(int l = j; (l < m && l < j + sticker[0].length); l++){
                        if(matrix[k][l] == 2){
                            if(success) matrix[k][l] = 1;
                            else matrix[k][l] = 0;
                        }
                    }
                }
                if(success){
                    return true;
                }
            }
        }
        return false;
    }

    static boolean compare(int row, int col, int[][] sticker){
        for(int i = 0; i < sticker.length; i++){
            for(int j = 0; j < sticker[i].length; j++){
                int crow = row + i;
                int ccol = col + j;
                if(crow >= n || ccol >= m) return false;
                else if(matrix[crow][ccol] != 0 && sticker[i][j] == 1) return false;
                else if(sticker[i][j] == 1){
                    matrix[crow][ccol] = 2;
                }
            }
        }
        return true;
    }

    static int[][] rotate(int[][] origin){
        int height = origin[0].length;
        int width = origin.length;
        int[][] temp = new int[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                temp[i][j] = origin[width - 1 - j][i];
            }
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];

        for(int t = 0; t < k; t++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[a][b];
            for(int i = 0; i < a; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < b; j++){
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            boolean success = false;
            success = match(sticker);
            for(int i = 1; i < 4; i++){
                if(success == true){
                    break;
                }
                sticker = rotate(sticker);
                success = match(sticker);
            }

        }

        int result = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 1){
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}

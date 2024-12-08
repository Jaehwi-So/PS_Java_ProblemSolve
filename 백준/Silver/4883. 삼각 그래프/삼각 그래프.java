import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 1000000;
    static int[] dy = {-1, -1, -1, 0};
    static int[] dx = {-1, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int index = 1;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            if(n == 0){
                break;
            }
            int[][] matrix = new int[n][3];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 3; j++){
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            matrix[0][0] = MAX;
            matrix[0][2] += matrix[0][1];
            for(int i = 1; i < n; i++){
                for(int j = 0; j < 3; j++){
                    int min = MAX;
                    for(int k = 0; k < 4; k++){
                        int row = i + dy[k];
                        int col = j + dx[k];
                        if(row < 0 || col < 0 || row >= n || col >= 3){
                            continue;
                        }
                        min = Math.min(min, matrix[row][col] + matrix[i][j]);
                    }
                    matrix[i][j] = min;
                }
            }

            sb.append(String.format("%d. %d\n", index, matrix[n-1][1]));
            index++;

        }

        System.out.println(sb);
    }
}
import java.io.*;
import java.util.*;

public class Main {
    static int n = 10;
    static int[][] matrix;
    static int result = Integer.MAX_VALUE;

    static int[][] getPoints(int row, int col, int size){
        if(row + size > n || col + size > n){
            return new int[][]{{-1, -1}, {-1, -1}};
        }
        for(int i = row; i < row + size; i++){
            for(int j = col; j < col + size; j++){
                if(matrix[i][j] == 0){
                    return new int[][]{{-1, -1}, {-1, -1}};
                }
            }
        }
        return new int[][]{{row, col}, {row+size-1, col+size-1}};
    }

    static void dfs(int index, int count, int[] paper){
        if(index / n == 10){
            result = Math.min(result, count);
        }
        else{
            int row = index / n;
            int col = index % n;
            if(matrix[row][col] == 0){
                dfs(index + 1, count, Arrays.copyOf(paper, paper.length));
            }
            else{
                for(int s = 1; s <= 5; s++){
                    if(paper[s] <= 0) continue;
                    int[][] points = getPoints(row, col, s);
                    if(points[0][0] == -1) continue;
                    for(int i = points[0][0]; i <= points[1][0]; i++){
                        for(int j = points[0][1]; j <= points[1][1]; j++){
                            matrix[i][j] = 0;
                        }
                    }
                    int[] next = Arrays.copyOf(paper, paper.length);
                    next[s]--;
                    dfs(index + 1, count + 1, next);
                    for(int i = points[0][0]; i <= points[1][0]; i++){
                        for(int j = points[0][1]; j <= points[1][1]; j++){
                            matrix[i][j] = 1;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        matrix = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, new int[]{0, 5, 5, 5, 5, 5});
        if(result == Integer.MAX_VALUE) result = -1;
        System.out.println(result);
    }
}
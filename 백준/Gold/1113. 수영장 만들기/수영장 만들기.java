import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int result;
    static int[][] matrix;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};

    static void bfs(int srow, int scol, int number){
        int count = 0;
        boolean available = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{srow, scol});
        matrix[srow][scol] = number + 1;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            count++;

            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m){
                    available = false;
                    continue;
                }
                if(matrix[row][col] == number){
                    matrix[row][col] = number + 1;
                    queue.offer(new int[]{row, col});
                }
            }
        }

        if(available) result += count;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            char[] chs = st.nextToken().toCharArray();
            for(int j = 0; j < m; j++){
                matrix[i][j] = Character.getNumericValue(chs[j]);
            }
        }

        for(int number = 1; number <= 8; number++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(matrix[i][j] == number){
                        bfs(i, j, number);
                    }
                }
            }
        }

        System.out.println(result);


    }
}
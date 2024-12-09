import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited;
    static int n;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static void bfs(int sRow, int sCol, char[][] matrix){
        Queue<int[]> queue = new LinkedList<>();
        visited[sRow][sCol] = true;
        queue.offer(new int[]{sRow, sCol});
        char ch = matrix[sRow][sCol];


        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            for(int i = 0; i < 4; i++){
                int nrow = row + dy[i];
                int ncol = col + dx[i];
                if(nrow < 0 || nrow >= n || ncol < 0 || ncol >= n){
                    continue;
                }
                if(matrix[nrow][ncol] == ch && !visited[nrow][ncol]){
                    visited[nrow][ncol] = true;
                    queue.offer(new int[]{nrow, ncol});
                }
            }
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        char[][] matrix = new char[n][n];
        char[][] cMatrix = new char[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = st.nextToken().toCharArray();
            cMatrix[i] = Arrays.copyOf(matrix[i], n);
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 'G'){
                    cMatrix[i][j] = 'R';
                }
            }
        }

        int aResult = 0;
        int bResult = 0;

        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    bfs(i, j, matrix);
                    aResult++;
                }
            }
        }

        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!visited[i][j]){
                    bfs(i, j, cMatrix);
                    bResult++;
                }
            }
        }

        System.out.println(aResult + " " + bResult);

    }
}

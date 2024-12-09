import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] matrix;
    static boolean[][] visited;
    static int n;
    static int m;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int bfs(int jRow, int jCol){
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 'F')
                queue.offer(new int[]{0, i, j, 0});
            }
        }

        visited[jRow][jCol] = true;
        queue.offer(new int[]{1, jRow, jCol, 0});


        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int row = current[1];
            int col = current[2];
            for(int i = 0; i < 4; i++){
                int nrow = row + dy[i];
                int ncol = col + dx[i];
                if(nrow < 0 || nrow >= n || ncol < 0 || ncol >= m){
                    if(current[0] == 1){
                        return current[3] + 1;
                    }
                    else{
                        continue;
                    }
                }
                if(current[0] == 0){
                    if(matrix[nrow][ncol] == '.'){
                        matrix[nrow][ncol] = 'F';
                        queue.offer(new int[]{0, nrow, ncol, 0});
                    }
                }
                else{
                    if(matrix[nrow][ncol] == '.' && !visited[nrow][ncol]){
                        visited[nrow][ncol] = true;
                        queue.offer(new int[]{1, nrow, ncol, current[3] + 1});
                    }
                }
            }
        }

        return -1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        visited = new boolean[n][m];

        int jRow = 0;
        int jCol = 0;
        int fRow = 0;
        int fCol = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = st.nextToken().toCharArray();
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 'J'){
                    jRow = i;
                    jCol = j;
                    matrix[i][j] = '.';
                }
            }
        }

        int result = bfs(jRow, jCol);
        if(result == -1){
            System.out.println("IMPOSSIBLE");
        }
        else{
            System.out.println(result);
        }
    }
}
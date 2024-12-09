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
    static int bfs(int srow, int scol){
        Queue<int[]> queue = new LinkedList<>();
        visited[srow][scol] = true;
        queue.offer(new int[]{srow, scol});
        int count = 0;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            count++;
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m){
                    continue;
                }
                if(matrix[row][col] == '0' || visited[row][col]){
                    continue;
                }
                visited[row][col] = true;
                queue.offer(new int[]{row, col});
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                matrix[i][j] = st.nextToken().charAt(0);
            }
        }

        int cnt = 0;
        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visited[i][j] && matrix[i][j] == '1'){
                    max = Math.max(bfs(i, j), max);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max);

    }
}
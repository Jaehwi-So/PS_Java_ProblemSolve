import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int row;
    int col;
    public Point(int row, int col){
        this.row = row;
        this.col = col;
    }
}
public class Main {
    static int index = 1;
    static int[][] matrix;
    static int[][] visited;
    static int n;
    static int m;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static boolean isAvailable(int row, int col){
        if(row >= 0 && row < n && col >= 0 && col < m && matrix[row][col] != -1 && visited[row][col] == 0){
            return true;
        }
        return false;
    }

    static void bfs(int sRow, int sCol){
        Queue<Point> queue = new LinkedList<>();
        Queue<Point> result = new LinkedList<>();
        int count = 0;
        visited[sRow][sCol] = index;
        queue.offer(new Point(sRow, sCol));
        while(!queue.isEmpty()){
            Point current = queue.poll();
            int row = current.row;
            int col = current.col;
            result.offer(current);
            count++;

            for(int i = 0; i < 4; i++){
                int nextRow = row + dy[i];
                int nextCol = col + dx[i];
                if(isAvailable(nextRow, nextCol)){
                    visited[nextRow][nextCol] = index;
                    queue.offer(new Point(nextRow, nextCol));
                }
            }
        }

        while(!result.isEmpty()){
            Point p = result.poll();
            matrix[p.row][p.col] = count;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        visited = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            char[] chs = st.nextToken().toCharArray();
            for(int j = 0; j < m; j++){
                matrix[i][j] = Character.getNumericValue(chs[j]);
                if(matrix[i][j] == 1){
                    matrix[i][j] = -1;
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 0 && visited[i][j] == 0){
                    bfs(i, j);
                    index++;
                }
            }
        }

        int[][] result = new int[n][m];
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == -1){
                   result[i][j] = 1;
                   set.clear();
                   for(int k = 0; k < 4; k++){
                       int row = i + dy[k];
                       int col = j + dx[k];
                       if(row >= 0 && row < n && col >= 0 && col < m && visited[row][col] != 0 && !set.contains(visited[row][col])){
                           result[i][j] += (matrix[row][col] % 10);
                           set.add(visited[row][col]);
                       }
                   }
                    
                   result[i][j] %= 10; 
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                sb.append(result[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
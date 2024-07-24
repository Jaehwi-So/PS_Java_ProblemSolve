import java.io.*;
import java.util.*;

class Point{
    int row;
    int col;
    int time;
    public Point(int row, int col, int time){
        this.row = row;
        this.col = col;
        this.time = time;
    }
}
public class Main {
    static int n;
    static int m;
    static char[][] matrix;
    static boolean[][] visited;
    static int[][] water;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static String result = "KAKTUS";
    static final int MAX = 100000;
    static boolean isAvailable(int row, int col){
        if(row >= 0 && row < n && col >= 0 && col < m && (matrix[row][col] == '.' || matrix[row][col] == 'D')){
            return true;
        }
        return false;
    }

    static void bfs(Point start, Point end){
        Queue<Point> queue = new LinkedList();
        visited[start.row][start.col] = true;
        queue.offer(start);

        while(!queue.isEmpty()){
            Point current = queue.poll();
            int row = current.row;
            int col = current.col;
            if(row == end.row && col == end.col){
                result = Integer.toString(current.time);
                return;
            }

            for(int i = 0; i < 4; i++){
                int nrow = row + dy[i];
                int ncol = col + dx[i];
                if(isAvailable(nrow, ncol) && !visited[nrow][ncol] && water[nrow][ncol] > current.time + 1){
                    visited[nrow][ncol] = true;
                    queue.offer(new Point(nrow, ncol, current.time + 1));
                }
            }
        }
    }

    static void waterBFS(Point start){
        Queue<Point> queue = new LinkedList();
        water[start.row][start.col] = 0;
        queue.offer(start);

        while(!queue.isEmpty()){
            Point current = queue.poll();
            int row = current.row;
            int col = current.col;

            for(int i = 0; i < 4; i++){
                int nrow = row + dy[i];
                int ncol = col + dx[i];
                if(isAvailable(nrow, ncol) && matrix[nrow][ncol] != 'D'){
                    if(water[nrow][ncol] > current.time + 1){
                        water[nrow][ncol] = current.time + 1;
                        queue.offer(new Point(nrow, ncol, current.time + 1));
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        visited = new boolean[n][m];
        water = new int[n][m];
        for(int[] line : water){
            Arrays.fill(line, MAX);
        }

        Point start = new Point(0, 0, 0);
        Point end = new Point(0, 0, 0);
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = st.nextToken().toCharArray();
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 'S'){
                    matrix[i][j] = '.';
                    start = new Point(i, j, 0);
                }
                else if(matrix[i][j] == 'D'){
                    end = new Point(i, j, 0);
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == '*'){
                    waterBFS(new Point(i, j, 0));
                }
            }
        }
        
        bfs(start, end);

        System.out.println(result);

    }
}
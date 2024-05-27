import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int row;
    int col;
    int height;
    public Point(int height, int row, int col){
        this.height = height;
        this.row = row;
        this.col = col;
    }
}
public class Main {
    static int[][][] array;
    static int m;
    static int n;
    static int h;
    static void bfs(List<Point> starts){
        Queue<Point> queue = new LinkedList<>();
        for(Point p : starts){
            queue.offer(p);
        }

        while(!queue.isEmpty()){
            Point p = queue.poll();
            int height = p.height;
            int row = p.row;
            int col = p.col;
            if(row+1 < n && array[height][row+1][col] == 0){
                queue.offer(new Point(height, row+1, col));
                array[height][row+1][col] = array[height][row][col] + 1;
            }
            if(col+1 < m && array[height][row][col+1] == 0){
                queue.offer(new Point(height, row, col+1));
                array[height][row][col+1] = array[height][row][col] + 1;
            }
            if(row-1 >= 0 && array[height][row-1][col] == 0){
                queue.offer(new Point(height, row-1, col));
                array[height][row-1][col] = array[height][row][col] + 1;
            }
            if(col-1 >= 0 && array[height][row][col-1] == 0){
                queue.offer(new Point(height, row, col-1));
                array[height][row][col-1] = array[height][row][col] + 1;
            }
            if(height+1 < h && array[height+1][row][col] == 0){
                queue.offer(new Point(height+1, row, col));
                array[height+1][row][col] = array[height][row][col] + 1;
            }
            if(height-1 >= 0 && array[height-1][row][col] == 0){
                queue.offer(new Point(height-1, row, col));
                array[height-1][row][col] = array[height][row][col] + 1;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        array = new int[h][n][m];


        List<Point> points = new ArrayList<>();
        for(int l = 0; l < h; l++){
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < m; j++){
                    array[l][i][j] = Integer.parseInt(st.nextToken());
                    if(array[l][i][j] == 1){
                        points.add(new Point(l, i, j));
                    }
                }
            }
        }


        bfs(points);
        int result = Integer.MIN_VALUE;
        for(int[][] box : array){
            for(int[] line : box){
                for(int i : line){
                    if(i == 0){
                        System.out.println(-1);
                        return;
                    }
                    result = Math.max(result, i - 1);
                }
            }
        }

        System.out.println(result);

    }
}
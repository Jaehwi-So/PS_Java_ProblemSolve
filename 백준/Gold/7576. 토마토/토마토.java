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
    static int[][] array;
    static int m;
    static int n;
    static void bfs(List<Point> starts){
        Queue<Point> queue = new LinkedList<>();
        for(Point p : starts){
            queue.offer(p);
        }

        while(!queue.isEmpty()){
            Point p = queue.poll();
            int row = p.row;
            int col = p.col;
            if(row+1 < n && array[row+1][col] == 0){
                queue.offer(new Point(row+1, col));
                array[row+1][col] = array[row][col] + 1;
            }
            if(col+1 < m && array[row][col+1] == 0){
                queue.offer(new Point(row, col+1));
                array[row][col+1] = array[row][col] + 1;
            }
            if(row-1 >= 0 && array[row-1][col] == 0){
                queue.offer(new Point(row-1, col));
                array[row-1][col] = array[row][col] + 1;
            }
            if(col-1 >= 0 && array[row][col-1] == 0){
                queue.offer(new Point(row, col-1));
                array[row][col-1] = array[row][col] + 1;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        array = new int[n][m];


        List<Point> points = new ArrayList<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
                if(array[i][j] == 1){
                    points.add(new Point(i, j));
                }
            }
        }

        bfs(points);
        int result = Integer.MIN_VALUE;
        for(int[] line : array){
            for(int i : line){
                if(i == 0){
                    System.out.println(-1);
                    return;
                }
                result = Math.max(result, i - 1);
            }
        }
        System.out.println(result);

    }
}
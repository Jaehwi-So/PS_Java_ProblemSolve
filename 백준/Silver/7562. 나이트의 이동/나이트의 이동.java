import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

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
    static int n;
    static int bfs(Point start, Point end){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);
        array[start.row][start.col] = 1;
        while(!queue.isEmpty()){
            Point k = queue.poll();
            int row = k.row;
            int col = k.col;
            if(row == end.row && col == end.col){
                return array[row][col] - 1;
            }
            for(int i = 1; i <= 2; i++){
                for(int j = 1; j <= 2; j++){
                    if(i == j){
                        continue;
                    }
                    if(row+i < n && col+j < n && array[row+i][col+j] == 0){
                        queue.offer(new Point(row+i, col+j));
                        array[row+i][col+j] = array[row][col] + 1;
                    }
                    if(row+i < n && col-j >= 0 && array[row+i][col-j] == 0){
                        queue.offer(new Point(row+i, col-j));
                        array[row+i][col-j] = array[row][col] + 1;
                    }
                    if(row-i >= 0 && col-j >= 0 && array[row-i][col-j] == 0){
                        queue.offer(new Point(row-i, col-j));
                        array[row-i][col-j] = array[row][col] + 1;
                    }
                    if(row-i >= 0 && col+j < n && array[row-i][col+j] == 0){
                        queue.offer(new Point(row-i, col+j));
                        array[row-i][col+j] = array[row][col] + 1;
                    }
                }
            }

        }
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(st.nextToken());
        for(int l = 0; l < t; l++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            array = new int[n][n];

            st = new StringTokenizer(br.readLine());
            Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine());
            Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sb.append(bfs(start, end)).append("\n");

        }
        System.out.println(sb);

    }
}

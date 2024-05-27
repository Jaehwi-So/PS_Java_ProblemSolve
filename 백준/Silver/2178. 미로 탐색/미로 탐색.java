import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point{
    int row;
    int col;
    int count;
    public Point(int row, int col, int count){
        this.row = row;
        this.col = col;
        this.count = count;
    }
}
public class Main {
    static int n;
    static int m;
    static short[][] array;
    static void visit(int row, int col){
        array[row][col] = 2;
    }
    static int bfs(Point s){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(s);
        int result = 0;
        while(!queue.isEmpty()){
            Point p = queue.poll();
            if(array[p.row][p.col] == 1){
                visit(p.row, p.col);
                if(p.row == n && p.col == m){
                    result = p.count;
                    break;
                }
                queue.offer(new Point(p.row - 1, p.col, p.count + 1));
                queue.offer(new Point(p.row, p.col + 1, p.count + 1));
                queue.offer(new Point(p.row + 1, p.col, p.count + 1));
                queue.offer(new Point(p.row, p.col - 1, p.count + 1));
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new short[n+2][m+2];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            char[] chs = st.nextToken().toCharArray();
            for(int j = 1; j <= m; j++){
                array[i][j] = (short) (chs[j-1] - '0');
            }
        }

//        StringBuilder sb = new StringBuilder();
        System.out.println(bfs(new Point(1, 1, 1)));
    }
}

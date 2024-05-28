import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
    int row;
    int col;
    boolean isDestroy;
    int count;
    public Point(int row, int col, boolean isDestroy, int count){
        this.row = row;
        this.col = col;
        this.isDestroy = isDestroy;
        this.count = count;
    }
}
public class Main {
    static int n;
    static int m;
    static int[][] array;

    static int bfs(Point s){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(s);

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        boolean[][][] visited = new boolean[2][n][m];
        visited[0][s.row][s.col] = true;

        while(!queue.isEmpty()){
            Point p = queue.poll();

            if(p.row == n-1 && p.col == m-1){
                return p.count;
            }

            for(int i = 0; i < 4; i++){
                int row = p.row + dy[i];
                int col = p.col + dx[i];

                if(row < 0 || row >= n || col < 0 || col >= m){
                    continue;
                }
                
                // 벽이 아닌 경우
                if(array[row][col] == 0){
                    // 이미 벽을 부순 경우
                    if(p.isDestroy && !visited[1][row][col]){
                        queue.offer(new Point(row, col, true, p.count + 1));
                        visited[1][row][col] = true;
                    }
                    // 벽을 부수지 않은 경우
                    else if(!p.isDestroy && !visited[0][row][col]){
                        queue.offer(new Point(row, col, false, p.count + 1));
                        visited[0][row][col] = true;
                    }
                }
                // 벽인 경우
                else if(array[row][col] == -1){
                    // 부순 적이 없으면 벽을 부순다
                    if(!p.isDestroy && !visited[0][row][col]){
                        queue.offer(new Point(row, col, true, p.count + 1));
                        visited[1][row][col] = true;
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
        array = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            char[] chs = st.nextToken().toCharArray();
            for(int j = 0; j < m; j++){
                if (chs[j] == '0') {
                    array[i][j] = 0;
                }
                else{
                    array[i][j] = -1;
                }
            }
        }

        int[][] temp = new int[n][m];
        for(int i = 0; i < n; i++){
            temp[i] = Arrays.copyOf(array[i], m);
        }

        int res = bfs(new Point(0, 0, false, 1));
        System.out.println(res);


//        for(int[] line : array){
//            System.out.println(Arrays.toString(line));
//        }
    }
}
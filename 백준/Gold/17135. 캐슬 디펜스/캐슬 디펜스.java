import java.io.*;
import java.util.*;

class Point {
    int row;
    int col;
    int distance;
    public Point(int row, int col, int distance){
        this.row = row;
        this.col = col;
        this.distance = distance;
    }

}
public class Main {
    static int n;
    static int m;
    static int range;
    static int[][] matrix;
    static int[] dx = {0, -1, 1, 0};
    static int[] dy = {1, 0, 0, -1};
    static int result = 0;
    static void moveMatrix(){
        for(int i = n - 1; i > 0; i--){
            matrix[i] = Arrays.copyOf(matrix[i-1], matrix[i].length);
        }
        Arrays.fill(matrix[0], 0);
    }
    static boolean isAvailable(int row, int col){
        if(row >= 0 && row < n && col >= 0 && col < m){
            return true;
        }
        return false;
    }
    static void run(Point[] archers){
        int count = 0;
        for(int l = 0; l < n; l++){
            List<Point> removePoints = new ArrayList<>();
            for(int k = 0; k < 3; k++){
                Queue<Point> queue = new LinkedList<>();
                boolean[][] visited = new boolean[n+1][m];
                queue.offer(archers[k]);
                visited[archers[k].row][archers[k].col] = true;
                List<Point> candidate = new ArrayList<>();
                int maxDist = range;
                while(!queue.isEmpty()){
                    Point current = queue.poll();
                    int row = current.row;
                    int col = current.col;
                    if(current.distance > maxDist){
                        continue;
                    }
                    if(matrix[row][col] == 1){
                        maxDist = current.distance;
                        candidate.add(current);
                    }
                    for(int i = 0; i < 4; i++){
                        int nrow = row + dy[i];
                        int ncol = col + dx[i];
                        if(isAvailable(nrow, ncol) && !visited[nrow][ncol]){
                            queue.offer(new Point(nrow, ncol, current.distance + 1));
                        }
                    }
                }

                if(candidate.size() > 0){
                    Collections.sort(candidate, new Comparator<Point>(){
                        public int compare(Point p1, Point p2){
                            return p1.col - p2.col;
                        }
                    });

                    Point target = candidate.get(0);
                    removePoints.add(target);
                }

            }
            for(Point p : removePoints){
                if(matrix[p.row][p.col] == 1){
                    count++;
                    matrix[p.row][p.col] = 0;
                }
            }

//            for(int[] line : matrix){
//                System.out.println(Arrays.toString(line));
//            }
//            System.out.println();
            moveMatrix();
        }
        result = Math.max(result, count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        range = Integer.parseInt(st.nextToken());
        matrix = new int[n+1][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] temp = new int[n+1][m];
        for(int l = 0; l <= n; l++){
            temp[l] = Arrays.copyOf(matrix[l], matrix[l].length);
        }
        
        for(int i = 0; i < m - 2; i++){
            for(int j = i + 1; j < m - 1; j++){
                for(int k = j + 1; k < m; k++){
                    for(int l = 0; l <= n; l++){
                        matrix[l] = Arrays.copyOf(temp[l], matrix[l].length);
                    }
                    Point[] archers = {new Point(n, i, 0), new Point(n, j, 0), new Point(n, k, 0)};
                    run(archers);
                }
            }
        }

        System.out.println(result);

    }
}
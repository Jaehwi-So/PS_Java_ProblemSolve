import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int k;
    static int w;
    static int[][] matrix;


    // 오른쪽, 왼쪽, 위쪽, 아래쪽
    static int[] dy = {0, 0, 0, -1, 1};     // {0, 0, 0, -1, 1};
    static int[] dx = {0, 1, -1, 0, 0};     // {0, 1, -1, 0, 0};
    static int[][] directs = {{0, 0, 0}, {0, 4, 3}, {0, 4, 3}, {0, 1, 2}, {0, 1, 2}};

    //walls[i][j][1] : 오른쪽에 벽 존재
    static boolean[][][] walls;


    static void windFlow(int[] start){
        Queue<int[]> queue = new LinkedList<>();
        int[][] temp = new int[n][m];
        int d = start[2];
        int srow = start[0] + dy[d];
        int scol = start[1] + dx[d];
        queue.offer(new int[]{srow, scol, 5});
        temp[srow][scol] = 5;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(current[2] == 0) continue;
            for(int i = 0; i < 3; i++){
                int dir = directs[d][i];
                if(walls[current[0]][current[1]][dir]) continue;
                int row = current[0] + dy[dir];
                int col = current[1] + dx[dir];
                if(row < 0 || col < 0 || row >= n || col >= m) continue;

                int nrow = row + dy[d];
                int ncol = col + dx[d];
                if(nrow < 0 || ncol < 0 || nrow >= n || ncol >= m) continue;
                if(walls[row][col][d]) continue;
                temp[nrow][ncol] = current[2] - 1;
                queue.offer(new int[]{nrow, ncol, current[2] - 1});
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                matrix[i][j] += temp[i][j];
            }
        }
    }

    static void distribute(){
        int[][] temp = new int[n][m];
        int[] idx = {1, 4}; //오른쪽, 아래쪽
        int[] cdx = {0, 1, 0, 0, 0};
        int[] cdy = {0, 0, 0, 0, 1};

        for(int row = 0; row < n; row++){
            for(int col = 0; col < m; col++){
                for(int i = 0; i < 2; i++){
                    int d = idx[i];
                    int nrow = row + cdy[d];
                    int ncol = col + cdx[d];
                    if(nrow < 0 || ncol < 0 || nrow >= n || ncol >= m) continue;
                    if(walls[row][col][d]) continue;
                    if(matrix[row][col] == matrix[nrow][ncol]) continue;

                    int num = (int)Math.floor(Math.abs((matrix[nrow][ncol] - matrix[row][col])) / 4.0);
                    if(matrix[row][col] < matrix[nrow][ncol]){
                        temp[row][col] += num;
                        temp[nrow][ncol] -= num;
                    }
                    else{
                        temp[row][col] -= num;
                        temp[nrow][ncol] += num;
                    }
                }


            }
        }

        for(int row = 0; row < n; row++){
            for(int col = 0; col < m; col++){
                matrix[row][col] += temp[row][col];
            }
        }

    }

    static void decreaseTemperature(){
        for(int i = 0; i < n; i++){
            matrix[i][0] = Math.max(matrix[i][0] - 1, 0);
            matrix[i][m-1] = Math.max(matrix[i][m-1] - 1, 0);
        }
        for(int i = 1; i < m - 1; i++){
            matrix[0][i] = Math.max(matrix[0][i] - 1, 0);
            matrix[n-1][i] = Math.max(matrix[n-1][i] - 1, 0);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        walls = new boolean[n][m][5];

        List<int[]> points = new ArrayList<>();
        List<int[]> tests = new ArrayList<>();
        for(int i = 0 ; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                int k = Integer.parseInt(st.nextToken());
                if(k == 5) tests.add(new int[]{i, j});
                else if(k != 0) points.add(new int[]{i, j, k});
            }
        }
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        for(int i = 0; i < w; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());
            if(t == 0){ //row, row-1
                walls[r][c][3] = true; //위쪽벽
                if(r-1 >= 0) walls[r-1][c][4] = true; //아래쪽벽
            }
            else{ //col, col+1
                walls[r][c][1] = true; //오른쪽벽
                if(c+1 < m) walls[r][c+1][2] = true; //왼쪽벽
            }
        }

        int chocolate = 0;
        while(true){
            // 1. 온풍기 바람
            for(int[] p : points){
                windFlow(p);
            }

            // 2. 온도 분배
            distribute();

            // 3. 바깥쪽 칸의 온도 1씩 감소
            decreaseTemperature();

            //4. 초콜릿 먹기
            chocolate++;
            if(chocolate > 100) break;

            // 5. 확인점 확인
            boolean success = true;
            for(int[] p : tests){
                if(matrix[p[0]][p[1]] < k){
                    success = false;
                    break;
                }
            }

            if(success) break;
        }
        System.out.println(chocolate);
    }
}
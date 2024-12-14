import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[101][101];
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};

    static int[] make(int[][] matrix, int srow, int scol, int orow, int ocol){
        boolean[][] visited = new boolean[101][101];
        visited[srow][scol] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{srow, scol, srow, scol});
        Queue<int[]> newP = new LinkedList<>();
        int[] point = new int[2];

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row > 100 || col > 100){
                    continue;
                }
                if(matrix[row][col] == 1 && !visited[row][col]){
                    visited[row][col] = true;
                    int direct = (i - 1);
                    if(direct == -1) direct = 3;
                    int trow = current[2] + dy[direct];
                    int tcol = current[3] + dx[direct];
                    if(trow < 0 || tcol < 0 || trow > 100 || tcol > 100){

                    }
                    else{
                        if(matrix[trow][tcol] == 0){
                            newP.offer(new int[]{trow, tcol});
                        }
                        if(row == orow && col == ocol){
                            point[0] = trow;
                            point[1] = tcol;
                        }
                    }

                    queue.offer(new int[]{row, col, trow, tcol});
                }
            }
        }
        while(!newP.isEmpty()){
            int[] p = newP.poll();
            matrix[p[0]][p[1]] = 1;
        }
        return point;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            int[][] temp = new int[101][101];
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            temp[y][x] = 1;
            temp[y+dy[d]][x+dx[d]] = 1;

            int[] point = new int[]{y+dy[d], x+dx[d]};
            for(int j = 0; j < g; j++){
                point = make(temp, point[0], point[1], y, x);
            }

            for(int l = 0; l <= 100; l++){
                for(int j = 0; j <= 100; j++){
                    if(temp[l][j] == 1) board[l][j] = 1;
                }
            }
        }
        int count = 0;
        for(int i = 0; i <= 99; i++){
            for(int j = 0; j <= 99; j++){
                if(board[i][j] == 1 &&
                        board[i+1][j] == 1 &&
                        board[i][j+1] == 1 &&
                        board[i+1][j+1] == 1){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
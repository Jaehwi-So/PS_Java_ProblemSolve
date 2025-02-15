import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int g;
    static int r;
    static int[][] matrix;  //3 : G가 뿌려진곳, 4 : R가 뿌려진곳
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int result = 0;

    static int bfs(){
        Queue<int[]> queue = new LinkedList<>();
        int[][][] visited = new int[n][m][2];
        for(int[][] mat : visited){
            for(int[] line : mat){
                Arrays.fill(line, -1);
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] >= 3){
                    queue.offer(new int[]{i, j, matrix[i][j] - 3, 0});
                    visited[i][j][matrix[i][j] - 3] = 0;
                }
            }
        }

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int type = current[2];
            int time = current[3];
            if(visited[row][col][0] == visited[row][col][1] && visited[row][col][0] > -1){
                continue;
            }
            for(int i = 0; i < 4; i++){
                int nrow = row + dy[i];
                int ncol = col + dx[i];
                if(nrow < 0 || ncol < 0 || nrow >= n || ncol >= m) continue;
                if(matrix[nrow][ncol] == 0) continue;
                int ntype = type == 0 ? 1 : 0;
                if(visited[nrow][ncol][type] == -1 && (visited[nrow][ncol][ntype] == -1 || visited[nrow][ncol][ntype] == time + 1)){
                    visited[nrow][ncol][type] = time + 1;
                    queue.offer(new int[]{nrow, ncol, type, time+1});
                }
            }
        }

        int result = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j][0] == visited[i][j][1] && visited[i][j][0] >= 0){
                    result++;
                }

            }
        }
        return result;
    }
    static void dfs(int index, int green, int red){

        if(green == g && red == r){
            result = Math.max(result, bfs());
//            for(int[] line : matrix){
//                System.out.println(Arrays.toString(line));
//            }
        }
        else if(index >= n*m) return;
        else{
            for(int i = index; i < n*m; i++){
                int row = i / m;
                int col = i % m;
                if(matrix[row][col] == 2){
                    if(green < g){
                        matrix[row][col] = 3;
                        dfs(i + 1, green + 1, red);
                        matrix[row][col] = 2;
                    }
                    if(red < r){
                        matrix[row][col] = 4;
                        dfs(i + 1, green, red + 1);
                        matrix[row][col] = 2;
                    }
                    dfs(i + 1, green, red);
                    break;
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);
        System.out.println(result);


    }
}
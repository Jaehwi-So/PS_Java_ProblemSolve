import java.io.*;
import java.util.*;

public class Main {
    static int k;
    static int n;
    static int m;
    static int[][] matrix;
    static int[][][] visited;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int[] sdy = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] sdx = {-2, -1, 1, 2, -2, -1, 1, 2};

    static int bfs(){
        visited[0][0][0] = k;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0, 0});

        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int currentMove = current[2];
            int currentJump = current[3];
            if(current[0] == n-1 && current[1] == m-1){
                min = Math.min(min, current[2]);
            }
            else{
                for(int i = 0; i < 4; i++){
                    int row = current[0] + dy[i];
                    int col = current[1] + dx[i];
                    if(row < 0 || col < 0 || row >= n || col >= m){
                        continue;
                    }
                    if(matrix[row][col] != 1 &&
                            visited[row][col][currentJump] > currentMove + 1){
                        visited[row][col][currentJump] = currentMove + 1;
                        queue.offer(new int[]{row, col, currentMove + 1, currentJump});
                    }
                }

                if(currentJump < k){
                    for(int i = 0; i < 8; i++){
                        int row = current[0] + sdy[i];
                        int col = current[1] + sdx[i];
                        if(row < 0 || col < 0 || row >= n || col >= m){
                            continue;
                        }
                        if(matrix[row][col] != 1 &&
                                visited[row][col][currentJump + 1] > currentMove + 1){
                            visited[row][col][currentJump + 1] = currentMove + 1;
                            queue.offer(new int[]{row, col, currentMove + 1, currentJump + 1});
                        }
                    }
                }
            }
        }

        if(min == Integer.MAX_VALUE){
            return -1;
        }
        return min;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];
        visited = new int[n][m][k+1];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int[][] mat : visited){
            for(int[] line : mat){
                Arrays.fill(line, Integer.MAX_VALUE);
            }
        }

        System.out.println(bfs());

    }
}
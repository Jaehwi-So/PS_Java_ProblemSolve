import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] matrix;
    static boolean[][] globVisited;
    static Queue<int[]> mQueue = new LinkedList<>();
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};

    static int bfs(int srow, int scol, int color){

        int rainbowCnt = 0;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        mQueue.clear();
        visited[srow][scol] = true;
        queue.offer(new int[]{srow, scol});

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            mQueue.offer(current);
            if(matrix[current[0]][current[1]] != 1){
                globVisited[current[0]][current[1]] = true;
            }
            else{
                rainbowCnt++;
            }
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= n) continue;
                if(matrix[row][col] != color && matrix[row][col] != 1) continue;
                if(!visited[row][col]){
                    queue.offer(new int[]{row, col});
                    visited[row][col] = true;
                }
            }
        }

        return rainbowCnt;
    }


    static void gravity(){
        for(int j = 0; j < n; j++){
            for(int i = n - 2; i >= 0; i--){
                if(matrix[i][j] == 0 || matrix[i][j] == -1) continue;
                int p = -1;
                for(int k = i + 1; k < n; k++){
                    if(matrix[k][j] != 0) break;
                    p = k;
                }
                if(p != -1){
                    matrix[p][j] = matrix[i][j];
                    matrix[i][j] = 0;
                }
            }
        }
    }
    static void convert(){
        int[][] next = new int[n][n];
        for(int j = n - 1; j >= 0; j--){
            for(int i = 0; i < n; i++){
                next[n-1-j][i] = matrix[i][j];
            }
        }
        matrix = next;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int k = Integer.parseInt(st.nextToken());
                matrix[i][j] = k == -1 ? -1 : k+1;
            }
        }

        int score = 0;

        while(true){
            globVisited = new boolean[n][n];
            int max = -1;
            int rainbow = 0;
            Queue<int[]> temp = new LinkedList<>();
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(matrix[i][j] > 1 && !globVisited[i][j]){
                        int rainbowCnt = bfs(i, j, matrix[i][j]);
                        if(mQueue.size() > 1){
                            if(max < mQueue.size() || (max == mQueue.size() && rainbowCnt >= rainbow)){
                                temp.clear();
                                max = mQueue.size();
                                rainbow = rainbowCnt;
                                while(!mQueue.isEmpty()){
                                    temp.offer(mQueue.poll());
                                }
                            }

                        }
                    }
                }
            }

            if(max == -1){
                break;
            }

            score += (int)Math.pow(max, 2);
            while(!temp.isEmpty()){
                int[] current = temp.poll();
                matrix[current[0]][current[1]] = 0;
            }
            gravity();
            convert();
            gravity();
        }

        // 13 -> 9 4
        System.out.println(score);

    }
}
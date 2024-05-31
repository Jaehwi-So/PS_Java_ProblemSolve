import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] array;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int wallCount = 3;
    static int MAX_VALUE = Integer.MIN_VALUE;


    static int bfs(int row, int col){
        int count = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        visited[row][col] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            count++;
            for(int i = 0; i < 4; i++){
                int r = current[0] + dy[i];
                int c = current[1] + dx[i];
                if(r >= 0 && r < n && c >= 0 && c < m){
                    if(array[r][c] == 0 && visited[r][c] == false){
                        int[] next = new int[]{r, c};
                        queue.offer(next);
                        visited[r][c] = true;
                    }
                }
            }
        }

        return count;
    }

    static int calc(){
        for(boolean[] line : visited){
            Arrays.fill(line, false);
        }

        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(array[i][j] == 2 && visited[i][j] == false){
                    cnt += bfs(i, j);
                }
            }
        }
        return (n * m) - cnt - wallCount;
    }

    static void backtrack(int start, int step){
        if(step == 3){
            int result = calc();
            if(MAX_VALUE < result){
                MAX_VALUE = result;
            }
        }
        else{
            int row = start / m;
            int col = start % m;

            if(row >= n){
                return;
            }

            for(int j = col; j < m; j++){
                if(array[row][j] == 0){
                    array[row][j] = 1;
                    int next = (row * m) + j;
                    backtrack(next + 1, step + 1);
                    array[row][j] = 0;
                }
            }

            for(int i = row + 1; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(array[i][j] == 0){
                        array[i][j] = 1;
                        int next = (i * m) + j;
                        backtrack(next + 1, step + 1);
                        array[i][j] = 0;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
                if(array[i][j] == 1){
                    wallCount++;
                }
            }
        }




        visited = new boolean[n][m];

        backtrack(0, 0);
        System.out.println(MAX_VALUE);



    }
}

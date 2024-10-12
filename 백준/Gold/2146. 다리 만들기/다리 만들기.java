import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int min = Integer.MAX_VALUE;
    static int idx = 1;
    static int[][] matrix;
    static boolean[][] onSearch;
    static int[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static void bfs(int srow, int scol){
        Queue<int[]> queue = new LinkedList<>();
        int index = matrix[srow][scol];
        queue.offer(new int[]{srow, scol, 0});
        visited[srow][scol] = 0;
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(matrix[current[0]][current[1]] != 0 && matrix[current[0]][current[1]] != index){
                min = Math.min(min, current[2]);
            }
            else if(current[2] > min){
                continue;
            }
            else{
                for(int i = 0; i < 4; i++){
                    int row = current[0] + dy[i];
                    int col = current[1] + dx[i];
                    if(row < 0 || col < 0 || row >= n || col >= n){
                        continue;
                    }
                    if(matrix[row][col] == index){
                        if(visited[row][col] == 0){
                            continue;
                        }
                        visited[row][col] = 0;
                        onSearch[row][col] = true;
                        queue.offer(new int[]{row, col, 0});
                    }
                    else {
                        if(visited[row][col] <= current[2] + 1){
                            continue;
                        }
                        visited[row][col] = current[2] + 1;
                        queue.offer(new int[]{row, col, current[2] + 1});
                    }
                }
            }
        }
    }

    static void island(int srow, int scol){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{srow, scol});
        matrix[srow][scol] = idx;
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= n){
                    continue;
                }
                if(matrix[row][col] == 1){
                    matrix[row][col] = idx;
                    queue.offer(new int[]{row, col});
                }
            }

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];
        onSearch = new boolean[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 1){
                    idx++;
                    island(i, j);
                }
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] != 0){
                    visited = new int[n][n];
                    for(int[] line : visited){
                        Arrays.fill(line, Integer.MAX_VALUE);
                    }
                    if(!onSearch[i][j]) {
                        bfs(i, j);
                    }
                }
            }
        }

//        for(int[] line : matrix){
//            System.out.println(Arrays.toString(line));
//        }

        System.out.println(min - 1);

    }
}
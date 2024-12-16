import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int k;
    static char[][] matrix;
    static int[][][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int bfs(){
        visited[0][0][0] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0, 1, 0});

        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int wall = current[2];
            int distance = current[3];
            int time = current[4];
            if(wall > k) continue;
            else if(current[0] == n-1 && current[1] == m-1){
                min = Math.min(distance, min);
            }
            else{
                int nextTime = time == 0 ? 1 : 0;
                for(int i = 0; i < 4; i++){
                    int row = current[0] + dy[i];
                    int col = current[1] + dx[i];
                    if(row < 0 || col < 0 || row >= n || col >= m){
                        continue;
                    }
                    if(matrix[row][col] == '1' && time == 0){
                        if(visited[row][col][wall+1] > distance + 1){
                            visited[row][col][wall+1] = distance + 1;
                            queue.offer(new int[]{row, col, wall + 1, distance + 1, 1});
                        }
                    }
                    else if(matrix[row][col] == '1' && time == 1){
                        if(visited[row][col][wall+1] > distance + 2){
                            visited[row][col][wall+1]= distance + 2;
                            queue.offer(new int[]{row, col, wall + 1, distance + 2, 1});
                        }
                    }
                    else if(matrix[row][col] == '0'){
                        if(visited[row][col][wall] > distance + 1){
                            visited[row][col][wall]= distance + 1;
                            queue.offer(new int[]{row, col, wall, distance + 1, nextTime});
                        }
                    }
                }
            }
        }

        return min;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        matrix = new char[n][m];
        visited = new int[n][m][k+2];

        for(int[][] matrix : visited){
            for(int[] line : matrix){
                Arrays.fill(line, Integer.MAX_VALUE);
            }
        }


        for(int i = 0; i < n; i++){
            matrix[i] = br.readLine().toCharArray();
        }

        int result = bfs();
        if(result == Integer.MAX_VALUE){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }
    }
}
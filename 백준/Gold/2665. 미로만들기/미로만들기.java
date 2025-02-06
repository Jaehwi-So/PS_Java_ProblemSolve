import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] matrix;
    static int[][] visited;
    static final int MAX = Integer.MAX_VALUE;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        matrix = new char[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = st.nextToken().toCharArray();
        }
        visited = new int[n][n];
        for(int[] line : visited){
            Arrays.fill(line, MAX);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});
        visited[0][0] = 0;


        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            if(visited[row][col] >= current[2]){
                for(int i = 0; i < 4; i++){
                    int nrow = row + dy[i];
                    int ncol = col + dx[i];
                    if(nrow < 0 || ncol < 0 || nrow >= n || ncol >= n) continue;
                    if(matrix[nrow][ncol] == '0'){
                        if(visited[nrow][ncol] > current[2] + 1){
                            queue.offer(new int[]{nrow, ncol, current[2] + 1});
                            visited[nrow][ncol] = current[2] + 1;
                        }
                    }
                    else{
                        if(visited[nrow][ncol] > current[2]){
                            queue.offer(new int[]{nrow, ncol, current[2]});
                            visited[nrow][ncol] = current[2];
                        }
                    }
                }
            }
        }

        System.out.println(visited[n-1][n-1]);

    }
}
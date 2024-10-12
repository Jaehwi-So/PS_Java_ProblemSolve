import java.io.*;
import java.util.*;

public class Main {
    static int h;
    static int n;
    static int m;
    static char[][][] matrix;

    static boolean[][][] visited;

    static int[] dz = {0, 0, 0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};

    static int bfs(int[] start, int[] end){
        Queue<int[]> queue = new LinkedList<>();
        int shei = start[0];
        int srow = start[1];
        int scol = start[2];
        visited[shei][srow][scol] = true;
        queue.offer(start);

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(current[0] == end[0] && current[1] == end[1] && current[2] == end[2]){
                return current[3];
            }
            else{
                for(int i = 0; i < 6; i++){
                    int hei = current[0] + dz[i];
                    int row = current[1] + dy[i];
                    int col = current[2] + dx[i];
                    if(hei < 0 || row < 0 || col < 0 || hei >= h || row >= n || col >= m){
                        continue;
                    }
                    if(matrix[hei][row][col] == '#'){
                        continue;
                    }
                    if(!visited[hei][row][col]){
                        visited[hei][row][col] = true;
                        queue.offer(new int[]{hei, row, col, current[3] + 1});
                    }

                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(true){
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if(h == 0 && n == 0 && m == 0){
                break;
            }

            matrix = new char[h][n][m];
            visited = new boolean[h][n][m];
            int[] start = new int[3];
            int[] end = new int[3];
            for(int i = 0; i < h; i++){
                for(int j = 0; j < n; j++){
                    st = new StringTokenizer(br.readLine());
                    matrix[i][j] = st.nextToken().toCharArray();
                    for(int k = 0; k < m; k++){
                        if(matrix[i][j][k] == 'S'){
                            start = new int[]{i, j, k, 0};
                        }
                        else if(matrix[i][j][k] == 'E'){
                            end = new int[]{i, j, k, 0};
                        }
                    }
                }
                st = new StringTokenizer(br.readLine());
            }

            int result = bfs(start, end);
            if(result == -1){
                sb.append("Trapped!");
            }
            else{
                sb.append(String.format("Escaped in %d minute(s).", result));
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}
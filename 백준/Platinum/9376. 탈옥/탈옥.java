import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[][] matrix;
    static PriorityQueue<int[]> pq;
    static int[][][] visited;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static int[] result;
    static final int MAX = 100000;

    static void bfs(){
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            if(visited[current[0]][current[1]][current[2]] < current[3]) continue;

//            System.out.println(Arrays.toString(current));
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m){
                    result[current[2]] = Math.min(result[current[2]], current[3]);
                    continue;
                }
                if(matrix[row][col] == '*') continue;

                if(matrix[row][col] == '.' || matrix[row][col] == '$'){
                    if(visited[row][col][current[2]] > current[3]){
                        pq.offer(new int[]{row, col, current[2], current[3]});
                        visited[row][col][current[2]] = current[3];
                    }
                }
                else if(matrix[row][col] == '#'){
                    if(visited[row][col][current[2]] > current[3] + 1){
                        pq.offer(new int[]{row, col, current[2], current[3] + 1});
                        visited[row][col][current[2]] = current[3] + 1;
                    }
                    if(current[2] == 0 && visited[row][col][1] != MAX){
                        if(visited[row][col][2] > current[3] + visited[row][col][1]){
                            pq.offer(new int[]{row, col, 2, current[3] + visited[row][col][1]});
                            visited[row][col][2] = current[3] + visited[row][col][1];
                        }
                    }
                    if(current[2] == 1 && visited[row][col][0] != MAX){
                        if(visited[row][col][2] > current[3] + visited[row][col][0]){
                            pq.offer(new int[]{row, col, 2, current[3] + visited[row][col][0]});
                            visited[row][col][2] = current[3] + visited[row][col][0];
                        }
                    }
                }

            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int test = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < test; t++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            matrix = new char[n][m];
            pq = new PriorityQueue(new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    return o1[3] - o2[3];
                }
            });

            visited = new int[n][m][3];
            for(int[][] mat : visited){
                for(int[] line : mat){
                    Arrays.fill(line, MAX);
                }
            }
            result = new int[3];
            Arrays.fill(result, MAX);

            int index = 0;
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                char[] chs = st.nextToken().toCharArray();
                for(int j = 0; j < m; j++){
                    matrix[i][j] = chs[j];
                    if(matrix[i][j] == '$'){
                        pq.offer(new int[]{i, j, index, 0});
                        visited[i][j][index] = 0;
                        index++;
                    }
                }
            }

            bfs();

//            System.out.println(Arrays.toString(result));
            sb.append(Math.min(result[0] + result[1], result[2])).append("\n");
        }

        System.out.println(sb);
    }
}
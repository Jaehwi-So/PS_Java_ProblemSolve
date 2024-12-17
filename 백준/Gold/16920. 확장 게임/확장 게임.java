import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int p;
    static char[][] matrix;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};

    static Queue<int[]> points = new LinkedList<>();
    static Queue<int[]> queue = new LinkedList<>();
    static Queue<int[]> temp = new LinkedList<>();
    static boolean[][] visited;

    static char parse(int a){
        return Integer.toString(a).charAt(0);
    }

    static void bfs(int dist){

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(current[2] < dist){
                temp.offer(current);
                for(int i = 0; i < 4; i++){
                    int row = current[0] + dy[i];
                    int col = current[1] + dx[i];
                    if(row < 0 || col < 0 || row >= n || col >= m){
                        continue;
                    }
                    if(matrix[row][col] == '.' && !visited[row][col]){
                        queue.offer(new int[]{row, col, current[2] + 1, current[3]});
                        visited[row][col] = true;
                    }
                }
            }
            else{
                current[2] = 0;
                points.offer(current);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        visited = new boolean[n][m];


        int[] dist = new int[p+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= p; i++){
            dist[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < n; i++){
            matrix[i] = br.readLine().toCharArray();
        }

        for(int k = 1; k <= p; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (matrix[i][j] == parse(k)) {
                        points.offer(new int[]{i, j, 0, k});
                    }
                }
            }
        }

        int current = 1;
        while(!points.isEmpty()){
            while(!points.isEmpty() && points.peek()[3] == current){
                int[] point = points.poll();
                queue.offer(point);
            }
            bfs(dist[current]);
            while(!temp.isEmpty()){
                int[] point = temp.poll();
                matrix[point[0]][point[1]] = parse(current);
            }
            current++;
            if(current > p){
                current = 1;
            }
        }

        int[] result = new int[p+1];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] != '.' && matrix[i][j] != '#'){
                    result[Character.getNumericValue(matrix[i][j])]++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= p; i++){
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);

    }
}

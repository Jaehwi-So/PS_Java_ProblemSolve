import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] matrix;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int bfs(int srow, int scol){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{srow, scol});
        matrix[srow][scol] = 1;
        int count = 0;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            count++;
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m){
                    continue;
                }
                if(matrix[row][col] == 0){
                    matrix[row][col] = 1;
                    queue.offer(new int[]{row, col});
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        matrix = new int[n][m];

        for(int l = 0; l < k; l++){
            st = new StringTokenizer(br.readLine());
            int sc = Integer.parseInt(st.nextToken());
            int sr = Integer.parseInt(st.nextToken());

            int ec = Integer.parseInt(st.nextToken());
            int er = Integer.parseInt(st.nextToken());

            for(int i = sr; i < er; i++){
                for(int j = sc; j < ec; j++){
                    matrix[i][j] = 1;
                }
            }
        }

        int total = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 0){
                    int count = bfs(i, j);
                    list.add(count);
                    total++;
                }
            }
        }
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        sb.append(total).append("\n");
        for(Integer i : list){
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}

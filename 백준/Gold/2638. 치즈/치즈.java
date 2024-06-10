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
    static int[][] visited;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};

    static Queue<int[]> deleteQ = new LinkedList<>();

    static void visitedReset(){
        for(int[] line : visited){
            Arrays.fill(line, 0);
        }
    }
    static void inner_bfs(int srow, int scol){

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{srow, scol});
        visited[srow][scol] = 1;
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            int row = point[0];
            int col = point[1];
            array[row][col] = 2;
            for(int i = 0; i < 4; i++){
                int r = row + dy[i];
                int c = col + dx[i];
                if(r >= 0 && c >= 0 && r < n && c < m){
                    if(array[r][c] != 1 && visited[r][c] == 0){
                        visited[r][c] = 1;
                        queue.offer(new int[]{r, c});
                    }
                }
            }
        }
    }

    static void bfsOuter(int srow, int scol){
        Queue<int[]> queue = new LinkedList<>();
        visited[srow][scol] = 1;
        queue.offer(new int[]{srow, scol});
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            int row = point[0];
            int col = point[1];
            int count = 0;
            for(int i = 0; i < 4; i++){
                int r = row + dy[i];
                int c = col + dx[i];
                if(r >= 0 && c >= 0 && r < n && c < m){
                    if(array[r][c] == 1 && visited[r][c] == 0){
                        visited[r][c] = 1;
                        queue.offer(new int[]{r, c});
                    }
                    if(array[r][c] == 2){
                        count++;
                    }
                }
            }
            if(count >= 2){
                deleteQ.offer(point);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new int[n][m];
        visited = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
                if(i == 0 || j == 0 || i == n-1 || j == m-1){
                    array[i][j] = 2;
                }
            }
        }

        int count = 0;
        while(true){
            visitedReset();
            inner_bfs(0, 0);

            visitedReset();
            boolean exit = true;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(array[i][j] == 1 && visited[i][j] == 0){
                        bfsOuter(i, j);
                        exit = false;
                    }
                }
            }
            if(exit){
                break;
            }
            else{
                while(!deleteQ.isEmpty()){
                    int[] p = deleteQ.poll();
                    array[p[0]][p[1]] = 2;
                }
                count++;
            }
//            System.out.println("Cheese melt");
//            for(int[] line : array){
//                System.out.println(Arrays.toString(line));
//            }
        }

        System.out.println(count);

    }
}
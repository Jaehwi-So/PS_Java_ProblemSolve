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
    static int[][] temp;
    static boolean[][] visited;
    static int[] dy = {0 , 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static void bfs(int srow, int scol){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{srow, scol});
        visited[srow][scol] = true;

        while(!queue.isEmpty()){
            int[] point = queue.poll();
            int crow = point[0];
            int ccol = point[1];
            for(int i = 0; i < 4; i++){
                int row = crow + dy[i];
                int col = ccol + dx[i];
                if(row >= 0 && row < n && col >= 0 && col < m){
                    if(visited[row][col] == false){
                        if(array[row][col] > 0){
                            queue.offer(new int[]{row, col});
                            visited[row][col] = true;
                        }
                        else{
                            temp[crow][ccol] = Math.max(0, temp[crow][ccol] - 1);
                        }
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
        temp = new int[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        boolean exit = false;
        while(exit == false){
            boolean flag = false;
            for(int i = 0; i < n; i++){
                temp[i] = Arrays.copyOf(array[i], m);
                Arrays.fill(visited[i], false);
            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(array[i][j] > 0 && visited[i][j] == false){
                        if(flag == false){
                            bfs(i, j);
                            flag = true;
                        }
                        else{
                            exit = true;
                            break;
                        }
                    }
                }
                if(exit == true){
                    break;
                }
            }

            if(exit == true){
                break;
            }

            if(flag == false){
                count = 0;
                break;
            }

            count++;
            for(int i = 0; i < n; i++){
                array[i] = Arrays.copyOf(temp[i], m);
            }
        }
        System.out.println(count);
    }
}
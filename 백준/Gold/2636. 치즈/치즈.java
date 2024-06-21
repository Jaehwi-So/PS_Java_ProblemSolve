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
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static boolean[][] visited;
    static Queue<int[]> deleted = new LinkedList<>();
    static void calcAir(){
        int[] start = {0, 0};
        visited[0][0] = true;
        array[0][0] = -1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            for(int i = 0; i < 4; i++){
                int row = point[0] + dy[i];
                int col = point[1] + dx[i];
                if(row >= 0 && row < n && col >= 0 && col < m){
                    if(array[row][col] != 1 && visited[row][col] == false){
                        queue.offer(new int[]{row, col});
                        array[row][col] = -1;
                        visited[row][col] = true;
                    }
                }
            }
        }
    }

    static int bfs(int[] start){
        int count = 0;
        visited[start[0]][start[1]] = true;
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(start);
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            count++;
            boolean isDel = false;
            for(int i = 0; i < 4; i++){
                int row = point[0] + dy[i];
                int col = point[1] + dx[i];
                if(row >= 0 && row < n && col >= 0 && col < m){
                    if(array[row][col] == -1 && isDel == false){
                        deleted.offer(point);
                        isDel = true;
                    }
                    else{
                        if(array[row][col] == 1 && visited[row][col] == false){
                            queue.offer(new int[]{row, col});
                            visited[row][col] = true;
                        }
                    }
                }
            }
        }

        return count;
    }

    static void initVisited(){
        for(boolean[] line : visited){
            Arrays.fill(line, false);
        }
    }

    static void print(){
        for(int[] line : array){
            System.out.println(Arrays.toString(line));
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        array = new int[n][m];
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int before = 0;
        while(true){
            initVisited();
            calcAir();
            initVisited();
            int cnt = 0;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                   if(array[i][j] == 1 && visited[i][j] == false){
                       cnt += bfs(new int[]{i, j});
                   }
                }
            }
            before = cnt;
            time++;
            while(!deleted.isEmpty()){
                int[] point = deleted.poll();
                array[point[0]][point[1]] = -1;
                cnt--;
            }
            if(cnt == 0){
                break;
            }
        }

        System.out.println(time);
        System.out.println(before);
    }
}
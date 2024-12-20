import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[][] matrix;
    static boolean[][][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static Queue<int[]> queue = new LinkedList<>();
    static Queue<int[]> temp = new LinkedList<>();

    static boolean bfs(){
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int type = current[2];
            if(visited[current[0]][current[1]][1] && visited[current[0]][current[1]][2]){
                return true;
            }
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m){
                    continue;
                }
                if(visited[row][col][type] == false){
                    if(matrix[row][col] != 'X'){
                        queue.offer(new int[]{row, col, type});
                    }
                    else if(matrix[row][col] == 'X'){
                        temp.offer(new int[]{row, col, type});
                    }
                }
                visited[row][col][type] = true;
                visited[row][col][0] = true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        visited = new boolean[n][m][3];

        int a = 1;
        Queue<int[]> starts = new LinkedList<>();
        for(int i = 0; i < n; i++){
            matrix[i] = br.readLine().toCharArray();
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 'L'){
                    queue.offer(new int[]{i, j, a});
                    visited[i][j][a] = true;
                    a++;
                }
                else if(matrix[i][j] == '.'){
                    starts.offer(new int[]{i, j, 0});
                }
            }
        }
        int day = 0;

        //초기에는 거위들부터 우선탐색하여 물의 탐색을 줄임
        if(bfs()){
            System.out.println(day);
            return;
        }
        while(!starts.isEmpty()){
            int[] p = starts.poll();
            if(visited[p[0]][p[1]][0]) continue;
            queue.offer(p);
            bfs();
        }

        while(true){
            day++;
            while(!temp.isEmpty()){
                int[] point = temp.poll();
                matrix[point[0]][point[1]] = '.';
                queue.offer(point);
            }
            if(bfs()){
                System.out.println(day);
                return;
            }
        }
    }
}
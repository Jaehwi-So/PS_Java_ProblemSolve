import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[][] matrix;
    static boolean[][] visited;
    static boolean[][][] visited2;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static Queue<int[]> queue = new LinkedList<>();
    static Queue<int[]> temp = new LinkedList<>();
    static Queue<int[]> queue2 = new LinkedList<>();
    static Queue<int[]> temp2 = new LinkedList<>();



    static void bfs(){
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m){
                    continue;
                }
                if(visited[row][col] == false){
                    if(matrix[row][col] == '.'){
                        queue.offer(new int[]{row, col});
                    }
                    else if(matrix[row][col] == 'X'){
                        temp.offer(new int[]{row, col});
                    }
                }
                visited[row][col] = true;
            }
        }
    }

    static boolean bfs2(){
        while(!queue2.isEmpty()){
            int[] current = queue2.poll();
            int idx = current[2];
            int another = idx == 0 ? 1 : 0;
            if(visited2[current[0]][current[1]][another] == true){
                return true;
            }
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m){
                    continue;
                }
                if(visited2[row][col][idx] == false){
                    if(matrix[row][col] == '.'){
                        queue2.offer(new int[]{row, col, idx});
                    }
                    else if(matrix[row][col] == 'X'){
                        temp2.offer(new int[]{row, col, idx});
                    }
                }
                visited2[row][col][idx] = true;
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
        visited = new boolean[n][m];
        visited2 = new boolean[n][m][2];

        int a = 0;
        for(int i = 0; i < n; i++){
            matrix[i] = br.readLine().toCharArray();
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 'L'){
                    matrix[i][j] = '.';
                    queue2.offer(new int[]{i, j, a});
                    visited2[i][j][a] = true;
                    a++;
                }
                if(matrix[i][j] == '.'){
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int day = 0;
        while(true){
            // 백조가 만날 수 있는 지 검사
            if(bfs2()){
                System.out.println(day);
                return;
            }
            day++;


            // 빙산을 녹임
            bfs();
            while(!temp.isEmpty()){
                int[] point = temp.poll();
                matrix[point[0]][point[1]] = '.';
                queue.offer(point);
            }

            // 녹은 다음 백조가 이동할 수 없었던 지점에서 가능해진 지점을 큐에 추가
            Queue<int[]> innerQ = new LinkedList<>();
            while(!temp2.isEmpty()){
                int[] point = temp2.poll();
                if(matrix[point[0]][point[1]] == '.'){
                    queue2.offer(point);
                }
                else{
                    innerQ.offer(point);
                }
            }

            while(!innerQ.isEmpty()){
                temp.offer(innerQ.poll());
            }
        }

    }
}
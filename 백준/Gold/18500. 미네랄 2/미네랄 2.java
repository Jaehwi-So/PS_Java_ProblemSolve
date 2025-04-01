import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[][] matrix;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {-1, 1, 0, 0};
    static Queue<int[]> temp = new LinkedList<>();

    static boolean isSeperate(int row, int col){
        temp.clear();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.offer(new int[]{row, col});
        visited[row][col] = true;

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            temp.offer(current);
            if(current[0] == n - 1){
                temp.clear();
                return false;
            }
            else{
                for(int i = 0; i < 4; i++){
                    int r = current[0] + dy[i];
                    int c = current[1] + dx[i];
                    if(r < 0 || c < 0 || r >= n || c >= m) continue;
                    if(visited[r][c]) continue;
                    if(matrix[r][c] == 'x'){
                        queue.offer(new int[]{r, c});
                        visited[r][c] = true;
                    }
                }
            }
        }
        return true;
    }

    static void down(){

        Stack<int[]> stack = new Stack<>();
        Stack<int[]> stack2 = new Stack<>();
        Stack<int[]> stack3 = new Stack<>();

        while(!temp.isEmpty()){
            int[] point = temp.poll();
            matrix[point[0]][point[1]] = 't';
            stack.push(new int[]{point[0], point[1]});
        }

        while(true){
            boolean exit = false;
            while(!stack.isEmpty()){
                int[] point = stack.pop();
                if(point[0] + 1 == n || matrix[point[0] + 1][point[1]] == 'x'){
                    exit = true;
                    break;
                }
                stack2.push(new int[]{point[0], point[1]});
            }
            if(exit) break;
            while(!stack2.isEmpty()){
                int[] point = stack2.pop();
                matrix[point[0]][point[1]] = '.';
                stack3.push(point);
            }
            while(!stack3.isEmpty()){
                int[] point = stack3.pop();
                matrix[point[0] + 1][point[1]] = 't';
                stack.push(new int[]{point[0] + 1, point[1]});
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(matrix[i][j] == 't') matrix[i][j] = 'x';
            }
        }

    }
    static int destroy(int row, boolean direct){
        int col = -1;
        if(direct){
            for(int i = 0; i < m; i++){
                if(matrix[row][i] == 'x'){
                    matrix[row][i] = '.';
                    col = i;
                    break;
                }
            }
        }
        else{
            for(int i = m - 1; i >= 0; i--){
                if(matrix[row][i] == 'x'){
                    matrix[row][i] = '.';
                    col = i;
                    break;
                }
            }
        }
        return col;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new char[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            matrix[i] = st.nextToken().toCharArray();
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int[] height = new int[k];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            height[i] = n - Integer.parseInt(st.nextToken());
        }

        boolean dir = true;
        for(int l = 0; l < k; l++){
            int row = height[l];
            int col = destroy(row, dir);
            dir = !dir;

            if(col != -1) {
                for (int i = 0; i < 4; i++) {
                    int r = row + dy[i];
                    int c = col + dx[i];
                    if (r < 0 || c < 0 || r >= n || c >= m || matrix[r][c] != 'x') continue;
                    if (isSeperate(r, c)) {
                        down();
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(matrix[i]).append("\n");
        }
        System.out.println(sb);

    }
}
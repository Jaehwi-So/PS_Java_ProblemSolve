import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int k;
    static char[][] matrix;
    static int[][][] visited;
    static final int MAX = Integer.MAX_VALUE;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int appendBit(int bit, int append){
        char[] chs = Integer.toBinaryString(bit).toCharArray();
        chs[append+1] = '1';
        return Integer.parseInt(new String(chs), 2);
    }
    static void bfs(int[] start){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]][start[3]] = 0;
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= m) continue;
                if(matrix[row][col] == 'x') continue;
                int bit = current[3];
                if(matrix[row][col] != '.'){
                    bit = appendBit(bit, Character.getNumericValue(matrix[row][col]));
                }
                if(visited[row][col][bit] > current[2] + 1){
                    queue.offer(new int[]{row, col, current[2] + 1, bit});
                    visited[row][col][bit] = current[2] + 1;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0) break;
            matrix = new char[n][m];
            Queue<int[]> queue = new LinkedList<>();
            int[] start = new int[3];
            int index = 0;
            for(int i = 0; i < n; i++){
                matrix[i] = br.readLine().toCharArray();
                for(int j = 0; j < m; j++){
                    if(matrix[i][j] == '*'){
                        matrix[i][j] = Integer.toString(index).charAt(0);
                        index++;
                        queue.offer(new int[]{i, j});
                    }
                    else if(matrix[i][j] == 'o'){
                        matrix[i][j] = '.';
                        start = new int[]{i, j, 0, 0};
                    }
                }
            }
            k = index;
            start[3] = (int)Math.pow(2, k);
            visited = new int[n][m][(int)Math.pow(2, k+1)];
            for(int[][] mat : visited){
                for(int[] line : mat){
                    Arrays.fill(line, MAX);
                }
            }
            bfs(start);

            int min = MAX;
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    min = Math.min(min, visited[i][j][(int)Math.pow(2, k+1) - 1]);
                }
            }
            if(min == MAX){
                min = -1;
            }
            sb.append(min).append("\n");
        }
        System.out.println(sb);

    }
}
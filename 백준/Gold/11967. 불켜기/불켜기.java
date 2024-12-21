import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static boolean[][] matrix;
    static boolean[][] visited;
    static List<Integer>[][] light;
    static int[][] turn;
    static Queue<int[]> queue = new LinkedList<>();
    static Queue<int[]> temp = new LinkedList<>();
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0 ,0};
    static int result = 1;

    static boolean bfs(){
        boolean hasNext = false;
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(light[current[0]][current[1]] != null){
                for(int idx : light[current[0]][current[1]]){
                    int lrow = turn[idx][0];
                    int lcol = turn[idx][1];
                    if(!matrix[lrow][lcol]){
                        matrix[lrow][lcol] = true;
                        result++;
                        hasNext = true;
                    }
                }
            }
            for(int i = 0; i < 4; i++){
                int row = current[0] + dy[i];
                int col = current[1] + dx[i];
                if(row < 0 || col < 0 || row >= n || col >= n){
                    continue;
                }
                if(!visited[row][col]){
                    visited[row][col] = true;
                    if(!matrix[row][col]){
                        temp.offer(new int[]{row, col});
                    }
                    else{
                        queue.offer(new int[]{row, col});
                    }
                }
            }
        }
        return hasNext;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        matrix = new boolean[n][n];
        visited = new boolean[n][n];
        light = new ArrayList[n][n];
        matrix[0][0] = true;

        turn = new int[m+1][2];

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            turn[i][0] = Integer.parseInt(st.nextToken()) - 1;
            turn[i][1] = Integer.parseInt(st.nextToken()) - 1;
            if(light[row][col] == null){
                light[row][col] = new ArrayList();
            }
            light[row][col].add(i);
        }


        visited[0][0] = true;
        queue.offer(new int[]{0, 0});

        while(true){
            boolean next = bfs();
            if(next){
                int length = temp.size();
                for(int i = 0; i < length; i++){
                    int[] current = temp.poll();
                    if(matrix[current[0]][current[1]]){
                        queue.offer(current);
                    }
                    else{
                        temp.offer(current);
                    }
                }
            }
            else break;
        }

        System.out.println(result);
    }
}
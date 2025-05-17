import java.io.*;
import java.util.*;

public class Main {
    static int n; //2^n
    static int q;
    static int[][] matrix;
    static int[] dy = {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};

    static void iceReduce(){
        Queue<int[]> reduces = new LinkedList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int count = 0;
                for(int k = 0; k < 4; k++){
                    int row = i + dy[k];
                    int col = j + dx[k];
                    if(row < 0 || col < 0 || row >= n || col >= n) continue;
                    if(matrix[row][col] == 0) continue;
                    count++;
                }
                if(count < 3) reduces.add(new int[]{i, j});
            }
        }

        while(!reduces.isEmpty()){
            int[] point = reduces.poll();
            matrix[point[0]][point[1]] = Math.max(matrix[point[0]][point[1]] - 1, 0);
        }
    }


    static void rotate(int l){
        if(l == 0) return;
        int len = (int)Math.pow(2, l);
        int size = len / 2;

        int[][] newMatrix = new int[n][n];
        for(int i = 0; i < n; i += len){
            for(int j = 0; j < n; j += len){

                // 2 0
                // 2 0, 2 1, 3 0, 3 1
                // 2 1, 3 1, 2 0, 3 0
                for(int r = 0; r < len; r++){
                    for(int c = 0; c < len; c++){
                        newMatrix[i+c][j+len-1-r] = matrix[i+r][j+c];
                    }
                }
            }
        }
        matrix = newMatrix;
    }


    static int[] getMaxCluster(){
        boolean[][] visited = new boolean[n][n];
        int[] result = new int[2];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                result[1] += matrix[i][j];
                if(!visited[i][j] && matrix[i][j] > 0){
                    int count = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    while(!queue.isEmpty()){
                        int[] current = queue.poll();
                        count++;
                        for(int k = 0; k < 4; k++){
                            int row = current[0] + dy[k];
                            int col = current[1] + dx[k];
                            if(row < 0 || col < 0 || row >= n || col >= n) continue;
                            if(matrix[row][col] <= 0) continue;
                            if(!visited[row][col]){
                                queue.offer(new int[]{row, col});
                                visited[row][col] = true;
                            }

                        }
                    }
                    result[0] = Math.max(count, result[0]);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = (int)Math.pow(2, Integer.parseInt(st.nextToken()));
        q = Integer.parseInt(st.nextToken());
        matrix = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < q; i++){
            int l = Integer.parseInt(st.nextToken());
            rotate(l);
            iceReduce();
        }

        int[] result = getMaxCluster();
        System.out.println(result[1]);
        System.out.println(result[0]);

    }
}
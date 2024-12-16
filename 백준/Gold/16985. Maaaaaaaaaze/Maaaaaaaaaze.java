import java.io.*;
import java.util.*;

public class Main {
    static char[][][][] origin;
    static char[][][] matrix;
    static boolean[] visited;
    static boolean[][][] V;
    static int[] dy = {0, 0, 0, 0, 1, -1};
    static int[] dx = {0, 0, 1, -1, 0, 0};
    static int[] dz = {1, -1, 0, 0, 0, 0};
    static int min = Integer.MAX_VALUE;

    static void bfs(){
        V = new boolean[5][5][5];
        V[0][0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0, 0});
        while(!queue.isEmpty()){
            int[] current = queue.poll();
            if(current[0] == 4 && current[1] == 4 && current[2] == 4){
                min = Math.min(current[3], min);
                return;
            }
            else if(current[3] >= min){
                continue;
            }
            else{
                for(int i = 0; i < 6; i++){
                    int row = current[0] + dy[i];
                    int col = current[1] + dx[i];
                    int height = current[2] + dz[i];
                    if(row < 0 || col < 0 || height < 0 ||
                        row > 4 || col > 4 || height > 4){
                        continue;
                    }
                    if(!V[row][col][height] && matrix[row][col][height] == '1'){
                        V[row][col][height] = true;
                        queue.offer(new int[]{row, col, height, current[3] + 1});
                    }
                }
            }
        }
    }
    static void search(int step){
        if(step == 5){
            bfs();
        }
        else{
            for(int i = 0; i < 5; i++){
                for(int l = 0; l < 4; l++){
                    if(!visited[i]){
                        if(step == 0 && origin[l][i][0][0] == '0') continue;
                        else if(step == 4 && origin[l][i][4][4] == '0') continue;
                        else{
                            matrix[step] = origin[l][i];
                            visited[i] = true;
                            search(step+1);
                            visited[i] = false;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        origin = new char[4][5][5][5];
        visited = new boolean[5];
        matrix = new char[5][5][5];
        for(int k = 0; k < 5; k++){
            for(int i = 0; i < 5; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 5; j++){
                    origin[0][k][i][j] = st.nextToken().charAt(0);
                }
            }
        }

        for(int l = 1; l < 4; l++){
            for(int k = 0; k < 5; k++){
                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 5; j++){
                        origin[l][k][i][j] = origin[l-1][k][j][4-i];
                    }
                }
            }
        }

        search(0);
        if(min == Integer.MAX_VALUE){
            min = -1;
        }
        System.out.println(min);
        
    }
}

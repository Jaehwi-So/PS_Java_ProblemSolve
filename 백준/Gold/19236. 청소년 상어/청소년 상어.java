import java.io.*;
import java.util.*;

public class Main {
    static int result = -1;
    static int[][][] matrix = new int[4][4][2];
    static int[] dx = new int[]{0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = new int[]{0, -1, -1, 0, 1, 1, 1, 0, -1};

    static void move(){
        for(int i = 1; i <= 16; i++){
            boolean find = false;
            for(int r = 0; r < 4; r++){
                for(int c = 0; c < 4; c++){
                    if(matrix[r][c][0] == i){
                        int dir = matrix[r][c][1];
                        for(int k = 0; k < 8; k++){
                            int row = r + dy[dir];
                            int col = c + dx[dir];
                            if(row < 0 || col < 0 || row >= 4 || col >= 4 || matrix[row][col][0] == -1){
                                dir = ((dir+1) % 9);
                                if(dir == 0) dir++;
                            }
                            else{
                                int tidx = matrix[r][c][0];
                                matrix[r][c][0] = matrix[row][col][0];
                                matrix[r][c][1] = matrix[row][col][1];
                                matrix[row][col][0] = tidx;
                                matrix[row][col][1] = dir;
                                break;
                            }
                        }
                        find = true;
                        break;
                    }
                }
                if(find) break;
            }
        }
    }
    static void dfs(int step, int row, int col, int direct, int sum){
        
        result = Math.max(result, sum);

        int[][][] mtemp = new int[4][4][2];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                mtemp[i][j][0] = matrix[i][j][0];
                mtemp[i][j][1] = matrix[i][j][1];
            }
        }

        move();

        int r = row;
        int c = col;
        while(true){
            r += dy[direct];
            c += dx[direct];
            if(r < 0 || c < 0 || r >= 4 || c >= 4) break;
            else if(matrix[r][c][0] > 0){
                int idx = matrix[r][c][0];
                int dir = matrix[r][c][1];
                matrix[row][col][0] = 0;
                matrix[r][c][0] = -1;

                dfs(step+1, r, c, dir, sum + idx);

                matrix[row][col][0] = -1;
                matrix[r][c][0] = idx;

            }

        }

        matrix = mtemp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                int index = Integer.parseInt(st.nextToken());
                int direct = Integer.parseInt(st.nextToken());
                matrix[i][j][0] = index;
                matrix[i][j][1] = direct;
            }
        }

        int start = matrix[0][0][0];
        matrix[0][0][0] = -1;
        dfs(0, 0, 0, matrix[0][0][1], start);
        System.out.println(result);

    }
}
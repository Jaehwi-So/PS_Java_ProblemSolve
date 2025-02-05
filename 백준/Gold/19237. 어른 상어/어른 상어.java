import java.io.*;
import java.util.*;

public class Main {
    static int n; // 격자 길이
    static int m; // 상어 수
    static int k; // 냄새 지속시간
    static int[][][] matrix;    // 상어 인덱스, 방향
    static int[][][] frav; // 상어 인덱스, 지속시간

    static int[] dy = {0, -1, 1, 0, 0}; //위, 아래, 왼쪽, 오른쪽
    static int[] dx = {0, 0, 0, -1, 1};
    static int[][][] priority;

    static Stack<int[]> stack = new Stack<>();

    static void init(int shark, int direct){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j][0] == shark){
                    matrix[i][j][1] = direct;
                    frav[i][j][0] = matrix[i][j][0];
                    frav[i][j][1] = k;
                    break;
                }
            }
        }
    }

    static void move(){
        // 배열 초기화 및 남아있는 냄새 감소
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j][0] = 0;
                matrix[i][j][1] = 0;
                frav[i][j][1]--;
                if(frav[i][j][1] == 0) frav[i][j][0] = 0;
            }
        }

        // 상어 실제 이동
        while(!stack.isEmpty()){
            int[] info = stack.pop();
            int idx = info[0];
            int row = info[1];
            int col = info[2];
            int direct = info[3];
            if(matrix[row][col][0] != 0 && matrix[row][col][0] < idx) continue;
            matrix[row][col][0] = idx;
            matrix[row][col][1] = direct;
            frav[row][col][0] = idx;
            frav[row][col][1] = k;
        }
    }

    static boolean check(){
        boolean result = true;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j][0] == 1) result = true;
                else if(matrix[i][j][0] != 0) return false;
            }
        }
        return result;
    }

    static void push(){
        for(int idx = 1; idx <= m; idx++){
            boolean find = false;
            for(int row = 0; row < n; row++){
                for(int col = 0; col < n; col++){
                    if(matrix[row][col][0] == idx){
                        boolean move = false;
                        int dir = matrix[row][col][1];
                        for(int p = 0; p < 2; p++){
                            for(int i = 0; i < 4; i++){
                                int d = priority[idx][dir][i];
                                int nrow = row + dy[d];
                                int ncol = col + dx[d];
                                if(nrow < 0 || ncol < 0 || nrow >= n || ncol >= n) continue;
                                if(p == 0 && frav[nrow][ncol][1] > 0) continue;
                                else if(p == 1 && frav[nrow][ncol][1] > 0 && frav[nrow][ncol][0] != idx) continue;
                                else{
                                    stack.push(new int[]{idx, nrow, ncol, d});
                                    move = true;
                                    break;
                                }
                            }
                            if(move) break;
                        }
                    }
                }
                if(find){
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        matrix = new int[n][n][2];
        frav = new int[n][n][2];

        priority = new int[m+1][5][4];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                matrix[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= m; i++){
            init(i, Integer.parseInt(st.nextToken()));
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= 4; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < 4; k++){
                    priority[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int time = 0;
        while(true){
            push();
            move();
            time++;
            if(check() || time > 1000){
                break;
            }
        }

        if(time > 1000) time = -1;
        System.out.println(time);

    }
}